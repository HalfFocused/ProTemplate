package code.patches;

import basemod.helpers.CardModifierManager;
import code.TheDisplaced;
import code.cards.collectible.uncommon.skill.Forsake;
import code.powers.ForsakePower;
import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import code.util.charUtil.mods.DreamModifier;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Iterator;


public class AbstractCardPatches {

    private static CardStrings forsakeCardStrings = CardCrawlGame.languagePack.getCardStrings(Forsake.ID);


    @SpirePatch(
            clz= AbstractCard.class,
            method="canUse"
    )
    static class canUsePatch{
        public static SpireReturn<Boolean> Prefix(AbstractCard __instance, AbstractPlayer p, AbstractMonster m)
        {
            if(p.hasPower(ForsakePower.POWER_ID)){
                boolean hasUUID = false;
                for(AbstractCard card : CardUtil.cardsDrawnWithForsakeThisTurn){
                    if(card.uuid.equals(__instance.uuid)){
                        hasUUID = true;
                        break;
                    }
                }
                if(!hasUUID) {
                    if (AbstractDungeon.player instanceof TheDisplaced) {
                        __instance.cantUseMessage = forsakeCardStrings.EXTENDED_DESCRIPTION[MathUtils.random(0, forsakeCardStrings.EXTENDED_DESCRIPTION.length - 1)];
                    } else {
                        __instance.cantUseMessage = forsakeCardStrings.EXTENDED_DESCRIPTION[0];
                    }
                    return SpireReturn.Return(false);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    static class BlessedCardPatch{
        public static void Postfix(AbstractCard __instance)
        {
            if(AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && CardModifierManager.hasModifier(__instance, DreamModifier.ID)){
                if(MathUtils.random(1,5) == 1) {
                    AbstractGameEffect effect = new DarkOrbPassiveEffect(__instance.hb.x + MathUtils.random(0, __instance.hb.width), __instance.hb.y + MathUtils.random(0, __instance.hb.height));
                    effect.renderBehind = false;
                    effect.duration *= 0.55f;
                    AbstractDungeon.topLevelEffects.add(effect);
                }
            }
        }
    }
}

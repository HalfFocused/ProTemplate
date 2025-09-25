package code.patches;

import code.TheDisplaced;
import code.cards.collectible.uncommon.skill.Forsake;
import code.cards.tokens.Vision;
import code.powers.ForsakePower;
import code.powers.FreeEtherealPower;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect;


public class AbstractCardPatches {

    private static final CardStrings forsakeCardStrings = CardCrawlGame.languagePack.getCardStrings(Forsake.ID);


    @SpirePatch(
        clz= AbstractCard.class,
        method="canUse"
    )
    static class CanUsePatch{
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
                    if (AbstractDungeon.player instanceof TheDisplaced && !CardUtil.isTimeStopped()) {
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
            method="freeToPlay"
    )
    static class FreeToPlayPatch{
        @SpireInsertPatch(
            rloc = 4
        )
        public static SpireReturn<Boolean> FreeToPlayOverride(AbstractCard __instance)
        {
            if(AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hasPower(FreeEtherealPower.POWER_ID) && __instance.isEthereal){
                return SpireReturn.Return(true);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    static class CardRenderPatch{
        public static void Postfix(AbstractCard __instance)
        {
            if(CardUtil.isTimeStopped()){
                if((__instance.rarity == AbstractCard.CardRarity.RARE && __instance.color == TheDisplaced.Enums.DISPLACED_COLOR) || __instance instanceof Vision){
                    if(MathUtils.random(1,6) == 1) {
                        AbstractGameEffect effect = new ShineSparkleEffect(__instance.hb.x + MathUtils.random(0, __instance.hb.width), __instance.hb.y + MathUtils.random(0, __instance.hb.height));
                        effect.renderBehind = false;
                        effect.duration *= 0.65f;
                        AbstractDungeon.topLevelEffects.add(effect);
                    }
                }
            }
        }
    }
}

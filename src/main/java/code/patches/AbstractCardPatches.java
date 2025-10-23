package code.patches;

import code.TheDisplaced;
import code.cards.tokens.Vision;
import code.powers.FreeEtherealPower;
import code.powers.InevitableFormPower;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect;


public class AbstractCardPatches {

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
            if(AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){

                //Highest Priority: Inevitable Form
                InevitableFormPower inevitableFormPower = (InevitableFormPower) AbstractDungeon.player.getPower(InevitableFormPower.POWER_ID);
                if(inevitableFormPower != null && inevitableFormPower.shouldCardCostZero()){
                    return SpireReturn.Return(true);
                }

                //Next Priority: FreeEtherealPower
                if(AbstractDungeon.player.hasPower(FreeEtherealPower.POWER_ID) && __instance.isEthereal){
                    return SpireReturn.Return(true);
                }
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

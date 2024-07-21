package code.patches;

import basemod.helpers.CardModifierManager;
import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import code.util.charUtil.mods.DreamModifier;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;


public class AbstractCardPatches {

    @SpirePatch(
            clz= AbstractCard.class,
            method="triggerOnExhaust"
    )
    static class ForgetCardPatch{
        public static void Prefix(AbstractCard __instance)
        {
            if(__instance instanceof ForgetCard){
                CardUtil.forgetCard((ForgetCard) __instance);
            }
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    static class BlessedCardPatch{
        public static void Postfix(AbstractCard __instance)
        {
            if(CardModifierManager.hasModifier(__instance, DreamModifier.ID)){
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

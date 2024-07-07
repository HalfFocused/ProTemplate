package code.patches;

import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;


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
            if(CardUtil.isCardBlessed(__instance)){
                if(MathUtils.random(1,6) == 1) {
                    LightFlareParticleEffect effect = new LightFlareParticleEffect(__instance.hb.x + MathUtils.random(0, __instance.hb.width), __instance.hb.y + MathUtils.random(0, __instance.hb.height), Color.WHITE);
                    effect.renderBehind = false;
                    effect.duration *= 0.65;
                    AbstractDungeon.topLevelEffects.add(effect);
                }
            }
        }
    }
}

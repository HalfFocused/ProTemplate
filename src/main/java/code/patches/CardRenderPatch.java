package code.patches;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;


public class CardRenderPatch {


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


    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    static class MythicCardPatch{
        public static void Postfix(AbstractCard __instance)
        {
            if(CardUtil.isMythic(__instance)){
                if(MathUtils.random(1,6) == 1) {
                    DarkOrbPassiveEffect effect = new DarkOrbPassiveEffect(__instance.hb.x + MathUtils.random(0, __instance.hb.width), __instance.hb.y + MathUtils.random(0, __instance.hb.height));
                    effect.renderBehind = false;
                    AbstractDungeon.topLevelEffects.add(effect);
                }
            }
        }
    }

    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCard"
    )
    static class DebilitatedCardPatch{
        public static void Postfix(AbstractCard __instance)
        {
            if(CardUtil.isDebilitated(__instance)){
                if(MathUtils.random(1,6) == 1) {
                    AbstractGameEffect effect = new FallingDustEffect(__instance.hb.x + MathUtils.random(0, __instance.hb.width), __instance.hb.y + MathUtils.random(0, __instance.hb.height));
                    effect.renderBehind = false;
                    AbstractDungeon.topLevelEffects.add(effect);
                }
            }
        }
    }

}

package code.patches;

import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
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
            method="triggerOnExhaust"
    )
    static class BlessedCardPatch{
        public static void Prefix(AbstractCard __instance)
        {
            if(__instance instanceof ForgetCard){
                CardUtil.forgetCard((ForgetCard) __instance);
            }
        }
    }
}

package code.patches;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.StunStarEffect;
import com.megacrit.cardcrawl.vfx.combat.UnknownParticleEffect;
import com.megacrit.cardcrawl.vfx.scene.*;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.rs;


public class TimeStopVFXPatch {
    @SpirePatch2(clz= AbstractMonster.class, method = "render")
    private static class MonsterAnimationPatch{
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument()
        {

            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException
                {
                    if(m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : (code.util.charUtil.CardUtil.isTimeSlowed() ? $proceed($$) / 5.0f : $proceed($$)));}");
                    }
                }
            };

        }

        @SpireInstrumentPatch
        public static ExprEditor animVarsInstrument() {
            return new ExprEditor() {
                public void edit(FieldAccess f)
                        throws CannotCompileException {
                    if (f.isReader() && (f.getFieldName().equals("animX") || f.getFieldName().equals("animY"))) {
                        f.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : $proceed($$));}");
                    }else if(f.isReader() && f.getFieldName().equals("color") && f.getClassName().equals(TintEffect.class.getName())){
                        f.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? new com.badlogic.gdx.graphics.Color(0.5f, 0.5f, 0.5f, 1f) : $proceed($$));}");
                    }
                }
            };
        }
    }



    @SpirePatch2(clz= AbstractMonster.class, method = "updateIntentVFX")
    private static class MonsterIntentVFXPatch {
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument() {

            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : $proceed($$));}");
                    }
                }
            };

        }
    }

    @SpirePatch2(clz= AbstractMonster.class, method = "updateIntent")
    private static class MonsterIntentEffectsVFXPatch {
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(AbstractGameEffect.class.getName()) && m.getMethodName().equals("update")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? \"\" : $proceed($$));}");
                    }
                }
            };
        }
    }

    @SpirePatch2(clz= BobEffect.class, method = "update")
    private static class BobEffectPatch {
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument()
        {

            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException
                {
                    if(m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : (code.util.charUtil.CardUtil.isTimeSlowed() ? $proceed($$) / 5.0f : $proceed($$)));}");
                    }
                }
            };

        }
    }

    @SpirePatch2(clz= InteractableTorchEffect.class, method = "update")
    private static class TorchEffectPausePatch {
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument() {

            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : $proceed($$));}");
                    }
                }
            };
        }
    }



    @SpirePatch2(clz= AbstractDungeon.class, method = "update")
    private static class SceneUpdatePausePatch {
        @SpireInstrumentPatch
        public static ExprEditor pauseSceneUpdatesInstrument() {

            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(AbstractScene.class.getName()) && m.getMethodName().equals("update")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? \"\" : $proceed($$));}");
                    }else if(m.getClassName().equals(AbstractGameEffect.class.getName()) && m.getMethodName().equals("update")){
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped() && code.patches.TimeStopVFXPatch.shouldFreeze(e)) ? \"\" : $proceed($$));}");
                    }
                }
            };
        }
    }

    public static boolean shouldFreeze(AbstractGameEffect effect){
        return(effect instanceof TorchParticleSEffect ||
            effect instanceof TorchParticleMEffect ||
            effect instanceof TorchParticleLEffect ||
            effect instanceof TorchParticleXLEffect ||
            effect instanceof LightFlareSEffect ||
            effect instanceof LightFlareMEffect ||
            effect instanceof LightFlareLEffect ||
            effect instanceof BuffParticleEffect ||
            effect instanceof DebuffParticleEffect ||
            effect instanceof StunStarEffect ||
            effect instanceof UnknownParticleEffect ||
            effect instanceof ShieldParticleEffect);
    }
}
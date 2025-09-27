package code.patches;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.monsters.city.TorchHead;
import com.megacrit.cardcrawl.monsters.exordium.HexaghostBody;
import com.megacrit.cardcrawl.monsters.exordium.HexaghostOrb;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheBeyondScene;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect;
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

    @SpirePatch2(clz= AbstractMonster.class, method = "renderIntent")
    private static class MonsterIntentRenderPatch {
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

    @SpirePatch2(clz= TheCollector.class, method = "update")
    @SpirePatch2(clz= TorchHead.class, method = "update")
    @SpirePatch2(clz= HexaghostOrb.class, method = "update")
    @SpirePatch2(clz= HexaghostBody.class, method = "update")
    @SpirePatch2(clz= AwakenedOne.class, method = "update")
    private static class EmitterMonsterUpdatePatch {
        @SpireInstrumentPatch
        public static ExprEditor deltaTimeInstrument() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : (code.util.charUtil.CardUtil.isTimeSlowed() ? $proceed($$) / 5.0f : $proceed($$)));}");
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
                    }
                }
            };
        }
    }

    /*
    Some particles that need to be frozen during the time stop.
     */
    @SpirePatch2(clz= TorchParticleSEffect.class, method = "update")
    @SpirePatch2(clz= TorchParticleMEffect.class, method = "update")
    @SpirePatch2(clz= TorchParticleLEffect.class, method = "update")
    @SpirePatch2(clz= TorchParticleXLEffect.class, method = "update")
    @SpirePatch2(clz= LightFlareSEffect.class, method = "update")
    @SpirePatch2(clz= LightFlareMEffect.class, method = "update")
    @SpirePatch2(clz= LightFlareLEffect.class, method = "update")
    @SpirePatch2(clz= BuffParticleEffect.class, method = "update")
    @SpirePatch2(clz= DebuffParticleEffect.class, method = "update")
    @SpirePatch2(clz= StunStarEffect.class, method = "update")
    @SpirePatch2(clz= GlowyFireEyesEffect.class, method = "update")
    @SpirePatch2(clz= StaffFireEffect.class, method = "update")
    @SpirePatch2(clz= TorchHeadFireEffect.class, method = "update")
    @SpirePatch2(clz= GhostIgniteEffect.class, method = "update")
    @SpirePatch2(clz= GhostlyFireEffect.class, method = "update")
    @SpirePatch2(clz= GhostlyWeakFireEffect.class, method = "update")
    @SpirePatch2(clz= ShieldParticleEffect.class, method = "update")
    @SpirePatch2(clz= AwakenedEyeParticle.class, method = "update")
    @SpirePatch2(clz= AwakenedWingParticle.class, method = "update")
    private static class ParticleFreezePatch {
        @SpireInstrumentPatch
        public static ExprEditor particleFreezeInstrumentPatch() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(Graphics.class.getName()) && m.getMethodName().equals("getDeltaTime")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? 0 : (code.util.charUtil.CardUtil.isTimeSlowed() ? $proceed($$) / 5.0f : $proceed($$)));}");
                    }
                }
            };
        }
    }

    /*
    Some particles unfortunately use random generation in their render code.
    To keep them still we instrument patch in a replacement with the max value of the random range
    instead, when needed.
     */
    @SpirePatch2(clz= TorchParticleSEffect.class, method = "render")
    @SpirePatch2(clz= TorchParticleMEffect.class, method = "render")
    @SpirePatch2(clz= TorchParticleLEffect.class, method = "render")
    @SpirePatch2(clz= TorchParticleXLEffect.class, method = "render")
    @SpirePatch2(clz= GhostlyFireEffect.class, method = "render")
    @SpirePatch2(clz= GhostlyWeakFireEffect.class, method = "render")
    @SpirePatch2(clz= AwakenedWingParticle.class, method = "render",
            paramtypez = {SpriteBatch.class, float.class, float.class})
    @SpirePatch2(clz= AwakenedEyeParticle.class, method = "render")
    private static class NoJitteringPatch {
        @SpireInstrumentPatch
        public static ExprEditor particleFreezeInstrumentPatch() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(MathUtils.class.getName()) && m.getMethodName().equals("random")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? $2 : $proceed($$));}");
                    }
                }
            };
        }
    }

    /*
    Did you know the floaters in The Beyond background use system time? i sure didn't!
    */
    @SpirePatch2(clz= TheBeyondScene.class, method = "renderCombatRoomBg")
    private static class SystemTimeFreezePatch {
        @SpireInstrumentPatch
        public static ExprEditor particleFreezeInstrumentPatch() {
            return new ExprEditor() {
                public void edit(MethodCall m)
                        throws CannotCompileException {
                    if (m.getClassName().equals(System.class.getName()) && m.getMethodName().equals("currentTimeMillis")) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.isTimeStopped()) ? code.actions.TheSecondDreamAction.lastActivatedSystemTime : $proceed($$));}");
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
            effect instanceof GlowyFireEyesEffect ||
            effect instanceof StaffFireEffect ||
            effect instanceof TorchHeadFireEffect ||
            effect instanceof GhostIgniteEffect ||
            effect instanceof GhostlyFireEffect ||
            effect instanceof GhostlyWeakFireEffect ||
            effect instanceof ShieldParticleEffect);
    }
}
package code.patches;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.lang.reflect.Field;

import static basemod.BaseMod.logger;
import static code.ModFile.makeShaderPath;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.rs;

/*
Huge thanks to the one and only @Cany0udance for both the original shader design and an improvement made
specifically for me that excludes the player model. This is wizardry to me but super awesome.
 */
public class TimeStopShaderPatch {
    public static ShaderProgram timestopShader;
    private static final FrameBuffer fbo;
    private static boolean skipPlayerRender = false;

    @SpirePatch(clz = AbstractDungeon.class, method = "render")
    public static class RenderDesaturationInCombat {
        private static Field animationTimerField;

        static {
            try {
                animationTimerField = AbstractCreature.class.getDeclaredField("animationTimer");
                animationTimerField.setAccessible(true);
            } catch (NoSuchFieldException e) {

            }
        }

        @SpireInsertPatch(locator = BeforeBackgroundRender.class)
        public static void startShader(AbstractDungeon instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                skipPlayerRender = true;
                StartFbo(sb);
            }
        }

        @SpireInsertPatch(locator = AfterRoomRender.class)
        public static void applyShaderButKeepPlayer(AbstractDungeon instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                skipPlayerRender = false;
                StopFbo(sb);
                if (player != null) {
                    player.render(sb);
                }
            }
        }
        private static class BeforeBackgroundRender extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher("com.megacrit.cardcrawl.dungeons.AbstractDungeon", "rs");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
        private static class BeforeRoomRender extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher("com.megacrit.cardcrawl.rooms.AbstractRoom", "render");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
        private static class AfterRoomRender extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher("com.megacrit.cardcrawl.rooms.AbstractRoom", "render");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                return new int[]{lines[0] + 1};
            }
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "render")
    public static class SkipPlayerDuringShaderPass {
        @SpirePrefixPatch
        public static SpireReturn<Void> skipRender(AbstractPlayer instance, SpriteBatch sb) {
            if (skipPlayerRender) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    public static void StartFbo(SpriteBatch sb) {
        sb.flush();
        fbo.begin();
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    public static void StopFbo(SpriteBatch sb) {
        sb.flush();
        fbo.end();
        TextureRegion region = new TextureRegion(fbo.getColorBufferTexture());
        region.flip(false, true);
        sb.setShader(timestopShader);
        sb.setColor(Color.WHITE);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb.draw(region, 0f, 0f);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.setShader(null);
        sb.flush();
    }
    static {
        timestopShader = new ShaderProgram(
                Gdx.files.internal(makeShaderPath("vertex.vs")).readString(),
                Gdx.files.internal(makeShaderPath("fragment.fs")).readString()
        );
        if (!timestopShader.isCompiled()) {
            logger.warn("Timestop shader not compiled: " + timestopShader.getLog());
        }
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
    }
}

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
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basemod.BaseMod.logger;
import static code.ModFile.makeShaderPath;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.rs;

public class TimeStopShaderPatch {
    public static ShaderProgram desaturationShader;
    private static final FrameBuffer fbo;

    @SpirePatch(clz = AbstractDungeon.class, method = "render")
    public static class RenderDesaturationInCombat {
        @SpirePrefixPatch
        public static void addShader(AbstractDungeon instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                StartFbo(sb);
            }
        }

        @SpireInsertPatch(rloc = 26)
        public static void removeShader(AbstractDungeon instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                StopFbo(sb);
            }
        }
    }
    /*


    @SpirePatch(clz = AbstractPlayer.class, method = "render")
    public static class AvoidShadingPlayer {
        @SpirePrefixPatch
        public static void removeShader(AbstractPlayer instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                StopFbo(sb);
            }
        }

        @SpirePostfixPatch
        public static void addShader(AbstractPlayer instance, SpriteBatch sb) {
            if (rs == AbstractDungeon.RenderScene.NORMAL && CardUtil.isTimeStopped()) {
                StartFbo(sb);
            }
        }
    }

     */

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
        sb.setShader(desaturationShader);
        sb.setColor(Color.WHITE);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb.draw(region, 0f, 0f);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.setShader(null);
        sb.flush();
    }

    static {
        desaturationShader = new ShaderProgram(
                Gdx.files.internal(makeShaderPath("vertex.vs")).readString(),
                Gdx.files.internal(makeShaderPath("fragment.fs")).readString()
        );
        if (!desaturationShader.isCompiled()) {
            logger.warn("Desaturation shader: " + desaturationShader.getLog());
        }
        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
    }
}

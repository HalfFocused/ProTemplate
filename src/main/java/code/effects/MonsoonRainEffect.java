package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static code.effects.MonsoonEffect.monsoonOpacity;

public class MonsoonRainEffect extends AbstractGameEffect {
    private float scaleY;
    private float x;
    private float y;
    private float vX;
    private float vY;

    public MonsoonRainEffect(Color setColor, float alpha) {
        this.x = MathUtils.random(-0.2F, 0.95f) * Settings.WIDTH - 128.0F;
        this.vX = MathUtils.random(300.0F, 300.0F) * Settings.scale;
        this.rotation = 95f;
        this.y = MathUtils.random(1.1F, 1.25F) * (float)Settings.HEIGHT - 128.0F;
        this.vY = MathUtils.random(-1350.0F, -1350.0F) * Settings.scale;
        this.duration = 2.0F;
        this.scale = MathUtils.random(1.5F, 1.9F);
        this.vX *= this.scale;
        this.scale *= Settings.scale;
        this.scaleY = MathUtils.random(0.5F, 2.0F) * Settings.scale;
        this.color = setColor.cpy();
        this.color.a = MathUtils.random(0.75F, 0.95F);
        /*
        if (this.scaleY < 1.0F * Settings.scale) {
            this.renderBehind = true;
        }
         */

    }

    public void update() {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.color.a = MathUtils.random(0.75F, 0.95F) * monsoonOpacity;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(ImageMaster.HORIZONTAL_LINE, this.x, this.y, 128.0F, 128.0F, 24.0F, 24.0F, this.scale * MathUtils.random(0.7F, 1.3F), this.scaleY * MathUtils.random(0.7F, 1.3F), this.rotation, 0, 0, 256, 256, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}

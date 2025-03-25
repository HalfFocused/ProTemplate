//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FutureHazeEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float vX2;
    private float vY2;
    private float aV;

    public FutureHazeEffect(boolean initial) {
        this.duration = MathUtils.random(5.0F, 8.0F);
        this.startingDuration = this.duration;
        if(initial) this.duration /= 2;
        int roll = MathUtils.random(5);
        if (roll == 0) {
            this.img = ImageMaster.DEATH_VFX_1;
        } else if (roll == 1) {
            this.img = ImageMaster.DEATH_VFX_2;
        } else if (roll == 2) {
            this.img = ImageMaster.DEATH_VFX_3;
        } else if (roll == 3) {
            this.img = ImageMaster.DEATH_VFX_4;
        } else if (roll == 4) {
            this.img = ImageMaster.DEATH_VFX_5;
        } else {
            this.img = ImageMaster.DEATH_VFX_6;
        }

        this.x = MathUtils.random(0.0F, (float)Settings.WIDTH) - (float)this.img.packedWidth / 2.0F;
        this.y = MathUtils.random(0.0F, (float)Settings.HEIGHT) - (float)this.img.packedHeight / 2.0F;
        this.vX = MathUtils.random(-20.0F, 20.0F) * Settings.scale * this.scale;
        this.vY = MathUtils.random(-60.0F, 60.0F) * Settings.scale * this.scale;
        this.vX2 = MathUtils.random(-20.0F, 20.0F) * Settings.scale * this.scale;
        this.vY2 = MathUtils.random(-60.0F, 60.0F) * Settings.scale * this.scale;
        this.aV = MathUtils.random(-50.0F, 50.0F);
        float tmp = MathUtils.random(0.2F, 0.4F);
        this.color = new Color();
        this.color.r = tmp;
        this.color.g = tmp;
        this.color.b = tmp;
        this.renderBehind = false;
        this.scale = MathUtils.random(12.0F, 20.0F) * Settings.scale;
    }// 49

    public void update() {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.vX += this.vX2 * Gdx.graphics.getDeltaTime();
        this.vY += this.vY2 * Gdx.graphics.getDeltaTime();
        this.rotation += this.aV * Gdx.graphics.getDeltaTime();
        if (this.startingDuration - this.duration < 1.5F) {
            this.color.a = Interpolation.fade.apply(0.0F, 0.25F, (this.startingDuration - this.duration) / 1.5F);
        } else if (this.duration < 1.5F) {
            this.color.a = Interpolation.fade.apply(0.25F, 0.0F, 1.0F - this.duration / 1.5F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose() {
    }
}

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SparkleEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float oX;
    private float oY;
    private float dur_div2;
    private Hitbox hb;
    private AtlasRegion img;

    public SparkleEffect(float x, float y, Color color) {
        this((Hitbox)null);// 20
        this.x = x;// 21
        this.y = y;// 22
        this.color = color;
    }// 23

    public SparkleEffect(Hitbox hb) {
        this.hb = null;// 16
        this.hb = hb;// 26
        this.img = ImageMaster.ROOM_SHINE_2;// 27
        this.duration = MathUtils.random(0.9F, 1.2F);// 28
        this.scale = MathUtils.random(0.6F, 0.9F) * Settings.scale;// 29
        this.dur_div2 = this.duration / 2.0F;// 30
        this.color = new Color(1.0F, MathUtils.random(0.6F, 0.8F), 0.798F, 0.0F);// 31
        this.oX += MathUtils.random(-50.0F, 50.0F) * Settings.scale;// 33
        this.oY += MathUtils.random(-50.0F, 50.0F) * Settings.scale;// 34
        this.oX -= (float)this.img.packedWidth / 2.0F;// 35
        this.oY -= (float)this.img.packedHeight / 2.0F;// 36
        this.renderBehind = MathUtils.randomBoolean(0.2F + (this.scale - 0.5F));// 38
        this.rotation = MathUtils.random(-5.0F, 5.0F);// 39
    }// 40

    public void update() {
        if (this.duration > this.dur_div2) {// 44
            this.color.a = Interpolation.pow3In.apply(0.6F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);// 45
        } else {
            this.color.a = Interpolation.pow3In.apply(0.0F, 0.6F, this.duration / this.dur_div2);// 47
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 50
        if (this.duration < 0.0F) {// 51
            this.isDone = true;// 52
        }

    }// 54

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);// 58
        sb.setBlendFunction(770, 1);// 59
        if (this.hb == null) {// 60
            sb.draw(this.img, this.x + this.oX, this.y + this.oY, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale * MathUtils.random(0.6F, 1.4F), this.rotation);// 61 70
        } else {
            sb.draw(this.img, this.hb.cX + this.oX, this.hb.cY + this.oY, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale * MathUtils.random(0.6F, 1.4F), this.rotation);// 73 82
        }

        sb.setBlendFunction(770, 771);// 85
    }// 86

    public void dispose() {
    }// 90
}

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;

public class BeforeYourEyesEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vY;
    private float dur_div2;
    private TextureAtlas.AtlasRegion img;

    public BeforeYourEyesEffect() {
        this.scale = Settings.scale;// 20
        this.img = ImageMaster.EYE_ANIM_0;// 21
        this.scale = MathUtils.random(1.0F, 1.5F);// 22
        this.startingDuration = this.scale + 0.8F;// 23
        this.duration = this.startingDuration;// 24
        this.scale *= Settings.scale;// 25
        this.dur_div2 = this.duration / 2.0F;// 27
        this.color = new Color(MathUtils.random(0.3F, 0.7F), MathUtils.random(0.3F, 0.7F), MathUtils.random(0.3F, 0.7F), 0.0F);// 28
        this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 100.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 25.0F * Settings.scale);// 30
        this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F + 30.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);// 34
        this.renderBehind = MathUtils.randomBoolean();// 38
        this.rotation = MathUtils.random(12.0F, 6.0F);// 39
        if (this.x > AbstractDungeon.player.hb.cX) {// 40
            this.rotation = -this.rotation;// 41
        }

        this.x -= (float)this.img.packedWidth / 2.0F;// 44
        this.y -= (float)this.img.packedHeight / 2.0F;// 45
    }

    public void update() {
        if (this.duration > this.dur_div2) {// 50
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);// 51
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);// 53
        }

        if (this.duration > this.startingDuration * 0.85F) {// 56
            this.vY = 12.0F * Settings.scale;// 57
            this.img = ImageMaster.EYE_ANIM_0;// 58
        } else if (this.duration > this.startingDuration * 0.8F) {// 59
            this.vY = 8.0F * Settings.scale;// 60
            this.img = ImageMaster.EYE_ANIM_1;// 61
        } else if (this.duration > this.startingDuration * 0.75F) {// 62
            this.vY = 4.0F * Settings.scale;// 63
            this.img = ImageMaster.EYE_ANIM_2;// 64
        } else if (this.duration > this.startingDuration * 0.7F) {// 65
            this.vY = 3.0F * Settings.scale;// 66
            this.img = ImageMaster.EYE_ANIM_3;// 67
        } else if (this.duration > this.startingDuration * 0.65F) {// 68
            this.img = ImageMaster.EYE_ANIM_4;// 69
        } else if (this.duration > this.startingDuration * 0.6F) {// 70
            this.img = ImageMaster.EYE_ANIM_5;// 71
        } else if (this.duration > this.startingDuration * 0.55F) {// 72
            this.img = ImageMaster.EYE_ANIM_6;// 73
        } else if (this.duration > this.startingDuration * 0.38F) {// 74
            this.img = ImageMaster.EYE_ANIM_5;// 75
        } else if (this.duration > this.startingDuration * 0.3F) {// 76
            this.img = ImageMaster.EYE_ANIM_4;// 77
        } else if (this.duration > this.startingDuration * 0.25F) {// 78
            this.vY = 3.0F * Settings.scale;// 79
            this.img = ImageMaster.EYE_ANIM_3;// 80
        } else if (this.duration > this.startingDuration * 0.2F) {// 81
            this.vY = 4.0F * Settings.scale;// 82
            this.img = ImageMaster.EYE_ANIM_2;// 83
        } else if (this.duration > this.startingDuration * 0.15F) {// 84
            this.vY = 8.0F * Settings.scale;// 85
            this.img = ImageMaster.EYE_ANIM_1;// 86
        } else {
            this.vY = 12.0F * Settings.scale;// 88
            this.img = ImageMaster.EYE_ANIM_0;// 89
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 92
        if (this.duration < 0.0F) {// 93
            this.isDone = true;// 94
        }

    }// 96

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);// 100
        sb.setBlendFunction(770, 1);// 101
        sb.draw(this.img, this.x, this.y + this.vY, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);// 102
        sb.setBlendFunction(770, 771);// 113
    }// 114

    public void dispose() {
    }// 118
}

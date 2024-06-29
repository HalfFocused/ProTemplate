//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class BlessCardEffect extends AbstractGameEffect {
    private static AtlasRegion img;
    private float sX;
    private float sY;
    private float cX;
    private float cY;
    private float dX;
    private float dY;
    private float yOffset;
    private float bounceHeight;
    private static final float DUR = 0.5F;
    private boolean playedSfx = false;
    private float sparkleTimer = 0.0F;

    public BlessCardEffect(float destX, float destY) {
        if (img == null) {// 25
            img = ImageMaster.vfxAtlas.findRegion("combat/empowerCircle1");// 26
        }

        this.sX = Settings.WIDTH * 0.5f;// 29
        this.sY = Settings.HEIGHT * 0.5f;// 30
        this.cX = this.sX;// 31
        this.cY = this.sY;// 32
        this.dX = destX;// 33
        this.dY = destY;// 34
        this.rotation = 0.0F;// 35
        this.duration = 0.1F;// 36
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);// 38
        bounceHeight = 0;

    }// 45

    public void update() {
        this.renderBehind = false;
        if (!this.playedSfx) {// 48
            this.playedSfx = true;// 49
            CardCrawlGame.sound.playA("ATTACK_WHIFF_2", MathUtils.random(0.7F, 0.8F));// 50
        }

        this.sparkleTimer -= Gdx.graphics.getDeltaTime();// 53
        if (this.sparkleTimer <= 0.0F) {// 54
            for(int i = 0; i < MathUtils.random(2, 5); ++i) {// 55
                LightFlareParticleEffect flare = new LightFlareParticleEffect(this.cX, this.cY, Color.WHITE);
                flare.renderBehind = false;
                AbstractDungeon.topLevelEffectsQueue.add(flare);// 56
            }

            this.sparkleTimer = MathUtils.random(0.001F, 0.005F);// 58
        }

        this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.1F);// 61
        this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.1F);// 62
        if (this.dX > this.sX) {// 64
            this.rotation -= Gdx.graphics.getDeltaTime() * 1000.0F;// 65
        } else {
            this.rotation += Gdx.graphics.getDeltaTime() * 1000.0F;// 67
        }

        if (this.duration > 0.05F) {// 70
            this.color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 0.25F) / 0.2F) * Settings.scale;// 71
            this.yOffset = Interpolation.circleIn.apply(this.bounceHeight, 0.0F, (this.duration - 0.25F) / 0.25F) * Settings.scale;// 72
        } else {
            this.yOffset = Interpolation.circleOut.apply(0.0F, this.bounceHeight, this.duration / 0.25F) * Settings.scale;// 74
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 77
        if (this.duration < 0.0F) {// 78
            this.isDone = true;// 79
            //CardCrawlGame.sound.playA("GOLD_GAIN", MathUtils.random(0.0F, 0.1F));// 80
            AdditiveSlashImpactEffect additiveSlashImpactEffect = new AdditiveSlashImpactEffect(this.dX, this.dY, Color.WHITE.cpy());
            additiveSlashImpactEffect.renderBehind = false;
            AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, additiveSlashImpactEffect, 0.05f, true));// 81 82
        }

    }// 84

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);// 89
        sb.setColor(new Color(0.4F, 1.0F, 1.0F, this.color.a / 5.0F));// 90
        sb.setColor(this.color);// 92
        sb.draw(img, this.cX - (float)(img.packedWidth / 2), this.cY - (float)(img.packedHeight / 2), (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * 0.7F, this.scale * 0.4F, this.rotation);// 93
        sb.setBlendFunction(770, 771);// 104
    }// 105

    public void dispose() {
    }// 109
}

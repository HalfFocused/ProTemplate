package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MonsoonEffect extends AbstractGameEffect {
    float halfwayPoint;

    public static float monsoonOpacity = 0F;
    public MonsoonEffect(float durationIn) {
        this.startingDuration = durationIn;
        this.duration = this.startingDuration;
        halfwayPoint = startingDuration / 2.0f;
        this.color = new Color(0.9F, 0.9F, 0.9F, 0.0F);
        this.renderBehind = true;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
        }

        if (this.duration > halfwayPoint) {
            this.color.a = Interpolation.pow3In.apply(0.9F, 0.0F, (this.duration - halfwayPoint) / halfwayPoint);
            monsoonOpacity = 1f;
        } else {
            this.color.a = Interpolation.pow3Out.apply(0.0F, 0.9F, this.duration / halfwayPoint);
            monsoonOpacity = this.color.a;
        }

        for (int i = 0; i < 4; i++) {
            AbstractDungeon.effectsQueue.add(new MonsoonRainEffect(Color.GRAY.cpy(), this.color.a));
        }


        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a * 0.7F));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
        sb.setColor(this.color);
        sb.draw(ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }
}
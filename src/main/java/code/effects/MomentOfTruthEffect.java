package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class MomentOfTruthEffect extends AbstractGameEffect {

    private static final float FADE_TIME = 0.35f;
    public MomentOfTruthEffect() {
        this.startingDuration = FADE_TIME * 2;
        this.duration = this.startingDuration;
        this.color = Color.GOLD.cpy();
        this.renderBehind = true;
    }

    public void update() {
        if(this.duration == startingDuration){
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.GOLD.cpy()));
        }
        for (int i = 0; i < 2; i++) {
            AbstractDungeon.effectsQueue.add(new SparkleEffect(MathUtils.random(0, Settings.WIDTH), MathUtils.random(0, Settings.HEIGHT), Color.GOLD.cpy()));
        }

        if (this.duration > FADE_TIME) {
            this.color.a = Interpolation.pow3In.apply(0.9F, 0.0F, (this.duration - FADE_TIME) / FADE_TIME);
        } else {
            this.color.a = Interpolation.pow3Out.apply(0.0F, 0.9F, this.duration / FADE_TIME);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a * 0.35f));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }
}
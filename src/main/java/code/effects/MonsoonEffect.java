package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MonsoonEffect extends AbstractGameEffect {
    float halfwayPoint;
    private static final float FADE_TIME = 0.75f;

    public static boolean monsoonHappening = false;
    public static float monsoonOpacity = 0F;

    private float lightningNoiseTimer;

    public MonsoonEffect() {
        this.startingDuration = FADE_TIME * 2;
        this.duration = this.startingDuration;
        halfwayPoint = startingDuration / 2.0f;
        this.color = new Color(0.9F, 0.9F, 0.9F, 0.0F);
        this.renderBehind = true;
        monsoonHappening = true;
        lightningNoiseTimer = 0f;
    }

    public void update() {
        if (lightningNoiseTimer <= 0) {
            CardCrawlGame.sound.playA("ORB_LIGHTNING_EVOKE", 0.4f + MathUtils.random(0.1f, 0.3f));
            lightningNoiseTimer = MathUtils.random(1.6f, 3.5f);
        }
        lightningNoiseTimer -= Gdx.graphics.getDeltaTime();

        if (this.duration > FADE_TIME) {
            this.color.a = Interpolation.pow3In.apply(0.9F, 0.0F, (this.duration - FADE_TIME) / FADE_TIME);
            monsoonOpacity = 1f;
        } else if(!monsoonHappening) {
            this.color.a = Interpolation.pow3Out.apply(0.0F, 0.9F, this.duration / FADE_TIME);
            monsoonOpacity = this.color.a;
        }

        for (int i = 0; i < 4; i++) {
            AbstractDungeon.effectsQueue.add(new MonsoonRainEffect(Color.GRAY.cpy(), false));
        }

        for (int i = 0; i < 5; i++) {
            AbstractDungeon.effectsQueue.add(new MonsoonRainEffect(Color.GRAY.cpy(), true));
        }

        if(!monsoonHappening || this.duration > FADE_TIME) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }
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
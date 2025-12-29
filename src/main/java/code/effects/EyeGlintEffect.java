package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AwakenedEyeParticle;

public class EyeGlintEffect extends AwakenedEyeParticle {
    public EyeGlintEffect(float x, float y) {
        super(x, y);
        this.scale = Settings.scale * MathUtils.random(0.75F, 1F);
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration / this.startingDuration);
    }
}

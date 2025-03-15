//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class RecklessAbandonEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int count = 5;

    public RecklessAbandonEffect(float x, float y, Color setColor) {
        this.x = x;// 20
        this.y = y;// 21
        this.duration = 0.0F;// 22
        this.color = setColor;// 23
        CardCrawlGame.screenShake.shake(ShakeIntensity.MED, ShakeDur.SHORT, false);// 24
    }// 25

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            if(MathUtils.randomBoolean()) {
                AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + MathUtils.random(-100.0F, 100.0F) * Settings.scale, this.y + MathUtils.random(-100.0F, 100.0F) * Settings.scale, 0.0F, 0.0F, MathUtils.random(360.0F), MathUtils.random(2.5F, 4.0F), this.color, this.color));
            }
            if (MathUtils.randomBoolean()) {
                AbstractDungeon.effectsQueue.add(new FlashAtkImgEffect(this.x + MathUtils.random(-150.0F, 150.0F) * Settings.scale, this.y + MathUtils.random(-150.0F, 150.0F) * Settings.scale, randomSlash(), true));
            }

            this.duration = MathUtils.random(0.1F, 0.15F);
            --this.count;
        }

        if (this.count == 0) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }

    private AbstractGameAction.AttackEffect randomSlash(){
        switch (MathUtils.random(3)){
            case 1:
                return AbstractGameAction.AttackEffect.SLASH_HEAVY;
            case 2:
                return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
            case 3:
                return AbstractGameAction.AttackEffect.SMASH;
        }
        return AbstractGameAction.AttackEffect.SLASH_HEAVY;
    }
}

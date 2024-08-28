//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class WarpEffect extends AbstractGameEffect {
    public WarpEffect(int warps) {
        this.startingDuration = 1f * warps;// 15
        this.duration = this.startingDuration;// 16
        this.color = new Color(0.8F, 0.15F, 0.8F, 0.0F);// 17
        this.renderBehind = true;// 18
    }// 19

    public void update() {
        if (this.duration == this.startingDuration) {// 23
            CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);// 24
        }

        if (this.duration > this.startingDuration / 2.0F) {// 26
            this.color.a = Interpolation.linear.apply(0F, 0.5F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));// 27
        } else {
            this.color.a = Interpolation.linear.apply(0.5F, 0F, this.duration * (this.startingDuration / 2.0F));// 32
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 34
        if (this.duration < 0.0F) {// 35
            this.isDone = true;// 36
        }

    }// 38

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a * 0.8F));// 42
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);// 43
        sb.setColor(this.color);// 44
        sb.draw(ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);// 45
    }// 46

    public void dispose() {
    }// 50
}

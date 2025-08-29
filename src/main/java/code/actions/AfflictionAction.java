package code.actions;

import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class AfflictionAction extends AbstractGameAction {
    private DamageInfo info;

    public AfflictionAction(AbstractCreature target, DamageInfo info) {
        this.info = info;// 18
        this.setValues(target, info);// 19
        this.actionType = ActionType.DAMAGE;// 20
        this.startDuration = Settings.ACTION_DUR_FAST;// 21
        this.duration = this.startDuration;// 22
    }// 23

    public void update() {
        if (this.shouldCancelAction()) {// 27
            this.isDone = true;// 28
        } else {
            this.tickDuration();// 32
            if (this.isDone) {// 34
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY, false));// 35
                this.target.damage(this.info);// 37
                if (this.target.lastDamageTaken > 0) {// 38
                    this.addToBot(new ApplyPowerAction(this.target, AbstractDungeon.player, new ForetoldPower(this.target, target.lastDamageTaken), target.lastDamageTaken));
                    if (this.target.hb != null) {// 40
                        this.addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));// 41
                    }
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {// 45
                    AbstractDungeon.actionManager.clearPostCombatActions();// 46
                } else {
                    this.addToTop(new WaitAction(0.1F));// 48
                }
            }

        }
    }// 29 51
}
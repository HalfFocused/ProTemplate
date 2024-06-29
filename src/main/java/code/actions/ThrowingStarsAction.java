
package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

import java.util.Iterator;

public class ThrowingStarsAction extends AbstractGameAction {
    private DamageInfo info;

    public ThrowingStarsAction(AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;// 18
        this.info = info;// 19
        this.actionType = ActionType.DAMAGE;// 20
        this.target = target;// 21
    }// 22

    public void update() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();// 26

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.isEthereal) {// 27
                this.addToTop(new DamageAction(this.target, this.info, true));// 28
                if (this.target != null && this.target.hb != null) {// 29
                    this.addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));// 30
                }
            }
        }

        this.isDone = true;// 34
    }// 35
}

package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ForceWaitAction extends AbstractGameAction {
    public ForceWaitAction(float setDur) {
        this.setValues((AbstractCreature)null, (AbstractCreature)null, 0);// 9
        this.duration = setDur;

        this.actionType = ActionType.WAIT;// 15
    }// 16

    public void update() {
        this.tickDuration();// 20
    }// 21
}

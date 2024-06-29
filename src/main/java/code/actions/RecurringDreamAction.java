package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;

public class RecurringDreamAction extends AbstractGameAction {
    public RecurringDreamAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        this.isDone = true;
        this.tickDuration();
    }
}

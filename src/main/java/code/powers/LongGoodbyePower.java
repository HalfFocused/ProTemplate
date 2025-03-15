package code.powers;

import code.ModFile;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LongGoodbyePower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("LongGoodbyePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LongGoodbyePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = (amount == 1) ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];;
    }

    @Override
    public void atStartOfTurn() {
        if(CardUtil.queuedWarps == 0) { //no ticking down while warping, plz!
            this.addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }
}

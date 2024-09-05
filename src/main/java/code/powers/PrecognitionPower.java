package code.powers;

import code.ModFile;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class PrecognitionPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("PrecognitionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PrecognitionPower(AbstractCreature owner, int amountIn) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amountIn);
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void onExhaust(AbstractCard exhausted) {
        this.flash();
        this.addToBot(new DrawCardAction(1));
        this.addToBot(new ReducePowerAction(owner,owner, this, 1));
    }
}

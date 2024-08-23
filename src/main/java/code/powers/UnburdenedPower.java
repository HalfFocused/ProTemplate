package code.powers;

import code.ModFile;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UnburdenedPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("UnburdenedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean triggeredThisTurn = false;

    public UnburdenedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        triggeredThisTurn = false;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if(!triggeredThisTurn){
            this.flash();
            this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
            triggeredThisTurn = true;
        }
    }
}

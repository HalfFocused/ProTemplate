package code.powers;

import code.ModFile;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class NextTurnSetEnergyPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("NextTurnSetEnergyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextTurnSetEnergyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                EnergyPanel.setEnergy(NextTurnSetEnergyPower.this.amount);
                isDone = true;
            }
        });
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        this.flash();
    }
}

package code.powers;

import code.ModFile;
import code.cards.collectible.uncommon.power.DoubleTime;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class DoubleTimePower extends AbstractEasyPower implements NonStackablePower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("DoubleTimePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    boolean createUpgraded;

    public DoubleTimePower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        createUpgraded = upgraded;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new GainEnergyAction(1));
        this.addToBot(new DrawCardAction(this.owner, 1));
        this.addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void onRemove() {
        AbstractCard cardToCreate = new DoubleTime();
        if(createUpgraded){
            cardToCreate.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(cardToCreate));
    }

    @Override
    public void updateDescription() {
        description = (amount == 1) ? (createUpgraded ? DESCRIPTIONS[1] : DESCRIPTIONS[0]) : DESCRIPTIONS[1] + amount + (createUpgraded ? DESCRIPTIONS[3] : DESCRIPTIONS[2]);
    }


}

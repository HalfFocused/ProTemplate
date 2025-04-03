package code.powers;

import code.ModFile;
import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class EyesWideOpenPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("EyesWideOpenPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EyesWideOpenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToTop(new MakeTempCardInDrawPileAction(new Vision(), amount, true, true));
    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }


}

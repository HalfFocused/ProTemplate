package code.powers;

import code.ModFile;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LapsePower extends AbstractEasyPower implements NonStackablePower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("LapsePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractCard card;

    public LapsePower(AbstractCreature owner, AbstractCard cardIn) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, -1);
        card = cardIn.makeStatEquivalentCopy();
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new MakeTempCardInHandAction(card));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        if(card == null) return;
        description = DESCRIPTIONS[0] + FontHelper.colorString(this.card.name, "y") + DESCRIPTIONS[1];
    }


}
package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;
import code.powers.BeforeYourEyesPower;
import code.powers.TimeStopPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class BeforeYourEyes extends AbstractEasyCard {
    public final static String ID = makeID("BeforeYourEyes");

    public BeforeYourEyes() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BeforeYourEyesPower(p, magicNumber)));
    }

    public void upp() {
        isEthereal = false;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}
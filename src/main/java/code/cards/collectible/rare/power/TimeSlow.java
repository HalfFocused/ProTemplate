package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.TimeSlowPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TimeSlow extends AbstractEasyCard {
    public final static String ID = makeID(TimeSlow.class.getSimpleName());

    public TimeSlow() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        //isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TimeSlowPower(p, magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
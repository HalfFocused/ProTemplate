package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.powers.DoubleTimePower;
import code.powers.EmptinessPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class DoubleTime extends AbstractEasyCard {
    public final static String ID = makeID(DoubleTime.class.getSimpleName());

    public DoubleTime() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleTimePower(p, magicNumber, upgraded)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}

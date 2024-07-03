package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.powers.EmptinessPower;
import code.powers.RisingStormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Emptiness extends AbstractEasyCard {
    public final static String ID = makeID(Emptiness.class.getSimpleName());

    public Emptiness() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EmptinessPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}

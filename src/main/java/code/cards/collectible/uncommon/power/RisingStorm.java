package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.powers.RisingStormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class RisingStorm extends AbstractEasyCard {
    public final static String ID = makeID(RisingStorm.class.getSimpleName());

    public RisingStorm() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RisingStormPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
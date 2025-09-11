package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.UnburdenedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClingingOn extends AbstractEasyCard {
    public final static String ID = makeID(ClingingOn.class.getSimpleName());


    public ClingingOn() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new UnburdenedPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.AsExpectedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AsExpected extends AbstractEasyCard {
    public final static String ID = makeID(AsExpected.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , , 

    public AsExpected() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new AsExpectedPower(p, 1)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
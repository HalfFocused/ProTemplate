package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.PrecognitionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Precognition extends AbstractEasyCard {
    public final static String ID = makeID("Precognition");
    // intellij stuff power, self, uncommon, , , , , 5, 3

    public Precognition() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new PrecognitionPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}
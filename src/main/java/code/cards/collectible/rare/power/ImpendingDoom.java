package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.EntropyPower;
import code.powers.ImpendingDoomPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ImpendingDoom extends AbstractEasyCard {
    public final static String ID = makeID("ImpendingDoom");

    public ImpendingDoom() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ImpendingDoomPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
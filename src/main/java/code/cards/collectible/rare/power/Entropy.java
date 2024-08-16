package code.cards.collectible.rare.power;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.EntropyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Entropy extends AbstractEasyCard {
    public final static String ID = makeID(Entropy.class.getSimpleName());

    public Entropy() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EntropyPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new WarpAction(secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}
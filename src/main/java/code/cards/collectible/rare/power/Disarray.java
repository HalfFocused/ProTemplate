package code.cards.collectible.rare.power;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.DisarrayPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MayhemPower;

public class Disarray extends AbstractEasyCard {
    public final static String ID = makeID(Disarray.class.getSimpleName());

    public Disarray() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DisarrayPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new WarpAction(secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}
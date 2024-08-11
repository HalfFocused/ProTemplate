package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.OverAndOverPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OverAndOver extends AbstractEasyCard {
    public final static String ID = makeID(OverAndOver.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , , 

    public OverAndOver() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new OverAndOverPower(p, 1)));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
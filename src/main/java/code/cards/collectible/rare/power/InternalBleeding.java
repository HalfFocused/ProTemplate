package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.InternalBleedingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InternalBleeding extends AbstractEasyCard {
    public final static String ID = makeID(InternalBleeding.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , 1, 

    public InternalBleeding() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new InternalBleedingPower(p,magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
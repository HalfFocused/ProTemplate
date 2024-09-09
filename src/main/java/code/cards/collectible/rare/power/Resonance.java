package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ResonancePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Resonance extends AbstractEasyCard {
    public final static String ID = makeID("Resonance");
    // intellij stuff power, self, rare, , , , , 1, 

    public Resonance() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new ResonancePower(p,magicNumber)));
    }

    public void upp() {
        isEthereal = false;
    }
}
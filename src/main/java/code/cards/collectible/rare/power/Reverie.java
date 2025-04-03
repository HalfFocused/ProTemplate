package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ReveriePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reverie extends AbstractEasyCard {
    public final static String ID = makeID(Reverie.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , 1, 

    public Reverie() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ReveriePower(p, magicNumber), magicNumber));
    }

    public void upp() {
        isInnate = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}
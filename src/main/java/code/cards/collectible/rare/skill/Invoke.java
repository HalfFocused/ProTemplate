package code.cards.collectible.rare.skill;


import code.actions.AlignAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Invoke extends AbstractEasyCard {
    public final static String ID = makeID(Invoke.class.getSimpleName());

    public Invoke() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AlignAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
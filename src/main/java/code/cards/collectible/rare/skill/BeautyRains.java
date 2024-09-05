package code.cards.collectible.rare.skill;

import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BeautyRains extends AbstractEasyCard {
    public final static String ID = makeID("BeautyRains");
    // intellij stuff skill, self, rare, , , , , 3, 2

    public BeautyRains() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PredictAction(magicNumber, card -> card.isEthereal));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
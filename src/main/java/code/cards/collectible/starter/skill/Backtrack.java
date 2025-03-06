package code.cards.collectible.starter.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Backtrack extends AbstractEasyCard {
    public final static String ID = makeID(Backtrack.class.getSimpleName());

    public Backtrack() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DiscardPileToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeBlock(2);
    }
}
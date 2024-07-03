package code.cards.collectible.starter.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Salvation extends AbstractEasyCard {
    public final static String ID = makeID(Salvation.class.getSimpleName());

    public Salvation() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new PutOnDeckAction(p,p,1,false));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
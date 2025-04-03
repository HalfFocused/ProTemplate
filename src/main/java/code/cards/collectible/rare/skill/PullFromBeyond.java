package code.cards.collectible.rare.skill;

import basemod.AutoAdd;
import code.actions.FutureBorrowAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
@AutoAdd.Ignore
public class PullFromBeyond extends AbstractEasyCard {
    public final static String ID = makeID("PullFromBeyond");

    public PullFromBeyond() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FutureBorrowAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
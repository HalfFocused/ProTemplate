package code.cards.collectible.uncommon.skill;

import code.actions.SeeingDoubleAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SeeingDouble extends AbstractEasyCard {
    public final static String ID = makeID(SeeingDouble.class.getSimpleName());

    public SeeingDouble() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(1, new SeeingDoubleAction()));
    }

    @Override
    public void upp() {
        selfRetain = true;
    }
}
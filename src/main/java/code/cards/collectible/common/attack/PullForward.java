package code.cards.collectible.common.attack;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PullForward extends AbstractEasyCard {
    public final static String ID = makeID(PullForward.class.getSimpleName());

    public PullForward() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new WarpAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
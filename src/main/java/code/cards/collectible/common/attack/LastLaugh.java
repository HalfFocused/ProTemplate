package code.cards.collectible.common.attack;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LastLaugh extends AbstractEasyCard {
    public final static String ID = makeID(LastLaugh.class.getSimpleName());
    public LastLaugh() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(2);
    }
}
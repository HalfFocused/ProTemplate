package code.cards.collectible.starter.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Elude extends AbstractEasyCard {
    public final static String ID = makeID("Elude");
    // intellij stuff attack, enemy, starter, 10, 3, , , , 


    public Elude() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        isEthereal = true;
        //random hit damage
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
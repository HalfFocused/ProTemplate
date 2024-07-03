package code.cards.collectible.starter.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.WitherPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Exposure extends AbstractEasyCard {
    public final static String ID = makeID(Exposure.class.getSimpleName());

    public Exposure() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        this.addToBot(new ApplyPowerAction(p, p, new WitherPower(m, this.magicNumber), magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}
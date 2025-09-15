package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Expose extends AbstractEasyCard {
    public final static String ID = makeID(Expose.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , 2, 1

    public Expose() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new ApplyPowerAction(m, p, new ForetoldPower(m, magicNumber), magicNumber));
    }
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
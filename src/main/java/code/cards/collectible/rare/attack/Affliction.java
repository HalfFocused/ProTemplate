package code.cards.collectible.rare.attack;

import code.actions.AfflictionAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Affliction extends AbstractEasyCard {
    public final static String ID = makeID(Affliction.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 10, 3, , , , 

    public Affliction() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AfflictionAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
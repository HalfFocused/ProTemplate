package code.cards.collectible.uncommon.attack;

import code.actions.AfflictionAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Affliction extends AbstractEasyCard {
    public final static String ID = makeID(Affliction.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 10, 3, , , , 

    public Affliction() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AfflictionAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
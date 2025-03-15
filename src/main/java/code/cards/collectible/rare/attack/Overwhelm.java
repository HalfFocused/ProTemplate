package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Overwhelm extends AbstractEasyCard {
    public final static String ID = makeID(Overwhelm.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 3, , , , , 

    public Overwhelm() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        this.addToBot(new ModifyDamageAction(this.uuid, this.baseDamage));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
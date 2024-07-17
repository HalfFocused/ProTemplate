package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ClingOnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClingOn extends AbstractEasyCard {
    public final static String ID = makeID(ClingOn.class.getSimpleName());

    public ClingOn() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        baseBlock = block = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new ApplyPowerAction(p,p, new ClingOnPower(p, 1), 1));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}
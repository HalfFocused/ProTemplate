package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static code.ModFile.makeID;

public class ShootingStar extends AbstractEasyCard {
    public final static String ID = makeID("ShootingStar");

    public ShootingStar() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyPowerAction(p,p, new DrawCardNextTurnPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
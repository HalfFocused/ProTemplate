package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TemporalStrike extends AbstractEasyCard {
    public final static String ID = makeID(TemporalStrike.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 10, 4, , , , 

    public TemporalStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 0;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void applyPowers(){
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = GameActionManager.turn * 2;
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = GameActionManager.turn * 2;
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
}
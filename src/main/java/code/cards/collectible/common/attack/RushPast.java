package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;

public class RushPast extends AbstractEasyCard {
    public final static String ID = makeID(RushPast.class.getSimpleName());

    public RushPast() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));// 36
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));// 37
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeDamage(3);

    }
}
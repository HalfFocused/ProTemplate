package code.cards.collectible.common.attack;

import basemod.ReflectionHacks;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.Iterator;

public class Confidence extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(Confidence.class.getSimpleName());
    // intellij stuff attack, enemy, common, 12, 6, , , 1, 1

    public Confidence() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));// 36
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));// 37
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    public void upp() {
        upgradeDamage(6);
        upgradeMagicNumber(2);
    }

    @Override
    public void onForget() {

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            this.addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

            if (!mo.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new GainStrengthPower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}
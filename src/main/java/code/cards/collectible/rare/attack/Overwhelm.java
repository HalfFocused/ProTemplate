package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class Overwhelm extends AbstractEasyCard {
    public final static String ID = makeID(Overwhelm.class.getSimpleName());

    private int timesPlayed = 0;

    public Overwhelm() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        timesPlayed++;
        this.addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, timesPlayed), 0.2F));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        this.addToBot(new ModifyDamageAction(this.uuid, this.baseDamage));
    }

    public void upp() {
        upgradeDamage(2);
    }
}
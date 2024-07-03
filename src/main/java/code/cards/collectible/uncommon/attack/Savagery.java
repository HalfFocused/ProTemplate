package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class Savagery extends AbstractEasyCard {
    public final static String ID = makeID(Savagery.class.getSimpleName());

    public Savagery() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        if (Settings.FAST_MODE) {// 39
            this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));// 40

            for(i = 0; i < 5; ++i) {// 41
                this.addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));// 42
            }
        } else {
            this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));// 45

            for(i = 0; i < 5; ++i) {// 46
                this.addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY)));// 47
            }
        }

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(4);
    }
}
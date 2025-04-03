package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Ruckus extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("Ruckus");
    // intellij stuff attack, all_enemy, uncommon, 6, , , , 3, 4

    public Ruckus() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage =  7;
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < 3; ++i) {
            dmgRandom(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public void onForget() {
        for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
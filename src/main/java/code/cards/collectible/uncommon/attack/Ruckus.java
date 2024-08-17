package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

public class Ruckus extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("Ruckus");
    // intellij stuff attack, all_enemy, uncommon, 6, , , , 3, 4

    public Ruckus() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 3;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {// 34
            dmgRandom(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void onForget() {
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EquilibriumPower(AbstractDungeon.player, 1), 1));
    }
}
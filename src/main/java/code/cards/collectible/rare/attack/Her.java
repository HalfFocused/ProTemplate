package code.cards.collectible.rare.attack;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Her extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("Her");
    // intellij stuff attack, all_enemy, rare, 16, 6, , , 2, 

    public Her() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 20;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public void onForget() {
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new WeakPower(AbstractDungeon.player, magicNumber, false)));
    }
}
package code.cards.collectible.rare.attack;

import code.actions.AllEnemiesLoseHPAction;
import code.cards.AbstractEasyCard;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static code.ModFile.makeID;

public class BallOfLight extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("BallOfLight");

    public BallOfLight() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseMagicNumber = magicNumber = 6;
        baseSecondMagic = secondMagic = 12;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++){
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(4);
    }

    @Override
    public void onForget() {
        this.addToTop(new AllEnemiesLoseHPAction(AbstractDungeon.player, secondMagic));
    }
}
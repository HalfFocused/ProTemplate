package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Aggression extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(Aggression.class.getSimpleName());

    public Aggression() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        isEthereal = true;
        baseDamage = damage = 10;
        baseBlock = block = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }

    @Override
    public void onForget() {
        this.addToTop(new GainBlockAction(AbstractDungeon.player,AbstractDungeon.player,block));
    }
}
package code.cards.collectible.rare.skill;

import code.actions.AllEnemiesLoseHPAction;
import code.actions.RelapseAction;
import code.cards.AbstractEasyCard;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Relapse extends AbstractEasyCard {
    public final static String ID = makeID(Relapse.class.getSimpleName());

    public Relapse() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ALL);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RelapseAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
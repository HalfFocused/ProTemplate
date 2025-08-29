package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Predict extends AbstractEasyCard {
    public final static String ID = makeID(Predict.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , 2, 2

    public Predict() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new ForetoldPower(m, this.magicNumber), magicNumber));
        this.addToBot(new DrawCardAction(secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
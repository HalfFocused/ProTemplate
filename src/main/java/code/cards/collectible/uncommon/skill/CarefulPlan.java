package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CarefulPlan extends AbstractEasyCard {
    public final static String ID = makeID(CarefulPlan.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 1, 1

    public CarefulPlan() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 4;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new DrawCardAction(secondMagic));
    }


    public void upp() {
        upgradeSecondMagic(1);
    }
}
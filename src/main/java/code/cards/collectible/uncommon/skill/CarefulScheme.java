package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CarefulScheme extends AbstractEasyCard {
    public final static String ID = makeID(CarefulScheme.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 1, 1

    public CarefulScheme() {
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
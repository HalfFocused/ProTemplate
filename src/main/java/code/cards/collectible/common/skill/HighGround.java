package code.cards.collectible.common.skill;

import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class HighGround extends AbstractEasyCard {
    public final static String ID = makeID("HighGround");
    // intellij stuff skill, self, common, , , 4, 2, 1, 

    public HighGround() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m, magicNumber, false)));
        addToBot(new PredictAction(secondMagic, card -> card.type == CardType.ATTACK));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
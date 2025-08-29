package code.cards.collectible.uncommon.skill;

import code.actions.FlashbackAction;
import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class HighGround extends AbstractEasyCard {
    public final static String ID = makeID("HighGround");
    // intellij stuff skill, self, common, , , 4, 2, 1, 

    public HighGround() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m, magicNumber, false)));
        addToBot(new FlashbackAction(1, card -> card.type == CardType.ATTACK));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
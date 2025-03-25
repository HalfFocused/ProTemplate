package code.cards.collectible.rare.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.LongGoodbyePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LongGoodbye extends AbstractEasyCard {
    public final static String ID = makeID("LongGoodbye");
    // intellij stuff skill, self, rare, , , 9, 3, , 

    public LongGoodbye() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = 9;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new LongGoodbyePower(p, magicNumber)));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}
package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

public class DesperateDodge extends AbstractEasyCard {
    public final static String ID = makeID(DesperateDodge.class.getSimpleName());

    public DesperateDodge() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 10;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.blck();
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false), magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
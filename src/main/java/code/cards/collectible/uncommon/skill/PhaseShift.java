package code.cards.collectible.uncommon.skill;

import code.actions.PhaseShiftAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PhaseShift extends AbstractEasyCard {
    public final static String ID = makeID(PhaseShift.class.getSimpleName());

    public PhaseShift() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PhaseShiftAction(p, block, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
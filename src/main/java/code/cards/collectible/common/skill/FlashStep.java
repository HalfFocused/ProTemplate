package code.cards.collectible.common.skill;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class FlashStep extends AbstractEasyCard {
    public final static String ID = makeID("FlashStep");

    public FlashStep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new WarpAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
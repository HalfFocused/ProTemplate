package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;
import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class FlashStep extends AbstractEasyCard {
    public final static String ID = makeID(FlashStep.class.getSimpleName());

    public FlashStep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(m, p, new ForetoldPower(m, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
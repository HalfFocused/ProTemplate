package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FutureSight extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("FutureSight");

    public FutureSight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        isEthereal = true;
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void onForget() {
        addToBot(new DrawCardAction(magicNumber));
    }
}
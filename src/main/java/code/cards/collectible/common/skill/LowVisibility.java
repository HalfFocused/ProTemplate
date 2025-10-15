package code.cards.collectible.common.skill;

import code.actions.FlashbackAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LowVisibility extends AbstractEasyCard {
    public final static String ID = makeID(LowVisibility.class.getSimpleName());

    public LowVisibility() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DrawCardAction(magicNumber));
        this.addToBot(new ExhaustAction(1, true, false, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
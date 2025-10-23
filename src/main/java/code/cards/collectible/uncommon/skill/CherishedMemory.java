package code.cards.collectible.uncommon.skill;

import code.actions.FlashbackAction;
import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class CherishedMemory extends AbstractEasyCard {
    public final static String ID = makeID(CherishedMemory.class.getSimpleName());

    public CherishedMemory() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlashbackAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
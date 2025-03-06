package code.cards.collectible.uncommon.skill;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.DisplacedTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Blink extends AbstractEasyCard {
    public final static String ID = makeID(Blink.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public Blink() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(DisplacedTags.CARDS_THAT_WARP);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WarpAction(magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}
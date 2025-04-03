package code.cards.collectible.uncommon.skill;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.DisplacedTags;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LostInThought extends AbstractEasyCard {
    public final static String ID = makeID("LostInThought");
    // intellij stuff skill, self, uncommon, , , , , 2, 

    public LostInThought() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        tags.add(DisplacedTags.CARDS_THAT_WARP);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
        this.addToBot(new WarpAction(secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}
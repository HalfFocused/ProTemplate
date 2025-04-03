package code.cards.collectible.rare.skill;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.DisplacedTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Accelerate extends AbstractEasyCard {
    public final static String ID = makeID("Accelerate");
    // intellij stuff skill, self, rare, , , , , 3, 

    public Accelerate() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        tags.add(DisplacedTags.CARDS_THAT_WARP);
        baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WarpAction(magicNumber));
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.selfRetain = true;
    }
}
package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DejaVu extends AbstractEasyCard {
    public final static String ID = makeID(DejaVu.class.getSimpleName());

    public DejaVu() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Vision();
        baseMagicNumber = magicNumber = 1;
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new MakeTempCardInDrawPileAction(new Vision(), 1, false, true));
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("temporary")); //TODO: Account for localization files
    }
}
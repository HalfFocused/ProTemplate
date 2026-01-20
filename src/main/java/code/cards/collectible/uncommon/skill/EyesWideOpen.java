package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;
import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class EyesWideOpen extends AbstractEasyCard {
    public final static String ID = makeID(EyesWideOpen.class.getSimpleName());

    public EyesWideOpen() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.cardsToPreview = new Vision();
        //shuffleBackIntoDrawPile = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new ApplyPowerAction(p, p, new EyesWideOpenPower(p, magicNumber)));
        addToBot(new MakeTempCardInDrawPileAction(new Vision(), 1, true, true));
        addToBot(new DrawCardAction(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("temporary")); //TODO: Account for localization files
    }
}
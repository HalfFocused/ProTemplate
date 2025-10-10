package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import code.powers.TheStarsAlignedPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheStarsAligned extends AbstractEasyCard {
    public final static String ID = makeID(TheStarsAligned.class.getSimpleName());

    public TheStarsAligned() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Vision(), 1, true, true));
        this.addToBot(new ApplyPowerAction(p, p, new TheStarsAlignedPower(p)));
    }

    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("temporary")); //TODO: Account for localization files
    }
}
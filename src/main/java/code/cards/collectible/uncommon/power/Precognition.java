package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import code.powers.PrecognitionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Precognition extends AbstractEasyCard {
    public final static String ID = makeID(Precognition.class.getSimpleName());

    public Precognition() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInDrawPileAction(new Vision(), 1, true, true));
        this.addToBot(new ApplyPowerAction(p, p, new PrecognitionPower(p, magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("dream")); //TODO: Account for localization files
    }
}
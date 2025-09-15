package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.cards.tokens.Vision;
import code.powers.EyesWideOpenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class EyesWideOpen extends AbstractEasyCard {
    public final static String ID = makeID(EyesWideOpen.class.getSimpleName());

    public EyesWideOpen() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EyesWideOpenPower(p, magicNumber)));
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
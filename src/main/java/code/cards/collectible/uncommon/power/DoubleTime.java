package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.powers.DoubleTimePower;
import code.powers.EmptinessPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class DoubleTime extends AbstractEasyCard {
    public final static String ID = makeID(DoubleTime.class.getSimpleName());

    private final boolean noPreview = false;

    public DoubleTime() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.cardsToPreview = new DoubleTime(true);
        if(upgraded){
            cardsToPreview.upgrade();
        }
    }

    public DoubleTime(boolean noPreview) {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        if(!noPreview) {
            this.cardsToPreview = new DoubleTime();
            if(upgraded){
                cardsToPreview.upgrade();
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleTimePower(p, magicNumber, upgraded)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        if(cardsToPreview != null) {
            cardsToPreview.upgrade();
        }
    }
}

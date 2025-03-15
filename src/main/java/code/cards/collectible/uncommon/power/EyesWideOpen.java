package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.cards.tokens.Vision;
import code.powers.EyesWideOpenPower;
import code.powers.PrecognitionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class EyesWideOpen extends AbstractEasyCard {
    public final static String ID = makeID(EyesWideOpen.class.getSimpleName());

    public EyesWideOpen() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EyesWideOpenPower(p, magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
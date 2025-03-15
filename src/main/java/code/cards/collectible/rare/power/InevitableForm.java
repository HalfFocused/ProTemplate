package code.cards.collectible.rare.power;

import code.actions.WarpAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.InevitableFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InevitableForm extends AbstractEasyCard {
    public final static String ID = makeID(InevitableForm.class.getSimpleName());

    public InevitableForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new InevitableFormPower(p, this.magicNumber), this.magicNumber));
        if(upgraded){
            this.addToBot(new WarpAction(secondMagic));
        }
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}
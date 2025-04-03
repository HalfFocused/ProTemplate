package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class NightTerrors extends AbstractEasyCard {
    public final static String ID = makeID(NightTerrors.class.getSimpleName());

    public NightTerrors() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new VulnerablePower(p, secondMagic, false), secondMagic));
        this.addToBot(new ApplyPowerAction(p,p, new GainStrengthPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
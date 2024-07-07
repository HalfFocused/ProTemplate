package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;
import code.powers.AuraPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static code.ModFile.makeID;

public class OppressiveAura extends AbstractEasyCard {
    public final static String ID = makeID(OppressiveAura.class.getSimpleName());

    public OppressiveAura() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        //baseBlock = block = 4;
        //turns
        baseMagicNumber = magicNumber = 3;
        //block per turn
        baseBlock = block = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AuraPower(p, magicNumber, block)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
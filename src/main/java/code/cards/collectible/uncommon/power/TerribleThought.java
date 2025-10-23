package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;
import code.powers.TerribleThoughtPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class TerribleThought extends AbstractEasyCard {
    public final static String ID = makeID(TerribleThought.class.getSimpleName());

    public TerribleThought() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TerribleThoughtPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}

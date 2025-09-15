package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Lucidity extends AbstractEasyCard {
    public final static String ID = makeID(Lucidity.class.getSimpleName());
    public Lucidity() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters){
            addToBot(new ApplyPowerAction(mon, p, new ForetoldPower(mon, magicNumber)));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
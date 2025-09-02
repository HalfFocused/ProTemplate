package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static code.ModFile.makeID;

public class Elude extends AbstractEasyCard {
    public final static String ID = makeID(Elude.class.getSimpleName());

    public Elude() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters){
            if(mon.getIntentBaseDmg() >= 0){
                addToBot(new ApplyPowerAction(mon, p, new WeakPower(mon, magicNumber, false), magicNumber));
            }
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}
package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.WitherPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeadFlesh extends AbstractEasyCard {
    public final static String ID = makeID(DeadFlesh.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, 5, 3

    public DeadFlesh() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(monster, p, new WitherPower(monster, this.magicNumber), magicNumber));
        }
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}
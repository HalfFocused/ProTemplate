package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LowVisibility extends AbstractEasyCard {
    public final static String ID = makeID("LowVisibility");
    // intellij stuff skill, ALL, uncommon, , , 10, 3, 1, 1

    public LowVisibility() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p,p,new WeakPower(p, magicNumber, false)));
        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
            this.addToBot(new GainBlockAction(mo, p, block));
            this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false)));
        }
    }

    public void upp() {
        upgradeBlock(4);
    }
}
package code.cards.collectible.uncommon.skill;

import code.actions.HollowFleshAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HollowFlesh extends AbstractEasyCard {
    public final static String ID = makeID("HollowFlesh");

    public HollowFlesh() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HollowFleshAction(p, block, this.freeToPlayOnce, this.energyOnUse));

    }

    public void upp() {
        upgradeBlock(3);
    }
}
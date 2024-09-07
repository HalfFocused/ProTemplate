package code.cards.collectible.rare.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.unique.MadnessAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BattleTempo extends AbstractEasyCard {
    public final static String ID = makeID("BattleTempo");
    // intellij stuff skill, self, rare, , , 8, 3, , 

    public BattleTempo() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new MadnessAction());
    }

    public void upp() {
        upgradeBlock(3);
    }
}
package code.cards.collectible.common.skill;

import basemod.cardmods.EtherealMod;
import code.actions.ApplyCardModAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FalteringGuard extends AbstractEasyCard {
    public final static String ID = makeID(FalteringGuard.class.getSimpleName());
    // intellij stuff skill, self, common, , , 8, 2, , 

    public FalteringGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyCardModAction(this, new EtherealMod()));
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
    }

    public void upp() {
        upgradeBlock(4);
    }
}
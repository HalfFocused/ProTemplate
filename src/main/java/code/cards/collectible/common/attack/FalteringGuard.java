package code.cards.collectible.common.attack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import code.actions.ApplyCardModAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FalteringGuard extends AbstractEasyCard {
    public final static String ID = makeID("FalteringGuard");
    // intellij stuff skill, self, common, , , 8, 2, , 

    public FalteringGuard() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 14;
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
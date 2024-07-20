package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.cards.tokens.DreamBlade;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fantasize extends AbstractEasyCard {
    public final static String ID = makeID(Fantasize.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public Fantasize() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new DreamBlade();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new DreamBlade(), 1, false, true));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Set;

public class FasterDiscardSpecificCardAction extends AbstractGameAction {
    private AbstractCard targetCard;
    private CardGroup group;

    public FasterDiscardSpecificCardAction(AbstractCard targetCardIn) {
        targetCard = targetCardIn;
    }

    @Override
    public void update() {
        this.group = AbstractDungeon.player.hand;
        if (this.group.contains(this.targetCard)) {
            this.group.moveToDiscardPile(this.targetCard);
            GameActionManager.incrementDiscard(false);
            this.targetCard.triggerOnManualDiscard();
        }
        isDone = true;
    }
}
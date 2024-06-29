package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class MemoryJogAction extends AbstractGameAction{
    private final int amount;
    public MemoryJogAction(int amountIn) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.amount = amountIn;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.player.exhaustPile.group);
            ArrayList<AbstractCard> mostExpensiveCards = new ArrayList<>();
            if (cards.size() == 0) {
                isDone = true;
                return;
            }

            int highestCost = 0;
            int lowestCost = Integer.MAX_VALUE;
            for (AbstractCard card : cards) {
                if (card.costForTurn > highestCost) {
                    highestCost = card.costForTurn;
                }

                if (card.costForTurn < lowestCost) {
                    lowestCost = card.costForTurn;
                }
            }

            outer:
            for (int i = highestCost; i >= lowestCost; i--) {
                for (int j = cards.size() - 1; j >= 0; j--) {
                    AbstractCard card = cards.get(j);
                    if (card.costForTurn == i) {
                        if(mostExpensiveCards.size() >= amount){
                            break outer;
                        }
                        mostExpensiveCards.add(card);
                    }
                }
            }

            for(AbstractCard card : mostExpensiveCards){
                AbstractDungeon.player.exhaustPile.removeCard(card);
                AbstractCard tmp = card.makeStatEquivalentCopy();
                tmp.purgeOnUse = true;
                this.addToTop(new NewQueueCardAction(tmp, true, false, true));
            }


            isDone = true;
        }

        this.tickDuration();
    }
}

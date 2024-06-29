package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DrawCheapestCardsAction extends AbstractGameAction{
    private final int amount;
    public DrawCheapestCardsAction(int amountIn) {
        this.actionType = ActionType.CARD_MANIPULATION;// 19
        this.duration = Settings.ACTION_DUR_MED;// 20
        this.amount = amountIn;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {// 26
            if (AbstractDungeon.player.hasPower("No Draw")) {// 79
                AbstractDungeon.player.getPower("No Draw").flash();// 80
            } else {
                ArrayList<AbstractCard> cards = new ArrayList<>(AbstractDungeon.player.drawPile.group);
                ArrayList<AbstractCard> cheapestCards = new ArrayList<>();
                if (cards.size() == 0) {
                    isDone = true;
                    return;
                }

                int highestCost = 0;
                for (AbstractCard card : cards) {
                    if (card.costForTurn > highestCost) {
                        highestCost = card.costForTurn;
                    }
                }

                outer:
                for (int i = 0; i <= highestCost; i++) {
                    for (int j = cards.size() - 1; j >= 0; j--) {
                        AbstractCard card = cards.get(j);
                        if (card.costForTurn == i) {
                            if(cheapestCards.size() >= amount){
                                break outer;
                            }
                            cheapestCards.add(card);
                        }
                    }
                }

                for (AbstractCard card : cheapestCards) {
                    AbstractDungeon.player.drawPile.moveToDeck(card, false);
                }

                this.addToTop(new DrawCardAction(cheapestCards.size()));
                isDone = true;
            }
        }

        this.tickDuration();
    }
}

package code.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FutureSelectCardsAction extends SelectCardsInHandAction {
    public FutureSelectCardsAction(int amount, String textForSelect, boolean anyNumber, boolean canPickZero, Predicate<AbstractCard> cardFilter, Consumer<List<AbstractCard>> callback) {
        super(amount, textForSelect, anyNumber, canPickZero, cardFilter, callback);
    }

    public void update() {
        super.update();
    }
}
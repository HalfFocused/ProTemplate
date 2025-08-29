//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FlashbackAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private Predicate<AbstractCard> filter;
    private Consumer<ArrayList<AbstractCard>> callback;

    public FlashbackAction(int numberOfCardsIn, Predicate<AbstractCard> filterIn, Consumer<ArrayList<AbstractCard>> callbackIn) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = this.startDuration = Settings.ACTION_DUR_FAST;
        player = AbstractDungeon.player;
        numberOfCards = numberOfCardsIn;
        filter = filterIn;
        callback = callbackIn;
    }

    public FlashbackAction(int numberOfCardsIn, Predicate<AbstractCard> filterIn) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = this.startDuration = Settings.ACTION_DUR_FAST;
        player = AbstractDungeon.player;
        numberOfCards = numberOfCardsIn;
        filter = filterIn;
        callback = null;
    }

    public FlashbackAction(int numberOfCardsIn) {
        actionType = ActionType.CARD_MANIPULATION;
        duration = this.startDuration = Settings.ACTION_DUR_FAST;
        player = AbstractDungeon.player;
        numberOfCards = numberOfCardsIn;
        filter = null;
        callback = null;
    }

    public void update() {
        if (this.duration == this.startDuration) {

            ArrayList<AbstractCard> matchingCards = new ArrayList<>();

            for(AbstractCard c : this.player.discardPile.group){
                if(filter == null || filter.test(c)){
                    matchingCards.add(c);
                }
            }

            if (!matchingCards.isEmpty() && this.numberOfCards > 0) {
                if (matchingCards.size() <= this.numberOfCards) {

                    //equal or less matches than choices, move all automatically

                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>(matchingCards);

                    for(AbstractCard c : cardsToMove){
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            this.player.discardPile.removeCard(c);
                        }

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {

                    CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for(AbstractCard c : matchingCards){
                        choices.addToBottom(c);
                    }

                    if (this.numberOfCards == 1) {
                        AbstractDungeon.gridSelectScreen.open(choices, this.numberOfCards, TEXT[0], false);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(choices, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards){
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        this.player.discardPile.removeCard(c);
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }
                if(callback != null){
                    callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
                }

                for(AbstractCard c : this.player.discardPile.group){
                    c.unhover();
                    c.target_x = (float)CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                for(AbstractCard c : this.player.hand.group){
                    c.applyPowers();
                }
            }
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}

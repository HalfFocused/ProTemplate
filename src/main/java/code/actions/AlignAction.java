package code.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;
import java.util.Iterator;

public class AlignAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;

    public AlignAction(int numberOfCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }
    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.drawPile.isEmpty() && this.numberOfCards > 0) {

                if (this.player.drawPile.size() <= this.numberOfCards) {

                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>(this.player.drawPile.group);

                    for(AbstractCard c : cardsToMove) {
                        /*
                        Find where the card used to be and put a Dazed there.
                         */
                        for(int i = 0; i < this.player.drawPile.size(); i++){
                            AbstractCard inPos = this.player.drawPile.group.get(i);
                            if(inPos.uuid.equals(c.uuid)){
                                this.player.drawPile.group.add(i, new Dazed());
                                break;
                            }
                        }

                        if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                            this.player.drawPile.moveToDiscardPile(c);
                            this.player.createHandIsFullDialog();
                        } else {
                            this.player.drawPile.moveToHand(c, this.player.drawPile);
                        }
                    }

                    this.isDone = true;
                } else {
                    CardGroup temp = new CardGroup(CardGroupType.UNSPECIFIED);

                    for(AbstractCard c : this.player.drawPile.group) {
                        temp.addToTop(c);
                    }

                    temp.sortAlphabetically(true);
                    temp.sortByRarityPlusStatusCardType(false);
                    if (this.numberOfCards == 1) {
                        AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], false);
                    }else {
                        AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for(AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {

                    /*
                    Find where the card used to be and put a Dazed there.
                     */
                    for(int i = 0; i < this.player.drawPile.size(); i++){
                        AbstractCard inPos = this.player.drawPile.group.get(i);
                        if(inPos.uuid.equals(c.uuid)){
                            this.player.drawPile.group.add(i, new Dazed());
                            break;
                        }
                    }

                    if (this.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                        this.player.drawPile.moveToDiscardPile(c);
                        this.player.createHandIsFullDialog();
                    } else {
                        this.player.drawPile.moveToHand(c, this.player.drawPile);
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}

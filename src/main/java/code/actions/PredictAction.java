package code.actions;

import basemod.BaseMod;
import code.relics.PairOfGears;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.lwjgl.Sys;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PredictAction extends AbstractGameAction {

    private static int foundCards = 0;

    public static ArrayList<AbstractCard> cardsAcceptedSoFar = new ArrayList<>();
    private int num;
    private Predicate<AbstractCard> quality;
    private boolean first;
    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
        first = true;
    }

    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn, boolean isFirst) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
        first = isFirst;
    }

    public void update(){
        if(first) {
            foundCards = 0;
            cardsAcceptedSoFar.clear();
            if(AbstractDungeon.player.drawPile.isEmpty() && !AbstractDungeon.player.discardPile.isEmpty()) {
                this.addToTop(new DrawCardAction(1, new PredictAction(num, quality, true)));
                this.addToTop(new EmptyDeckShuffleAction());
                isDone = true;
                return;
            }else{
                CardGroup drawPileReplica = new CardGroup(AbstractDungeon.player.drawPile, CardGroup.CardGroupType.UNSPECIFIED);
                int miniFound = 0;
                int totalChecked = 0;
                ArrayList<AbstractCard> toAddToLimbo = new ArrayList<>();
                for(int i = 0; miniFound < num && i < drawPileReplica.size() && AbstractDungeon.player.hand.size() + miniFound < BaseMod.MAX_HAND_SIZE; i++){
                    AbstractCard test = drawPileReplica.getNCardFromTop(i).makeSameInstanceOf();
                    toAddToLimbo.add(test);
                    if(quality.test(test)){
                        cardsAcceptedSoFar.add(test);
                        miniFound++;
                    }
                    totalChecked++;
                }
                if(totalChecked == miniFound){
                    this.addToTop(new DrawCardAction(num));
                }else{
                    for(AbstractCard c : toAddToLimbo){
                        c.beginGlowing();
                        c.glowColor = quality.test(c) ? Color.GREEN.cpy() : Color.RED.cpy();
                        AbstractDungeon.player.limbo.addToBottom(c);
                        c.target_x = ((float)Settings.WIDTH / 2.0F) - (AbstractDungeon.player.limbo.size() * (240 * Settings.xScale));
                        c.target_y = (float)Settings.HEIGHT / 2.0F;
                        c.targetAngle = 0.0F;
                        c.drawScale = 0.25F;
                        c.targetDrawScale = 0.75F;
                        c.applyPowers();
                    }
                    this.addToTop(new DrawCardAction(1, new PredictAction(num, quality, false)));
                    this.addToTop(new ForceWaitAction(0.5f));
                }
                isDone = true;
                return;
            }
        }
        AbstractCard c = DrawCardAction.drawnCards.get(0);
        boolean keep = quality.test(c);
        if (keep) {
            foundCards++;
        }
        if (foundCards < num && !AbstractDungeon.player.drawPile.isEmpty() && AbstractDungeon.player.hand.size() + (keep ? 0 : -1) < BaseMod.MAX_HAND_SIZE) {
            addToTop(new DrawCardAction(1, new PredictAction(num, quality, false)));
        } else {
            foundCards = 0;
            cardsAcceptedSoFar.clear();
        }
        if (!keep) {
            addToTop(new FasterDiscardSpecificCardAction(c));
        }
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.limbo.removeTopCard();
                for (AbstractCard limboCard : AbstractDungeon.player.limbo.group) {
                    limboCard.target_x += (240 * Settings.xScale);
                    limboCard.glowColor = quality.test(limboCard) ? Color.GREEN.cpy() : Color.RED.cpy();
                }
                isDone = true;
            }
        });
        isDone = true;
    }

    public static ArrayList<AbstractCard> getSimulatedHand(){
        ArrayList<AbstractCard> toCheck = new ArrayList<>(AbstractDungeon.player.hand.group);
        toCheck.addAll(cardsAcceptedSoFar);
        return toCheck;
    }
}

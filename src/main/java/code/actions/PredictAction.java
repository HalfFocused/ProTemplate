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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.CardTrailEffect;
import org.lwjgl.Sys;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PredictAction extends AbstractGameAction {

    private static int foundCards = 0;

    private static ArrayList<AbstractCard> cardsAcceptedSoFar = new ArrayList<>();

    public static ArrayList<AbstractCard> resultingCards = new ArrayList<>();
    private int num;

    AbstractGameAction followUpAction = null;
    private Predicate<AbstractCard> quality;
    private boolean first;
    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
        first = true;
    }

    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn, AbstractGameAction followUpIn) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
        first = true;
        followUpAction = followUpIn;
    }

    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn, AbstractGameAction followUpIn, boolean isFirst) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
        first = isFirst;
        followUpAction = followUpIn;
    }

    public void update(){
        if(first) {
            foundCards = 0;
            cardsAcceptedSoFar.clear();
            resultingCards.clear();
            if(AbstractDungeon.player.drawPile.isEmpty() && !AbstractDungeon.player.discardPile.isEmpty()) {
                this.addToTop(new PredictAction(num, quality, followUpAction));
                this.addToTop(new EmptyDeckShuffleAction());
            }else{
                int miniFound = 0;
                int totalChecked = 0;
                ArrayList<AbstractCard> toAddToLimbo = new ArrayList<>();
                for(int i = 0; miniFound < num && i < AbstractDungeon.player.drawPile.size() && AbstractDungeon.player.hand.size() + miniFound < BaseMod.MAX_HAND_SIZE; i++){
                    AbstractCard test = AbstractDungeon.player.drawPile.getNCardFromTop(i).makeSameInstanceOf();
                    toAddToLimbo.add(test);
                    if(quality.test(test)){
                        cardsAcceptedSoFar.add(AbstractDungeon.player.drawPile.getNCardFromTop(i));
                        miniFound++;
                    }
                    totalChecked++;
                }
                if(totalChecked == miniFound){
                    foundCards = 0;
                    resultingCards = new ArrayList<>(cardsAcceptedSoFar);
                    if(followUpAction != null){
                        this.addToTop(followUpAction);
                        this.addToTop(new ForceWaitAction(0.35f));
                    }
                    cardsAcceptedSoFar.clear();
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
                    this.addToTop(new DrawCardAction(1, new PredictAction(num, quality, followUpAction, false)));
                    this.addToTop(new ForceWaitAction(0.5f));
                }
            }
            isDone = true;
            return;
        }
        AbstractCard c = DrawCardAction.drawnCards.get(0);
        boolean keep = quality.test(c);
        if (keep) {
            foundCards++;
            resultingCards.add(c);
        }
        if (foundCards < num && !AbstractDungeon.player.drawPile.isEmpty() && AbstractDungeon.player.hand.size() + (keep ? 0 : -1) < BaseMod.MAX_HAND_SIZE) {
            addToTop(new DrawCardAction(1, new PredictAction(num, quality, followUpAction, false)));
        } else {
            foundCards = 0;
            cardsAcceptedSoFar.clear();
            if(followUpAction != null && !resultingCards.isEmpty()){
                this.addToTop(followUpAction);
            }
        }
        if (!keep) {
            addToTop(new FasterDiscardSpecificCardAction(c));
        }
        c.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.limbo.removeTopCard();
                for (AbstractCard limboCard : AbstractDungeon.player.limbo.group) {
                    limboCard.target_x += (240 * Settings.xScale);
                    limboCard.target_y = (float)Settings.HEIGHT / 2.0F;
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

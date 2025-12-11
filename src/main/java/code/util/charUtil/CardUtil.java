package code.util.charUtil;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.actions.DisplayCardAction;
import code.cards.collectible.rare.attack.ViciousCycle;
import code.powers.TimeSlowPower;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.function.Predicate;

public class CardUtil {

    public static ArrayList<AbstractCard> cardsPlayedLastTurn = new ArrayList<>();

    public static ArrayList<AbstractCard> cardsDrawnWithForsakeThisTurn = new ArrayList<>();

    private static AbstractGameAction.AttackEffect lastAttackEffect = null;

    public static boolean cardExhaustedThisTurn = false;
    public static boolean theSecondDreamActivatedThisTurn = false;
    public static boolean theSecondDreamActivatedLastTurn = false;

    public static void forgetCard(ForgetCard card){
        if(card instanceof AbstractCard) {
            ((AbstractCard) card).applyPowers();
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    card.onForget();
                    isDone = true;
                }
            });
            AbstractCard displayCard = ((AbstractCard) card).makeStatEquivalentCopy();
            displayCard.current_x = ((AbstractCard) card).current_x;
            displayCard.current_y = ((AbstractCard) card).current_y;
            AbstractDungeon.actionManager.addToTop(new UnlimboAction(displayCard));
            AbstractDungeon.actionManager.addToTop(new DisplayCardAction(displayCard));
        }
    }

    public static boolean hasEtherealCardInHand(AbstractPlayer p){
        for(AbstractCard c : p.hand.group){
            if (c.isEthereal) {
                return true;
            }
        }
        return false;
    }

    public static int etherealCardsPlayedThisTurn(){
        int played = 0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(c.isEthereal) played++;
        }
        return played;
    }

    public static int etherealCardsInHand(){
        int inHand = 0;
        for(AbstractCard c : AbstractDungeon.player.hand.group){
            if(c.isEthereal) inHand++;
        }
        return inHand;
    }

    public static boolean isTimeStopped(){
        return CardCrawlGame.isInARun() && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && (theSecondDreamActivatedThisTurn || (theSecondDreamActivatedLastTurn && !AbstractDungeon.actionManager.turnHasEnded));
    }

    public static boolean isTimeSlowed(){
        return CardCrawlGame.isInARun() && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hasPower(TimeSlowPower.POWER_ID);
    }

    public static AbstractGameAction.AttackEffect randomSlash(){

        if(lastAttackEffect == AbstractGameAction.AttackEffect.SLASH_DIAGONAL){
            switch (MathUtils.random(3)){
                case 1:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
                    return lastAttackEffect;
                case 2:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
                    return lastAttackEffect;
                case 3:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
                    return lastAttackEffect;
            }
        }else if(lastAttackEffect == AbstractGameAction.AttackEffect.SLASH_VERTICAL){
            switch (MathUtils.random(3)){
                case 1:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
                    return lastAttackEffect;
                case 2:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
                    return lastAttackEffect;
                case 3:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
                    return lastAttackEffect;
            }
        }else if(lastAttackEffect == AbstractGameAction.AttackEffect.SLASH_HEAVY){
            switch (MathUtils.random(3)){
                case 1:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
                    return lastAttackEffect;
                case 2:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
                    return lastAttackEffect;
                case 3:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
                    return lastAttackEffect;
            }
        }else if(lastAttackEffect == AbstractGameAction.AttackEffect.SLASH_HORIZONTAL){
            switch (MathUtils.random(3)){
                case 1:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
                    return lastAttackEffect;
                case 2:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
                    return lastAttackEffect;
                case 3:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
                    return lastAttackEffect;
            }
        }else{
            switch (MathUtils.random(4)){
                case 1:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
                    return lastAttackEffect;
                case 2:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
                    return lastAttackEffect;
                case 3:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
                    return lastAttackEffect;
                case 4:
                    lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
                    return lastAttackEffect;
            }
        }
        lastAttackEffect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
        return lastAttackEffect;
    }

    public static boolean isPowerTurnBased(AbstractPower power){
        return ReflectionHacks.getPrivate(power, AbstractPower.class, "isTurnBased");
    }

    public static void flashback(String cardID){
        for(AbstractCard card : AbstractDungeon.player.discardPile.group){
            if(card.cardID.equals(cardID)){
                AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(card));
            }
        }
    }
}
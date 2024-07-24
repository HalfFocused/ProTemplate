package code.util.charUtil;

import basemod.helpers.CardModifierManager;
import code.actions.DisplayCardAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;
import java.util.function.Predicate;

public class CardUtil {

    public static ArrayList<AbstractCard> cardsPlayedLastTurn = new ArrayList<>();

    public static ArrayList<CardStreak> cardStreaks = new ArrayList<>();

    public static int queuedWarps  = 0;

    @SpireEnum
    public static AbstractCard.CardRarity MYTHIC;

    @SpireEnum
    public static AbstractCard.CardRarity DEBILITATED;

    public static boolean isMythic(AbstractCard card){
        return card.rarity == MYTHIC;
    }

    public static boolean isDebilitated(AbstractCard card){
        return card.rarity == DEBILITATED;
    }

    public static boolean isCommon(AbstractCard card){
        return card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.SPECIAL || card.rarity == AbstractCard.CardRarity.BASIC || card.rarity == AbstractCard.CardRarity.CURSE;
    }

    public static boolean isUncommon(AbstractCard card){
        return card.rarity == AbstractCard.CardRarity.UNCOMMON;
    }

    public static boolean isRare(AbstractCard card){
        return card.rarity == AbstractCard.CardRarity.RARE;
    }

    public static ArrayList<AbstractCard.CardRarity> uniqueRaritiesInHand() {
        ArrayList<AbstractCard.CardRarity> rarities = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (!rarities.contains(getAdjustedRarity(card))) {
                rarities.add(getAdjustedRarity(card));
            }
        }
        return rarities;
    }

    public static AbstractCard.CardRarity getAdjustedRarity(AbstractCard card){
        if(isCommon(card)){
            return AbstractCard.CardRarity.COMMON;
        }else{
            return card.rarity;
        }
    }

    public static int getRarityNumber(AbstractCard card){
        if(isDebilitated(card)){
            return 0;
        }else if(isCommon(card)){
            return 1;
        }else if(isUncommon(card)){
            return 2;
        }else if(isRare(card)){
            return 3;
        }else if(isMythic(card)){
            return 4;
        }
        System.out.println("Something is wrong! -> CardUtil#getRarityNumber");
        return -1;
    }

    public static void forgetCard(ForgetCard card){
        if(card instanceof AbstractCard) {
            ((AbstractCard) card).applyPowers();
            card.onForget();
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

    public static CardGroup filteredRandomCard(Predicate<AbstractCard> filter) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(filter).forEach(c -> retVal.addToTop(c.makeCopy()));
        return retVal;
    }


    public static class CardStreak{
        public String name;
        public int streak;
        public CardStreak(String nameIn, int streakIn){
            name = nameIn;
            streak = streakIn;
        }
    }
}
package code.patches;

import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

@SpirePatch(
        clz= GameActionManager.class,
        method="callEndOfTurnActions"
)
public class RiotEndOfTurnPatch {
    public static void Prefix(GameActionManager __instance)
    {
        /*
            Reset the streaks of any cards with a streak that were not played this turn.
         */
        for(CardUtil.CardStreak pairing : CardUtil.cardStreaks){
            boolean playedThisTurn = false;
            for(AbstractCard cardPlayedThisTurn : __instance.cardsPlayedThisTurn){
                if (pairing.name.equals(cardPlayedThisTurn.name)) {
                    playedThisTurn = true;
                    break;
                }
            }
            if(!playedThisTurn){
                pairing.streak = 0;
            }
        }
        /*
           For each card played this turn, increment the streak with that name or create a streak for that name.
         */
        ArrayList<String> cardsAlreadyIncremented = new ArrayList<>();
        for(AbstractCard card : __instance.cardsPlayedThisTurn){
            if(!cardsAlreadyIncremented.contains(card.name)) {
                CardUtil.CardStreak streak = null;
                for (CardUtil.CardStreak streakIterator : CardUtil.cardStreaks) {
                    if (card.name.equals(streakIterator.name)) {
                        streak = streakIterator;
                    }
                }
                if (streak == null) {
                    CardUtil.cardStreaks.add(new CardUtil.CardStreak(card.name, 1));
                } else {
                    streak.streak++;
                }
                cardsAlreadyIncremented.add(card.name);
            }
        }
        CardUtil.cardsPlayedLastTurn.clear();
        CardUtil.cardsDrawnWithForsakeThisTurn.clear();
        CardUtil.cardsPlayedLastTurn.addAll(__instance.cardsPlayedThisTurn);

    }

    public static void Postfix(GameActionManager __instance){

    }


}

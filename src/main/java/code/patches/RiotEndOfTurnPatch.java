package code.patches;

import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import javax.smartcardio.Card;
import java.util.ArrayList;

@SpirePatch(
        clz= GameActionManager.class,
        method="callEndOfTurnActions"
)
public class RiotEndOfTurnPatch {
    public static void Prefix(GameActionManager __instance) {
        CardUtil.cardsPlayedLastTurn.clear();
        CardUtil.cardsDrawnWithForsakeThisTurn.clear();
        CardUtil.cardsPlayedLastTurn.addAll(__instance.cardsPlayedThisTurn);
    }
}
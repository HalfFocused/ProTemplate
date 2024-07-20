package code.patches;

import basemod.helpers.CardModifierManager;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

@SpirePatch(
        clz= GameActionManager.class,
        method="callEndOfTurnActions"
)
public class RiotEndOfTurnPatch {
    public static void Prefix(GameActionManager __instance)
    {
        CardUtil.cardsPlayedLastTurn.clear();
        CardUtil.cardsPlayedLastTurn.addAll(__instance.cardsPlayedThisTurn);

        /*
        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
                CardModifierManager.
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
            }
        }

        var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
            }
        }
         */
    }

    public static void Postfix(GameActionManager __instance){

    }


}

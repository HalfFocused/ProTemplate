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


        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
            /*
            if(CardUtil.isMythic(c)){
                AbstractDungeon.actionManager.addToTop(new DenounceAction(c));
            }
            if(CardUtil.isDebilitated(c)){
                AbstractDungeon.actionManager.addToTop(new ExaltAction(c));
            }
             */
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
            }
            /*
            if(CardUtil.isMythic(c)){
                AbstractDungeon.actionManager.addToTop(new DenounceAction(c));
            }
            if(CardUtil.isDebilitated(c)){
                AbstractDungeon.actionManager.addToTop(new ExaltAction(c));
            }
             */
        }

        var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (CardModifierManager.hasModifier(c, "ephemeral")) {
                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
            //Mythic and debilitated cards in the hand are handled in ForgetPatch
        }
    }

    public static void Postfix(GameActionManager __instance){

    }
}

package code.patches;

import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.Iterator;

@SpirePatch(
        clz=AbstractCard.class,
        method="makeStatEquivalentCopy"
)
public class MakeCopyPatch {

    @SpireInsertPatch(
            loc=983,
            localvars = {"card"}
    )
    /*
    Patch making a copy of a card to also change the rarity if the card's rarity isn't it's default rarity.
     */
    public static void Insert(AbstractCard __instance, @ByRef AbstractCard[] card) {
        if(__instance.rarity != CardUtil.defaultRarity(__instance)) {
            card[0].rarity = __instance.rarity;
        }
    }
}

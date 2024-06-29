package code.patches;

import code.util.charUtil.AtBlockModifyRelic;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

@SpirePatch(
        clz=AbstractCard.class,
        method="applyPowersToBlock"
)
public class AtBlockModifyPatch {

    @SpireInsertPatch(
            rloc=3,
            localvars = {"tmp"}
    )
    public static void Insert(AbstractCard __instance, @ByRef float[] tmp) {
        AbstractPlayer player = AbstractDungeon.player;
        Iterator iterator;
        AbstractRelic relic;

        for (iterator = AbstractDungeon.player.relics.iterator(); iterator.hasNext(); tmp[0] = (relic instanceof AtBlockModifyRelic) ? ((AtBlockModifyRelic) relic).atBlockModify(tmp[0], __instance) : tmp[0]) {
            relic = (AbstractRelic) iterator.next();
        }
    }
}

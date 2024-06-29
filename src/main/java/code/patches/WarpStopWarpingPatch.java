package code.patches;

import code.actions.WarpAction;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz= GameActionManager.class,
        method="getNextAction"
)
public class WarpStopWarpingPatch {

    @SpireInsertPatch(
            loc=471
    )
    public static void Insert(GameActionManager __instance)
    {
        if(CardUtil.queuedWarps > 0) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                public void update() {
                    CardUtil.queuedWarps--;
                    if(CardUtil.queuedWarps !=0) {
                        this.addToTop(new WarpAction(CardUtil.queuedWarps));
                    }
                    this.isDone = true;
                }
            });
        }
    }
}

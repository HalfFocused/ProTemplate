package code.patches.warp;

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

    /*
        I don't know where. but somewhere in the crazy mess of StS turn code is a point where the action queue clears,
        making queuing multiple warps unreliable. doing things with an internal queued warp counter and adding another
        queue after the end of the Warp is *a* way to circumvent this (as for if it's the best way? no clue)
     */
    @SpireInsertPatch(
            loc=471
    )
    public static void Insert(GameActionManager __instance)
    {
        if(CardUtil.queuedWarps > 0) {
            AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
                public void update() {
                    CardUtil.queuedWarps--;
                    if(CardUtil.queuedWarps != 0) {
                        this.addToBot(new WarpAction(CardUtil.queuedWarps));
                    }
                    this.isDone = true;
                }
            });
        }
    }
}
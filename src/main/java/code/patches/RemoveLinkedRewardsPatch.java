package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.rewards.RewardItem;

@SpirePatch(
        clz= RewardItem.class,
        method="claimReward"
)
public class RemoveLinkedRewardsPatch {
    @SpireInsertPatch(
            loc=312
    )
    public static SpireReturn<Boolean> checkRewardDone(RewardItem __instance)
    {
        if (__instance.ignoreReward) {
            return SpireReturn.Return(true);
        }
        return SpireReturn.Continue();
    }

    @SpireInsertPatch(
            locs={322, 325}
    )
    public static void setOtherRewardsDone(RewardItem __instance)
    {
        if (__instance.relicLink != null) {
            __instance.relicLink.isDone = true;
            __instance.relicLink.ignoreReward = true;
        }
    }

}
package code.patches;

import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractPlayer.class,
        method="applyStartOfCombatPreDrawLogic"
)
public class RiotStartOfCombatPatch {
    public static void Prefix(AbstractPlayer __instance)
    {
        CardUtil.cardStreaks.clear();
        CardUtil.cardsPlayedLastTurn.clear(); //Even though Recurring Dream working across combats was cool
        CardUtil.queuedWarps = 0; //In case the previous combat ends with warps queued.
        CardUtil.warpsThisCombat = 0;
    }
}

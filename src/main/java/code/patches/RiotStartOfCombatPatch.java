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
        CardUtil.cardsPlayedLastTurn.clear(); //Even though Recurring Dream working across combats was cool
    }
}

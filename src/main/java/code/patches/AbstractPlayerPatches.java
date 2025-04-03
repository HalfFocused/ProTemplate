package code.patches;

import code.util.charUtil.mods.FlashbackModifier;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;


public class AbstractPlayerPatches {

    @SpirePatch(
        clz=AbstractPlayer.class,
        method="damage"
    )
    static class canUsePatch{
        @SpireInsertPatch(
            loc=1793,
            localvars = {"damageAmount"}
        )
        public static void Insert(AbstractPlayer __instance, DamageInfo info, int damageAmount) {
            if (info.owner != null && info.owner != __instance && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
                FlashbackModifier.flashback(FlashbackModifier.NEVER_ENOUGH);
            }
        }
    }
}

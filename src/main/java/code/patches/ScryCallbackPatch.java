package code.patches;

import code.actions.ScryCallbackAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


/**
 * Created by Jedi#3970:
 * https://github.com/Jedi515/sts-jedi/blob/6fe1abcf796567034324b55d276bc8554842cc47/src/mod/jedi/actions/ScryCallbackAction.java#L9
 */
@SpirePatch(clz = ScryAction.class, method = "update")
public class ScryCallbackPatch {
    @SpireInsertPatch(
            loc=64
    )
    public static void InsertCallback(ScryAction __instance)
    {
        if (__instance instanceof ScryCallbackAction)
        {
            ((ScryCallbackAction)__instance).callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
        }
    }
}

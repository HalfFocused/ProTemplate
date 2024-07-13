package code.patches.patches;

import code.relics.ShatteredGlass;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;

@SpirePatch(
        clz=AbstractRoom.class,
        method="update"
)
public class ShatteredGlassPatch
{
    @SpireInsertPatch(
            locator=Locator.class
    )
    public static void Insert(AbstractRoom __instance)
    {
        AbstractRelic shatteredGlass = AbstractDungeon.player.getRelic(ShatteredGlass.ID);
        if (shatteredGlass != null) {
            shatteredGlass.onTrigger();
            AbstractDungeon.combatRewardScreen.setupItemReward();
        }
    }

    private static class Locator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "loading_post_combat");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{found[found.length - 1]};
        }
    }
}

package code.patches;

import basemod.ReflectionHacks;
import code.TheDisplaced;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;

import java.text.NumberFormat;
import java.util.Locale;

@SpirePatch2(
    clz=SpireHeart.class,
    method="buttonEffect"
)
public class SpireHeartPatch {

    @SpireInsertPatch(locator = InDeathCaseLocator.class)
    public static void LocalCase(SpireHeart __instance) {
        if(AbstractDungeon.player instanceof TheDisplaced){
            long globalDamageDealt = ReflectionHacks.getPrivate(__instance, SpireHeart.class, "globalDamageDealt");
            String[] eventText = ReflectionHacks.getPrivate(__instance, SpireHeart.class, "DESCRIPTIONS");

            long totalDamageDealt = ReflectionHacks.getPrivate(__instance, SpireHeart.class, "totalDamageDealt");
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            if(globalDamageDealt <= 0L){
                __instance.roomEventText.updateBodyText(eventText[3] + numberFormat.format(totalDamageDealt) + eventText[4] + TheDisplaced.getHeartFailureText());
            }else{
                __instance.roomEventText.updateBodyText(eventText[3] + numberFormat.format(totalDamageDealt) + eventText[4] + eventText[5] + numberFormat.format(globalDamageDealt) + eventText[6] + TheDisplaced.getHeartFailureText());
            }
        }
    }

    private static class InDeathCaseLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher("com.megacrit.cardcrawl.events.RoomEventDialog", "updateDialogOption");
            int[] allLocations = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{allLocations[3]};
        }
    }
}

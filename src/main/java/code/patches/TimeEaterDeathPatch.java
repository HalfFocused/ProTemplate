package code.patches;

import code.ModFile;
import code.util.charUtil.AtBlockModifyRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

@SpirePatch2(clz= TimeEater.class, method = "die")
public class TimeEaterDeathPatch {

    @SpireInsertPatch(
            locator=Locator.class
    )
    public static void updateRecord(TimeEater __instance) {
        SpireConfig record = ModFile.getTimeEaterRecord();
        record.setInt("wins",record.getInt("wins") + 1);
        record.setBool("lastRunDied", false);
        try {
            record.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(TimeEater.class, "onBossVictoryLogic");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}

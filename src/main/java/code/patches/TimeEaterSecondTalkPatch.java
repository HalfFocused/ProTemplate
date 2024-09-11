package code.patches;

import code.ModFile;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.io.IOException;
import java.util.ArrayList;

@SpirePatch2(clz= TimeEater.class, method = "takeTurn")
public class TimeEaterSecondTalkPatch {

    @SpireInsertPatch(
        loc=107
    )
    public static void secondDialog(TimeEater __instance, boolean ___firstTurn) {
        if(!___firstTurn && TimeEaterFieldInject.secondDialog.get(__instance)){
            //secondDialog
            if(__instance.nextMove != 5) { //skip the turn dialog if someone gets it below half health, cause its gonna shout instead
                AbstractDungeon.actionManager.addToBottom(new TalkAction(__instance, TimeEaterDialogPatch.dialogSequence[1], 0.5F, 2.0F));
            }
            TimeEaterFieldInject.secondDialog.set(__instance, false);
        }
    }
}

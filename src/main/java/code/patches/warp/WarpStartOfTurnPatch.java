package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;


@SpirePatch2(clz= AbstractRoom.class, method = "endTurn")
public class WarpStartOfTurnPatch {

    /*
        none of the actions queued in this method are good for us. let's just not!
        in all seriousness, this patch removes:

        -end of turn discard
        -clearing the card queue
        -the end turn action (the monster turn banner)
        -an annoying 1.2 second pause
     */
    static int index = 0;

    @SpireInstrumentPatch
    public static ExprEditor DiscardOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(GameActionManager.class.getName()) && m.getMethodName().equals("addToBottom")) {
                    index++;

                    if (index == 1 || index == 2 || index == 3) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? new com.megacrit.cardcrawl.actions.utility.WaitAction(0f) : $proceed($$));}");
                    }
                }
            }
        };
    }
}
package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

import java.util.ArrayList;


@SpirePatch2(clz= GameActionManager.class, method = "getNextAction")
public class GetNextActionPatch {

    /*
        Stop the player from drawing their hand for turn by instrument patching the gameHandSize field access to
        be zero if there is a warp queued. :3
     */
    @SpireInstrumentPatch
    public static ExprEditor CardDrawInstrumentPatch()
    {
        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                if(f != null) {
                    if (f.getClassName().equals(AbstractPlayer.class.getName()) && f.isReader() && f.getFieldName().equals("gameHandSize"))
                        f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? 0 : $proceed($$));}");
                }
            }
        };
    }

    /*
        simple stuff. Prevent the player from losing block on a Warp. next question.
     */
    @SpireInstrumentPatch
    public static ExprEditor LoseBlockOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractPlayer.class.getName()) && m.getMethodName().equals("loseBlock")) {
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? $proceed(0, false) : $proceed($$));}");
                }
            }
        };
    }
}
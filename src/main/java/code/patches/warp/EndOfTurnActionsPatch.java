package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;


@SpirePatch2(clz= GameActionManager.class, method = "callEndOfTurnActions")
public class EndOfTurnActionsPatch {

    /*
        Warp is designed to not effect relics.
        This patch replaces that pesky "applyEndOfTurnRelics" call with nothing if there is a warp queued. not bad.
     */
    @SpireInstrumentPatch
    public static ExprEditor EndOfTurnRelicsOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractRoom.class.getName()) && m.getMethodName().equals("applyEndOfTurnRelics")) {
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? \"\" : $proceed($$));}");
                }
            }
        };
    }
}
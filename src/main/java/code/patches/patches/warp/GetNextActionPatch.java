package code.patches.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;


@SpirePatch2(clz= GameActionManager.class, method = "getNextAction")
public class GetNextActionPatch {

    @SpireInstrumentPatch
    public static ExprEditor CardDrawInstrumentPatch()
    {
        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                if(f != null) {
                    if (f.getClassName().equals(AbstractPlayer.class.getName()) && f.getFieldName().equals("gameHandSize"))
                        f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? 0 : $proceed($$));}");
                }
            }
        };
    }

    static int index = 0;

    @SpireInstrumentPatch
    public static ExprEditor EndTurnInstrumentPatch()
    {
        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                if (f.getFieldName().equals("skipMonsterTurn") && f.isReader()) {
                    index++;
                    if(index == 2) {
                        f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? false : $proceed($$));}");
                    }
                }
            }
        };
    }

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

    @SpireInstrumentPatch
    public static ExprEditor NoRelicOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractPlayer.class.getName()) && m.getMethodName().equals("applyStartOfTurnRelics")) {
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? \"\" : $proceed($$));}");
                }else if(m.getClassName().equals(AbstractPlayer.class.getName()) && m.getMethodName().equals("applyStartOfTurnPostDrawRelics")){
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? \"\" : $proceed($$));}");
                }
            }
        };
    }
}

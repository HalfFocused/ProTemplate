package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch2(clz= AbstractPlayer.class, method = "applyStartOfTurnPostDrawRelics")
public class WarpStartOfTurnPostDrawRelicsPatch {
    /*
        Why do I let this method even get called instead of patching out where 'applyStartOfTurnPostDrawRelics' is called?
        So glad you asked!
        This is where the BaseMod hook for start of turn post draw is, that's why.
        patching it this way is necessary to support modded hooks.
     */
    @SpireInstrumentPatch
    public static ExprEditor NoRelicOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractRelic.class.getName()) && m.getMethodName().equals("atTurnStartPostDraw")) {
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? \"\" : $proceed($$));}");
                }
            }
        };
    }
}
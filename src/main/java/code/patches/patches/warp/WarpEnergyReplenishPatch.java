package code.patches.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch2(clz= PlayerTurnEffect.class, method = SpirePatch.CONSTRUCTOR)
public class WarpEnergyReplenishPatch {

    @SpireInstrumentPatch
    public static ExprEditor EnergyReplenishOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(EnergyManager.class.getName()) && m.getMethodName().equals("recharge")) {
                        m.replace("{if (!(code.util.charUtil.CardUtil.queuedWarps > 0)) $proceed($$);}");
                }
            }
        };
    }
}

package code.patches.warp;

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

    /*
        Did you know energy replenishing happens in the PLAYER TURN BANNER VISUAL EFFECT??!?!???!
        I HATE IT HERE
        HATE. HATE.
        Anyway, if we're warping we don't want to replenish energy, so I instrument patch occurrences of the
        recharge method with a little if statement around it.
     */
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
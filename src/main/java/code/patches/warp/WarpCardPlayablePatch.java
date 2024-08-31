package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;


@SpirePatch2(clz= AbstractCard.class, method = "hasEnoughEnergy")
public class WarpCardPlayablePatch {

    /*
        Make sure cards can be played and queued during warps by replacing any attempt
         to access turnHasEnded with false when a warp is queued. important for Entropy (rare power).
     */
    @SpireInstrumentPatch
    public static ExprEditor MonsterEndOfTurnOverride()
    {

        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                if(f.getFieldName().equals("turnHasEnded") && f.isReader()) {
                    f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? false : $proceed($$));}");
                }
            }
        };

    }
}
package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;


@SpirePatch2(clz= AbstractCard.class, method = "hasEnoughEnergy")
public class WarpCardPlayablePatch {

    @SpireInstrumentPatch
    public static ExprEditor MonsterEndOfTurnOverride()
    {

        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                if(f.getFieldName().equals("turnHasEnded")) {
                    f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? false : $proceed($$));}");
                }
            }
        };

    }
}

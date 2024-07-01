package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;


@SpirePatch2(clz= MonsterGroup.class, method = "applyEndOfTurnPowers")
public class WarpMonsterPatch {

    static int index = 0;
    @SpireInstrumentPatch
    public static ExprEditor MonsterEndOfTurnOverride()
    {

        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                //if we're warping. replace any check if the monster is dying with yes, so powers don't apply.
                //reality can be whatever I want it to be >:3
                if(f.getFieldName().equals("isDying")) {
                    f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? true : $proceed($$));}");
                }
            }
        };

    }
}

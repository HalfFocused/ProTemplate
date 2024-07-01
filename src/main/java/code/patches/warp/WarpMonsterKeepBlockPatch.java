package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


@SpirePatch2(clz= MonsterGroup.class, method = "applyPreTurnLogic")
public class WarpMonsterKeepBlockPatch {

    @SpireInstrumentPatch
    public static ExprEditor LoseBlockOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractMonster.class.getName()) && m.getMethodName().equals("loseBlock")) {
                    m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? $proceed(0, false) : $proceed($$));}");
                }
            }
        };
    }
}

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

    /*
        Monsters shouldn't lose block during a warp, so I perform a very similar patch to the player one here.
        If there is a warp queued, lose 0 block instead. cool.
     */
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
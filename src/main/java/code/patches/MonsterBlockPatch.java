package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;


@SpirePatch2(clz= MonsterGroup.class, method = "applyPreTurnLogic")
public class MonsterBlockPatch {

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

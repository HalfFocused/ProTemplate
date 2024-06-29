package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


@SpirePatch2(clz= NeowEvent.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez={
                boolean.class
        })

public class NeowGreetingPatch {

    static int index = 0;

    @SpireInstrumentPatch
    public static ExprEditor DiscardOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(NeowEvent.class.getName()) && m.getMethodName().equals("talk")) {
                    index++;

                    if (index == 4) {
                        m.replace("{$_=((com.megacrit.cardcrawl.dungeons.AbstractDungeon.player instanceof code.TheDisplaced) ? $proceed(code.TheDisplaced.getNeowGreeting()) : $proceed($$));}");
                    }
                }
            }
        };
    }
}

package code.patches.warp;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;


@SpirePatch2(clz= AbstractRoom.class, method = "endTurn")
public class WarpStartOfTurnPatch {

    /*
    @SpireInsertPatch(
            loc=520
    )
    public static SpireReturn<Void> Insert(AbstractRoom __instance)
    {
        if(CardUtil.queuedWarps > 0) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                public void update() {
                    this.addToBot(new EndTurnAction());
                    this.addToBot(new WaitAction(1.2F));
                    this.addToBot(new MonsterStartTurnAction());

                    AbstractDungeon.actionManager.monsterAttacksQueued = false;
                    this.isDone = true;
                }
            });
            AbstractDungeon.player.isEndingTurn = false;
            return SpireReturn.Return();
        }

        return SpireReturn.Continue();
    }
    */

    @SpireInstrumentPatch
    public static ExprEditor SkipTurnOverride()
    {
        return new ExprEditor() {
            public void edit(FieldAccess f)
                    throws CannotCompileException
            {
                //target any attempt to access the value AbstractRoom's skipMonsterTurn value
                if(f.getClassName().equals(AbstractRoom.class.getName()) && f.getFieldName().equals("skipMonsterTurn"))
                    //code to replace the field access with
                    f.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? false : $proceed($$));}");
            }
        };
    }

    static int index = 0;

    @SpireInstrumentPatch
    public static ExprEditor DiscardOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                //target any attempt to access the value AbstractRoom's skipMonsterTurn value
                if(m.getClassName().equals(GameActionManager.class.getName()) && m.getMethodName().equals("addToBottom")) {
                    index++;

                    if (index == 1 || index == 2 || index == 3) {
                        m.replace("{$_=((code.util.charUtil.CardUtil.queuedWarps > 0) ? new com.megacrit.cardcrawl.actions.utility.WaitAction(0f) : $proceed($$));}");
                    }
                }
            }
        };
    }
}
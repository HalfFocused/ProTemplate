package code.patches;

import code.ModFile;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.neow.NeowEvent;
import javassist.CannotCompileException;
import javassist.expr.ConstructorCall;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.*;
import java.util.Scanner;

import static code.ModFile.RECORD_FILE;

@SpirePatch2(clz= TimeEater.class, method = "takeTurn")
public class TimeEaterDialogPatch {

    static int index = 0;

    @SpireInstrumentPatch
    public static ExprEditor TimeEaterDialogOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(GameActionManager.class.getName()) && m.getMethodName().equals("addToBottom")) {
                    index++;

                    if (index == 2) {
                        m.replace("{$_=((com.megacrit.cardcrawl.dungeons.AbstractDungeon.player instanceof code.TheDisplaced) ? $proceed(new com.megacrit.cardcrawl.actions.animations.TalkAction(this, code.patches.TimeEaterDialogPatch.getTimeEaterDialog(), 0.5F, 3.0F)) : $proceed($$));}");
                    }
                }
            }
        };
    }

    public static String getTimeEaterDialog(){
        switch (MathUtils.random(4)){
            case 1:
                return "~A~ NL ~shallow~ NL @imitation...@";
            case 2:
                return "~Reckless.~";
            case 3:
                return "~You~ ~can't~ NL ~save~ ~her...~";
            case 4:
                return "~Not~ NL ~this~ ~time~";
        }
        return "~Not~ NL ~this~ ~time~";
    }
}

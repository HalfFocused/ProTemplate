package code.patches;

import code.ModFile;
import code.cards.collectible.rare.power.ChronoForm;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.lwjgl.Sys;

@SpirePatch2(clz= TimeEater.class, method = "takeTurn")
public class TimeEaterDialogPatch {

    static int index = 0;
    public static String[] dialogSequence = new String[2];

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

    public static String getTimeEaterDialog() {

        SpireConfig record = ModFile.getTimeEaterRecord();
        int playerWins = record.getInt("wins");
        int timeEaterWins = record.getInt("losses");
        boolean lastRunPlayerDied = record.getBool("lastRunDied");
        boolean hasChronoForm = AbstractDungeon.player.masterDeck.group.stream().anyMatch(card -> card instanceof ChronoForm);

        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
            if(mo instanceof TimeEater){
                TimeEaterFieldInject.secondDialog.set(mo, true);
            }
        }

        if(playerWins + timeEaterWins == 0){
            dialogSequence[0] = "~You're~ NL NL ~interesting.~";
            if(hasChronoForm){
                dialogSequence[1] = "~...and~ NL NL @foolish.@";
            }else{
                dialogSequence[1] = "~this~ ~is~ NL NL ~a~ ~shame...~";
            }
        }else{
            if(playerWins > timeEaterWins){
                if(playerWins == 1){
                    dialogSequence[0] = "~You're~ ~back...~";
                    if(hasChronoForm){
                        dialogSequence[1] = "~last~ ~time~ NL NL ~was~ ~a~ @fluke.@";
                    }else{
                        dialogSequence[1] = "~You've~ ~won~ ~once...~ NL NL @never@ @again...@";
                    }
                }else{
                    if(hasChronoForm){
                        dialogSequence[0] = "~You~ ~shouldn't~ NL NL @have@ @that...@";
                    }else {
                        dialogSequence[0] = randomStringFrom(
                                "~Insolent~ NL NL ~Ridiculous...~",
                                "~You~ ~can't...~ NL NL ~save~ ~her.~",
                                "~Welcome...~ NL NL ~back...~",
                                "~We've~ ~fought~ NL NL ~" + (playerWins + timeEaterWins) + "~ ~times...~"
                        );
                    }
                    if(lastRunPlayerDied){
                        if(timeEaterWins == 1) {
                            dialogSequence[1] = "~You'll~ ~die...~ NL NL @again...@";
                        }else{
                            dialogSequence[1] = randomStringFrom(
                                "~Killed~ ~you... NL NL ~" + timeEaterWins + "~ ~times.~",
                                    "~This~ ~will~ NL NL ~be~ @fun...@",
                                    "~Last~ ~time...~ NL NL @I@ @KILLED@ @you...@"
                            );
                        }
                    }else{
                        dialogSequence[1] = randomStringFrom(
                            "~This~ ~will~ NL NL ~be~ @fun...@",
                            "~Last~ ~time...~ NL NL @you@ @KILLED@ @me...@",
                            "~This~ ~time...~ NL NL ~you're~ @dying.@",
                            "~Killed~ ~me...~ NL NL ~" + playerWins + "~ ~times.~"
                        );
                    }

                }
            }else if(playerWins == timeEaterWins){
                dialogSequence[0] = "~Evenly~ NL NL ~matched...~";
                if(hasChronoForm){
                    dialogSequence[1] = "~My~ ~power~ NL NL ~is~ @greater...@";
                }else{
                    dialogSequence[1] = "~Let~ ~me...~ NL NL @correct@ ~that...~";
                }
            }else{
                if(hasChronoForm){
                    dialogSequence[0] = "~Without~ @that...@ NL NL ~YOU~ ~ARE~ ~NOTHING~";
                }else{
                    dialogSequence[0] = randomStringFrom(
                            "~Pathetic...~ NL NL ~Foolish...~",
                            "~You~ ~can't...~ NL NL ~save~ ~her.~",
                            "~Another...~ NL NL ~try?~",
                            "~We've~ ~fought~ NL NL ~" + (playerWins + timeEaterWins) + "~ ~times...~");
                }
                if(lastRunPlayerDied){
                    if(playerWins == 0) {
                        dialogSequence[1] = randomStringFrom(
                        "~You'll~ ~die~ NL NL ~here...~ NL NL @Again@",
                            "~You'll~ NL NL ~never~ ~win~",
                            "~I~ ~am...~ NL NL @superior...@"
                        );
                    }else{
                        dialogSequence[1] = randomStringFrom(
                                "~Killed~ ~you... NL NL ~" + timeEaterWins + "~ ~times.~ NL",
                                "@Time@ @is@ NL NL @up@",
                                "~Last~ ~time...~ NL NL @I@ @KILLED@ @you...@"
                        );
                    }
                }else{
                    if (playerWins == 1){
                        dialogSequence[1] = randomStringFrom(
                                "~This~ ~will~ NL NL ~be~ @fun...@",
                                "~Last~ ~time...~ NL NL @you@ @KILLED@ @me...@",
                                "~This~ ~time...~ NL NL @you're@ ~dying.~",
                                "~Killed~ ~me...~ NL NL ~once.~"
                        );
                    }else{
                        dialogSequence[1] = randomStringFrom(
                                "~This~ ~will~ NL NL ~be~ @fun...@",
                                "~Last~ ~time...~ NL NL @you@ @KILLED@ @me...@",
                                "~This~ ~time...~ NL NL @you're@ ~dying.~",
                                "~Killed~ ~me...~ NL NL ~" + playerWins + "~ ~times.~"
                        );
                    }
                }
            }
        }
        return dialogSequence[0];
    }

    private static String randomStringFrom(String... inputs){
        return inputs[MathUtils.random(0, inputs.length - 1)];
    }
}

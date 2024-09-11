package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;

import java.sql.Time;

@SpirePatch2(
        clz= TimeEater.class,
        method= SpirePatch.CLASS
)
public class TimeEaterFieldInject {
    public static SpireField<Boolean> secondDialog = new SpireField<>(() -> false);

}

package code.patches;

import basemod.ReflectionHacks;
import code.powers.ExtraTurnPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;

@SpirePatch(
        clz= EndTurnButton.class,
        method="render"
)
public class ExtraTurnButtonPatch {
    public static void Prefix(EndTurnButton __instance) {
        if(AbstractDungeon.player.hasPower(ExtraTurnPower.POWER_ID)){
            ReflectionHacks.setPrivate(__instance, EndTurnButton.class, "label", extraTurnText());
        }
    }

    private static String extraTurnText(){
        return "Extra Turn";
    }
}



package code.patches;

import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import code.powers.ExtraTurnPower;
import code.relics.ContinuityRune;
import code.util.charUtil.mods.EtherealModifier;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
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



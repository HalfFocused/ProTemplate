package code.patches;

import basemod.ReflectionHacks;
import code.TheDisplaced;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SensoryStone;
import com.megacrit.cardcrawl.events.city.Ghosts;
import javafx.scene.effect.Reflection;
import sun.security.provider.ConfigFile;

import java.util.ArrayList;

@SpirePatch(
        clz=Ghosts.class,
        method=SpirePatch.CONSTRUCTOR
)
public class GhostsPatch {
    @SpirePostfixPatch
    public static void Postfix(Ghosts __instance) {
        if(AbstractDungeon.player instanceof TheDisplaced){
            ReflectionHacks.setPrivateInherited(__instance, Ghosts.class, "body", TheDisplaced.getGhostsDialog());
        }
    }
}

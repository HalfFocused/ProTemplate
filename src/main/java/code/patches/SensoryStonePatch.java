package code.patches;

import code.TheDisplaced;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.events.beyond.SensoryStone;

import java.util.ArrayList;

@SpirePatch(
        clz= SensoryStone.class,
        method="getRandomMemory"
)
public class SensoryStonePatch {

    @SpireInsertPatch(
            loc=107,
            localvars = {"memories"}
    )

    public static void Insert(SensoryStone __instance, ArrayList<String> memories) {
        memories.add(TheDisplaced.getSensoryStone());
    }
}

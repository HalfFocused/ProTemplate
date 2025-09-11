package code.patches;

import code.cards.collectible.common.attack.LastLaugh;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class EnergyPanelPatch {
    @SpirePatch2(
            clz=EnergyPanel.class,
            method="useEnergy"
    )
    static class EnergyLossPatch{
        public static void Postfix(Object[] __args)
        {
            int energySpent = (int) __args[0];
            if(energySpent > 0 && EnergyPanel.totalCount == 0){
                CardUtil.flashback(LastLaugh.ID);
            }
        }
    }
}

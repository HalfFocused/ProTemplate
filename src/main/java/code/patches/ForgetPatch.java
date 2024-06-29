package code.patches;

import code.ModFile;
import code.util.charUtil.CardUtil;
import code.util.charUtil.EtherealExhaustHook;
import code.util.charUtil.ForgetCard;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz=AbstractCard.class,
        method="triggerOnEndOfPlayerTurn"
)
public class ForgetPatch {

    @SpireInsertPatch(
            loc=3028
    )
    public static SpireReturn<Void> Insert(AbstractCard __instance)
    {
        if(AbstractDungeon.player.hasPower(ModFile.makeID("ClingOnPower"))){
            return SpireReturn.Return();
        }
        if(__instance instanceof ForgetCard){
            CardUtil.forgetCard(__instance);
        }


        for(AbstractPower power : AbstractDungeon.player.powers){
            if(power instanceof EtherealExhaustHook){
                ((EtherealExhaustHook) power).onEtherealCardExhaust();
            }
        }

        return SpireReturn.Continue();
    }
}

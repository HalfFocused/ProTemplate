package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.rewards.RewardItem;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch2(clz= RewardItem.class, method = "claimReward")
public class PotionLinkedRewardPatch {

    @SpireInstrumentPatch
    public static ExprEditor IgnoreRewardOverride()
    {
        return new ExprEditor() {
            public void edit(MethodCall m)
                    throws CannotCompileException
            {
                if(m.getClassName().equals(AbstractPlayer.class.getName()) && m.getMethodName().equals("obtainPotion")) {
                    m.replace("{$_= (!this.ignoreReward && $proceed($$));}");
                }
            }
        };
    }
}
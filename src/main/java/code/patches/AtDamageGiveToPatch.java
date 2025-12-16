package code.patches;

import code.util.charUtil.AtDamageGiveToPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SpirePatch2(
    clz=AbstractCard.class,
    method="calculateCardDamage"
)
public class AtDamageGiveToPatch {
    @SpireInsertPatch(locator = SingleTargetDamageGive.class, localvars = {"p","tmp"})
    public static void modifySingleTargetDamagePatch(AbstractCard __instance, AbstractMonster mo, AbstractPower p, @ByRef float[] tmp) {
        if(p instanceof AtDamageGiveToPower){
            tmp[0] = ((AtDamageGiveToPower) p).atDamageGiveTo(mo, tmp[0], __instance.damageTypeForTurn, __instance);
        }
    }
    private static class SingleTargetDamageGive extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPower.class, "atDamageGive");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    @SpireInsertPatch(locator = MultiTargetDamageGive.class, localvars = {"p","tmp","i"})
    public static void modifyMultiTargetDamagePatch(AbstractCard __instance, AbstractMonster mo, AbstractPower p, float[] tmp, int i) {
        if(p instanceof AtDamageGiveToPower){
            tmp[i] = ((AtDamageGiveToPower) p).atDamageGiveTo(AbstractDungeon.getMonsters().monsters.get(i), tmp[i], __instance.damageTypeForTurn, __instance);
        }
    }

    private static class MultiTargetDamageGive extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPower.class, "atDamageGive");
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
        }
    }
}

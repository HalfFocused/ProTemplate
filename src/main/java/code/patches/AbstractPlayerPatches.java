package code.patches;

import code.TheDisplaced;
import code.cards.collectible.uncommon.skill.Forsake;
import code.powers.ForsakePower;
import code.util.charUtil.CardUtil;
import code.util.charUtil.mods.FlashbackModifier;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class AbstractPlayerPatches {

    @SpirePatch(
        clz=AbstractPlayer.class,
        method="damage"
    )
    static class canUsePatch{
        @SpireInsertPatch(
            loc=1793,
            localvars = {"damageAmount"}
        )
        public static void Insert(AbstractPlayer __instance, DamageInfo info, int damageAmount) {
            if (info.owner != null && info.owner != __instance && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
                FlashbackModifier.flashback(FlashbackModifier.NEVER_ENOUGH);
            }
        }
    }
}

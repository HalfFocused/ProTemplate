package code.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import java.util.Map;

public class CardStringsPatch {
    @SpirePatch(
            clz = CardStrings.class,
            method = "<class>"
    )
    public static class CardStringsFlavorField {
        @SerializedName("ENHANCED_FLAVOR")
        public static SpireField<String> enhancedFlavor = new SpireField(() -> {
            return null;
        });

        public CardStringsFlavorField() {
        }
    }
}

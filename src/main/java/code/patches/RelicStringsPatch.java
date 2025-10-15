package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class RelicStringsPatch {
    @SpirePatch(
            clz = RelicStrings.class,
            method = "<class>"
    )
    public static class RelicStringsFlavorField {
        @SerializedName("ENHANCED_FLAVOR")
        public static SpireField<String> enhancedFlavor = new SpireField(() -> {
            return null;
        });

        public RelicStringsFlavorField() {
        }
    }
}

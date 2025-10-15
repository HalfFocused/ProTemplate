package code.relics;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import code.ModFile;
import code.patches.CardStringsPatch;
import code.patches.RelicStringsPatch;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import code.util.TexLoader;

import static code.ModFile.makeRelicPath;
import static code.ModFile.modID;

public abstract class AbstractEasyRelic extends CustomRelic {
    public AbstractCard.CardColor color;

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        this.color = color;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    /*
    This method is called on the relic whenever the popup view is opened. How convenient!
     */
    @Override
    public void playLandingSFX(){
        super.playLandingSFX();

        RelicStrings relicStrings = ReflectionHacks.getPrivateInherited(this, AbstractEasyRelic.class, "relicStrings");

        if(CardUtil.isTimeStopped() && RelicStringsPatch.RelicStringsFlavorField.enhancedFlavor.get(relicStrings) != null){
            this.flavorText = RelicStringsPatch.RelicStringsFlavorField.enhancedFlavor.get(relicStrings);
        }else{
            this.flavorText = relicStrings.FLAVOR;
        }
    }
}
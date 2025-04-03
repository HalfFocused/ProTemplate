package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import code.ModFile;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class EtherealModifier extends AbstractCardModifier {
    public static String ID = ModFile.makeID("Ethereal");
    public EtherealModifier() {
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "Ethereal. NL " + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.isEthereal = true;
    }

    public void onRemove(AbstractCard card) {
        card.isEthereal = false;
    }

    public AbstractCardModifier makeCopy() {
        return new EtherealModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public Color getGlow(AbstractCard card) {
        return Color.LIGHT_GRAY;
    }
}
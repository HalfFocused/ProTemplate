package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SparkleModifier extends AbstractCardModifier {
    public static String ID = ModFile.makeID(SparkleModifier.class.getName());
    public SparkleModifier() {
    }

    public boolean shouldApply(AbstractCard card) {
        return true;
    }
    public AbstractCardModifier makeCopy() {
        return new SparkleModifier();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }

    public Color getGlow(AbstractCard card) {
        return Color.WHITE.cpy();
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }
}
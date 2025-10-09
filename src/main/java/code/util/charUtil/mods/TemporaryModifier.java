package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import code.util.TexLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;

public class TemporaryModifier extends AbstractCardModifier {

    public static final String ID = ModFile.makeID("Temporary");
    private int counter;
    private boolean givenOnCreation;
    //private static final Texture tex = TexLoader.getTexture(ModFile.makeUIPath("DreamIcon.png"));
    private final int initialAmount;


    public TemporaryModifier(boolean creation, int ephemeralCounterIn){
        counter = ephemeralCounterIn;
        givenOnCreation = creation;
        initialAmount = ephemeralCounterIn;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return ID + " " + counter + ". NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TemporaryModifier(givenOnCreation, counter);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean isInherent(AbstractCard card) {
        return givenOnCreation;
    }

    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        counter--;
        card.initializeDescription();
        if (counter <= 0) {
            if(group.type != CardGroup.CardGroupType.HAND) {
                if (group.type == CardGroup.CardGroupType.DRAW_PILE) {
                    card.target_x = MathUtils.random((float) Settings.WIDTH * 0.1F, (float) Settings.WIDTH * 0.4F);
                } else if (group.type == CardGroup.CardGroupType.DISCARD_PILE) {
                    card.target_x = MathUtils.random((float) Settings.WIDTH * 0.6F, (float) Settings.WIDTH * 0.9F);
                }
                card.target_y = MathUtils.random((float)Settings.HEIGHT * 0.8F, (float)Settings.HEIGHT * 0.35F);

            }
            this.addToTop(new ExhaustSpecificCardAction(card, group, false));
        }
    }


    @Override
    public void onInitialApplication(AbstractCard card){
        for(AbstractCardModifier mod : CardModifierManager.getModifiers(card, ID)){
            if(((TemporaryModifier) mod).counter > this.counter){
                CardModifierManager.removeSpecificModifier(card, mod, true);
            }
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card){
        for(AbstractCardModifier mod : CardModifierManager.getModifiers(card, ID)){
            if(((TemporaryModifier) mod).counter <= this.counter){
                return false;
            }
        }
        return true;
    }

    /*
    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(counter)).render(card);
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(counter)).render(card);
    }

     */
}

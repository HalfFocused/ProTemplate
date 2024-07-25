package code.util.charUtil.mods;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import code.util.TexLoader;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;

import javax.smartcardio.Card;

public class DreamModifier extends AbstractCardModifier {

    public static final String ID = ModFile.makeID("Dream");
    private int counter;
    private boolean givenOnCreation;
    private static final Texture tex = TexLoader.getTexture(ModFile.makeUIPath("DreamIcon.png"));
    private final int initialAmount;


    public DreamModifier(boolean creation, int ephemeralCounterIn){
        counter = ephemeralCounterIn;
        givenOnCreation = creation;
        initialAmount = ephemeralCounterIn;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return ID + " " + initialAmount + ". NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DreamModifier(givenOnCreation, counter);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean isInherent(AbstractCard card) {
        return givenOnCreation;
    }

    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        if(CardUtil.queuedWarps == 0) {
            counter--;
            card.initializeDescription();
            if (counter <= 0) {
                this.addToTop(new ExhaustSpecificCardAction(card, group, false));
            }
        }
    }


    @Override
    public void onInitialApplication(AbstractCard card){
        for(AbstractCardModifier mod : CardModifierManager.getModifiers(card, ID)){
            if(((DreamModifier) mod).counter > this.counter){
                CardModifierManager.removeSpecificModifier(card, mod, true);
            }
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card){
        for(AbstractCardModifier mod : CardModifierManager.getModifiers(card, ID)){
            if(((DreamModifier) mod).counter <= this.counter){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(counter)).render(card);
    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(counter)).render(card);
    }
}

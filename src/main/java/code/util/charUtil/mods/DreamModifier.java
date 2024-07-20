package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import code.ModFile;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DreamModifier extends AbstractCardModifier {

    private int counter;

    public DreamModifier(int ephemeralCounterIn){
        counter = ephemeralCounterIn;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return ModFile.modID + ":Dream " + counter + ". NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DreamModifier(counter);
    }

    @Override
    public String identifier(AbstractCard card) {
        return "dream";
    }

    public boolean isInherent(AbstractCard card) {
        return true;
    }

    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        counter--;
        card.initializeDescription();
        if(counter <= 0){
            this.addToTop(new ExhaustSpecificCardAction(card, group, false));
        }
    }

    /*
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        counter--;
        card.initializeDescription();
        if(counter <= 0){
            action.exhaustCard = true;
        }
    }

     */
}

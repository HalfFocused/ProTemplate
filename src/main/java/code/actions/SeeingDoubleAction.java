package code.actions;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import code.util.charUtil.mods.DreamModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.Iterator;

public class SeeingDoubleAction extends AbstractGameAction {
    public SeeingDoubleAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if(c.type == AbstractCard.CardType.ATTACK){
                AbstractCard copy = c.makeStatEquivalentCopy();
                CardModifierManager.addModifier(copy, new DreamModifier(false,1));
                this.addToTop(new MakeTempCardInHandAction(copy));
            }
        }

        this.isDone = true;
    }
}

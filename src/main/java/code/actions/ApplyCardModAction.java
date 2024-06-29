package code.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.util.charUtil.CardBlessedHook;
import code.util.charUtil.mods.BlessingModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ApplyCardModAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractCardModifier cardModifier;
    public ApplyCardModAction(AbstractCard cardIn, AbstractCardModifier cardModifierIn) {
        this.duration = 1f;
        card = cardIn;
        this.actionType = ActionType.SPECIAL;
        cardModifier = cardModifierIn;
    }

    public void update() {
        CardModifierManager.addModifier(card, cardModifier);
        if(cardModifier instanceof BlessingModifier) {
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power instanceof CardBlessedHook) {
                    ((CardBlessedHook) power).onCardBlessed(card);
                }
            }
        }

        isDone = true;
    }
}

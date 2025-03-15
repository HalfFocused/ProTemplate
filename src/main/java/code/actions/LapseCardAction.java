package code.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.powers.LapsePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LapseCardAction extends AbstractGameAction {
    private AbstractCard card;
    private CardGroup group;
    public LapseCardAction(AbstractCard cardIn, CardGroup groupIn) {
        this.duration = 1f;
        card = cardIn;
        this.actionType = ActionType.SPECIAL;
        group = groupIn;
    }

    public void update() {
        if(group.type != CardGroup.CardGroupType.HAND){
            card.target_x = (float) Settings.WIDTH / 2.0F + (300.0F * ((card.current_x < Settings.WIDTH / 2.0F) ? -1f : 1f)) * Settings.scale;
            card.target_y = (float) Settings.HEIGHT / 2.0F;
            card.targetDrawScale = 0.5f;
        }
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LapsePower(AbstractDungeon.player, card.makeCopy())));
        this.addToBot(new ExhaustSpecificCardAction(card, group));
        isDone = true;
    }
}

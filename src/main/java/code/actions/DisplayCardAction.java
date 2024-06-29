package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DisplayCardAction extends AbstractGameAction {
    private AbstractCard card;
    boolean toTheSide = false;
    public DisplayCardAction(AbstractCard cardIn) {
        this.duration = 0.6f;
        card = cardIn;
        this.actionType = ActionType.SPECIAL;
        toTheSide = false;
    }

    public DisplayCardAction(AbstractCard cardIn, boolean toTheSideIn) {
        this.duration = 0.6f;
        card = cardIn;
        this.actionType = ActionType.SPECIAL;
        toTheSide = toTheSideIn;
    }

    public void update() {
        if (this.duration == 0.6f) {

            AbstractDungeon.player.hand.group.remove(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            AbstractDungeon.player.limbo.group.add(card);
            card.target_x = ((float)Settings.WIDTH / 2.0F) + (toTheSide ? (150 * Settings.xScale) : 0);
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.drawScale = 0.25F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
        }

        this.tickDuration();
    }
}

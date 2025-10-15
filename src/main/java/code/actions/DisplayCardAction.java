package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DisplayCardAction extends AbstractGameAction {
    private AbstractCard card;
    public DisplayCardAction(AbstractCard cardIn) {
        this.duration = 0.6f;
        card = cardIn;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (this.duration == 0.6f) {
            AbstractDungeon.player.limbo.group.add(card);
            if(card.current_x >= Settings.WIDTH / 2.0f){
                card.target_x = ((float)Settings.WIDTH / 2.0F) + (300 * Settings.scale);
            }else{
                card.target_x = ((float)Settings.WIDTH / 2.0F) - (300 * Settings.scale);
            }
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.drawScale = 0.25F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
        }

        this.tickDuration();
    }
}

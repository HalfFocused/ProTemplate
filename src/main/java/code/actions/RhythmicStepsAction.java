package code.actions;

import basemod.BaseMod;
import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class RhythmicStepsAction extends AbstractGameAction {

    static boolean attack, skill = false;
    public RhythmicStepsAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if(c.type == AbstractCard.CardType.ATTACK){
                attack = true;
            }
            if(c.type == AbstractCard.CardType.SKILL){
                skill = true;
            }
            if(!(attack && skill) && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE && (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size()) != 0){
                this.addToTop(new DrawCardAction(1, new RhythmicStepsAction()));
            }else{
                attack = false;
                skill = false;
            }
        }

        this.isDone = true;
    }
}

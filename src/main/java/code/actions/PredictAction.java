package code.actions;

import basemod.BaseMod;
import code.relics.PairOfGears;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.function.Predicate;

public class PredictAction extends AbstractGameAction {

    private int num;
    private Predicate<AbstractCard> quality;
    public PredictAction(int numIn, Predicate<AbstractCard> qualityIn) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        num = numIn;
        quality = qualityIn;
    }

    public void update() {
        this.addToTop(new DrawCardAction(num, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    boolean gearSave = AbstractDungeon.player.hasRelic(PairOfGears.ID) && c.type == AbstractCard.CardType.POWER;
                    if(!quality.test(c) && !gearSave){
                        this.addToTop(new DiscardSpecificCardAction(c));
                    }
                    if(gearSave){
                        AbstractDungeon.player.getRelic(PairOfGears.ID).flash();
                    }
                }
                this.addToTop(new ForceWaitAction(Settings.FAST_MODE ? 0.25f : 0.5f));
                isDone = true;
            }
        }));


        this.isDone = true;
    }
}

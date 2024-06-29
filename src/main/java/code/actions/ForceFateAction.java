package code.actions;

import basemod.BaseMod;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ForceFateAction extends AbstractGameAction {

    private AbstractPlayer p;

    public ForceFateAction() {
        this.p = AbstractDungeon.player;// 17
        this.actionType = ActionType.CARD_MANIPULATION;// 19
        this.duration = Settings.ACTION_DUR_MED;// 20
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {// 26
            this.addToBot(new FetchAction(AbstractDungeon.player.drawPile, CardUtil::isCardBlessed, BaseMod.MAX_HAND_SIZE));
            this.addToBot(new FetchAction(AbstractDungeon.player.discardPile, CardUtil::isCardBlessed, BaseMod.MAX_HAND_SIZE));
            this.isDone = true;
        }

        this.tickDuration();
    }// 72
}

package code.powers;

import code.ModFile;
import code.actions.PredictAction;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.lwjgl.Sys;

public class ExtraTurnPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("ExtraTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ExtraTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }
    @Override
    public void updateDescription() {
        description = (amount == 1) ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }

    public void onInitialApplication() {
        if(AbstractDungeon.actionManager.monsterAttacksQueued){
            atEndOfTurn(true);
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getCurrRoom().skipMonsterTurn && CardUtil.queuedWarps <= 0) {
            this.flash();
            this.addToTop(new SkipEnemiesTurnAction());
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }
}

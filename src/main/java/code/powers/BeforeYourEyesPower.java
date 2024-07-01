package code.powers;

import code.ModFile;
import code.actions.AllEnemiesLoseHPAction;
import code.util.charUtil.EtherealExhaustHook;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BeforeYourEyesPower extends AbstractEasyPower implements EtherealExhaustHook {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("BeforeYourEyesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BeforeYourEyesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = (amount == 1) ?  DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }


    @Override
    public void onEtherealCardExhaust() {
        flash();
        for(int i = 0; i < amount; i++){
            this.addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
        }
    }
}

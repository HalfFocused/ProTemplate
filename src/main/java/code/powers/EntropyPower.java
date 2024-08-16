package code.powers;

import code.ModFile;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EntropyPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("EntropyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EntropyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for(int i = 0; i < this.amount; ++i) {
            this.addToBot(new AbstractGameAction() {
                public void update() {
                    this.addToBot(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), true));
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        description = (amount == 1) ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }


}

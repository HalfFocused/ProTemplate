package code.powers;

import code.ModFile;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class AsExpectedPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("AsExpectedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AsExpectedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature targetCreature) {
        if (damageAmount > 0 && targetCreature != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if(!targetCreature.hasPower(ForetoldPower.POWER_ID)){
                        flash();
                        addToTop(new ApplyPowerAction(targetCreature, owner, new ForetoldPower(targetCreature, AsExpectedPower.this.amount)));
                    }
                    isDone = true;
                }
            });
        }

    }
}

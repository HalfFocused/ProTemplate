package code.powers;

import code.ModFile;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class OverAndOverPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("OverAndOverPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OverAndOverPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        priority = 4;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(type != DamageInfo.DamageType.NORMAL){
            return damage;
        }else {
            int streak = 0;
            for(CardUtil.CardStreak cardStreak : CardUtil.cardStreaks){
                if(cardStreak.name.equals(card.name)){
                    streak = cardStreak.streak;
                }
            }
            return damage * (streak + 1);
        }
    }

}

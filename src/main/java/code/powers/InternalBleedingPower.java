package code.powers;

import code.ModFile;
import code.actions.PredictAction;
import code.effects.InternalBleedingSlashEffect;
import code.effects.RecklessAbandonEffect;
import code.util.charUtil.AtDamageGiveToPower;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static code.util.charUtil.CardUtil.randomSlash;

public class InternalBleedingPower extends AbstractEasyPower implements AtDamageGiveToPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("InternalBleedingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InternalBleedingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature attackedEnemy) {
        if(info.type == DamageInfo.DamageType.NORMAL) {
            for (AbstractPower p : attackedEnemy.powers) {
                if (p.type == PowerType.DEBUFF) {
                    this.addToTop(new VFXAction(new InternalBleedingSlashEffect(attackedEnemy.hb.cX + MathUtils.random(-attackedEnemy.hb.width * 0.35f, attackedEnemy.hb.width * 0.35f) * Settings.scale, attackedEnemy.hb.cY + MathUtils.random(-attackedEnemy.hb.height * 0.35f, attackedEnemy.hb.height * 0.35f) * Settings.scale, AbstractGameAction.AttackEffect.POISON, false), 0.1f));
                }
            }
        }
    }

    @Override
    public float atDamageGiveTo(AbstractCreature target, float damage, DamageInfo.DamageType type, AbstractCard sourceCard) {
        float newDamage = damage;
        for(AbstractPower p : target.powers){
            if(p.type == PowerType.DEBUFF){
                newDamage += amount;
            }
        }
        return newDamage;
    }
}

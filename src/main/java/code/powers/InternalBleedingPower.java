package code.powers;

import code.ModFile;
import code.actions.PredictAction;
import code.effects.InternalBleedingSlashEffect;
import code.effects.RecklessAbandonEffect;
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

public class InternalBleedingPower extends AbstractEasyPower {
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
            boolean mute = false;
            if (attackedEnemy.hasPower(VulnerablePower.POWER_ID)) {
                this.addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        attackedEnemy.damage(new DamageInfo(this.source, InternalBleedingPower.this.amount, DamageInfo.DamageType.HP_LOSS));
                        isDone = true;
                    }
                });
                this.addToBot(new VFXAction(new InternalBleedingSlashEffect(attackedEnemy.hb.cX + MathUtils.random(-attackedEnemy.hb.width * 0.35f, attackedEnemy.hb.width * 0.35f) * Settings.scale, attackedEnemy.hb.cY + MathUtils.random(-attackedEnemy.hb.height * 0.35f, attackedEnemy.hb.height * 0.35f) * Settings.scale, AbstractGameAction.AttackEffect.POISON, mute), 0.1f));
                mute = true;
            }
            if (attackedEnemy.hasPower(WeakPower.POWER_ID)) {
                this.addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        attackedEnemy.damage(new DamageInfo(this.source, InternalBleedingPower.this.amount, DamageInfo.DamageType.HP_LOSS));
                        isDone = true;
                    }
                });
                this.addToBot(new VFXAction(new InternalBleedingSlashEffect(attackedEnemy.hb.cX + MathUtils.random(-attackedEnemy.hb.width * 0.35f, attackedEnemy.hb.width * 0.35f) * Settings.scale, attackedEnemy.hb.cY + MathUtils.random(-attackedEnemy.hb.height * 0.35f, attackedEnemy.hb.height * 0.35f) * Settings.scale, AbstractGameAction.AttackEffect.POISON, mute), 0.1f));
                mute = true;
            }
            if (attackedEnemy.hasPower(ForetoldPower.POWER_ID)) {
                this.addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        attackedEnemy.damage(new DamageInfo(this.source, InternalBleedingPower.this.amount, DamageInfo.DamageType.HP_LOSS));
                        isDone = true;
                    }
                });
                this.addToBot(new VFXAction(new InternalBleedingSlashEffect(attackedEnemy.hb.cX + MathUtils.random(-attackedEnemy.hb.width * 0.35f, attackedEnemy.hb.width * 0.35f) * Settings.scale, attackedEnemy.hb.cY + MathUtils.random(-attackedEnemy.hb.height * 0.35f, attackedEnemy.hb.height * 0.35f) * Settings.scale, AbstractGameAction.AttackEffect.POISON, mute), 0.1f));
            }
        }
    }
}

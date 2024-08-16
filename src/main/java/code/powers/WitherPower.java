package code.powers;

import code.ModFile;
import code.util.charUtil.WarpHook;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class WitherPower extends AbstractEasyPower implements WarpHook, HealthBarRenderPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("WitherPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WitherPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }
    @Override
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))

    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        tick();
    }

    @Override
    public void onWarp() {
        tick();
    }

    private void tick(){
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            DamageAction action = new DamageAction(this.owner, new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.HP_LOSS));
            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, Color.BLACK.cpy());
            this.addToBot(action);
            //this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 2));
        }
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY.cpy();
    }

}

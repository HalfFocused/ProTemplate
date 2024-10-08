package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AllEnemiesLoseHPAction extends AbstractGameAction {

    AbstractCreature source;
    int damage;

    public AllEnemiesLoseHPAction(AbstractCreature sourceIn, int damageIn) {
        this.duration = Settings.ACTION_DUR_FAST;
        source = sourceIn;
        damage = damageIn;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            // 37
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    mo.damage(new DamageInfo(this.source, damage, DamageInfo.DamageType.HP_LOSS));
                }
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {// 37
                AbstractDungeon.actionManager.clearPostCombatActions();// 38
            }
        }

        this.tickDuration();
    }
}

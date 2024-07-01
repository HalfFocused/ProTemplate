package code.actions;

import code.cards.collectible.rare.skill.Relapse;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RelapseAction extends AbstractGameAction {
    public RelapseAction(int amountIn) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amountIn;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int cardsPlayed = 0;
            AbstractDungeon.player.exhaustPile.shuffle();
            for(AbstractCard card : AbstractDungeon.player.exhaustPile.group){
                if(card.isEthereal && !(card instanceof Relapse) && card.canUse(AbstractDungeon.player, AbstractDungeon.getRandomMonster())){
                    AbstractCard tmp = card.makeStatEquivalentCopy();
                    tmp.purgeOnUse = true;
                    this.addToBot(new NewQueueCardAction(tmp, true, false, true));
                    if(tmp instanceof ForgetCard){
                        ((ForgetCard) tmp).onForget();
                    }
                    cardsPlayed++;
                    if(cardsPlayed >= amount){
                        break;
                    }
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {// 37
                AbstractDungeon.actionManager.clearPostCombatActions();// 38
            }
        }

        this.tickDuration();
    }
}

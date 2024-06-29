package code.actions;

import basemod.helpers.CardModifierManager;
import code.effects.BlessCardEffect;
import code.util.charUtil.CardUtil;
import code.util.charUtil.mods.BlessingModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BlessRandomCardsInHandAction extends AbstractGameAction {

    private int num;
    private BlessingModifier mod;
    private Predicate<AbstractCard> cardPredicate;

    public BlessRandomCardsInHandAction(BlessingModifier modIn, int numIn, Predicate<AbstractCard> cardPredicateIn) {
        this.duration = Settings.ACTION_DUR_FAST;
        mod = modIn;
        num = numIn;
        cardPredicate = cardPredicateIn;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> chosenCards = new ArrayList<>();
            for(int i = 0; i < num; i++){
                ArrayList<AbstractCard> blessableCards = new ArrayList<>();
                for(AbstractCard card : AbstractDungeon.player.hand.group){
                    if(
                        !CardModifierManager.hasModifier(card, mod.identifier(card)) &&
                        !chosenCards.contains(card) &&
                        card.type != AbstractCard.CardType.CURSE &&
                        card.type != AbstractCard.CardType.STATUS &&
                        (cardPredicate == null || cardPredicate.test(card)) &&
                        AbstractDungeon.getMonsters().monsters.stream().anyMatch(card::cardPlayable) &&
                        !CardUtil.isDebilitated(card)
                    )
                    {
                        blessableCards.add(card);
                    }
                }
                if(blessableCards.size() == 0){
                    this.isDone = true;
                    break;
                }
                AbstractCard chosenCard = blessableCards.get(AbstractDungeon.cardRandomRng.random(blessableCards.size() -1));
                chosenCards.add(chosenCard);
                BlessCardEffect effect = new BlessCardEffect(chosenCard.hb.cX, chosenCard.hb.cY);
                effect.renderBehind = false;
                effect.duration = 0.1f;
                AbstractDungeon.effectList.add(effect);
                AbstractDungeon.actionManager.addToTop(new ApplyCardModAction(chosenCard, mod));
            }
            AbstractDungeon.actionManager.addToTop(new ForceWaitAction(0.11f));
        }

        this.tickDuration();
    }
}

package code.actions;


import basemod.BaseMod;
import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DissociateAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public DissociateAction() {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Predicate<AbstractCard> notEthereal = card -> !card.isEthereal;
            Consumer<List<AbstractCard>> makeEthereal = cards -> {
                for(AbstractCard card : cards){
                    CardModifierManager.addModifier(card, new EtherealMod());
                    this.addToBot(new DrawCardAction(1));
                }
            };
            this.addToBot(new SelectCardsInHandAction(BaseMod.MAX_HAND_SIZE, "Select Cards to make Ethereal", true, true, notEthereal, makeEthereal));
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SetupAction");
        TEXT = uiStrings.TEXT;
    }
}

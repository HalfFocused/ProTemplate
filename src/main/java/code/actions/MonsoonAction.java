package code.actions;


import basemod.BaseMod;
import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import code.effects.MonsoonEffect;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MonsoonAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public MonsoonAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int handSize = AbstractDungeon.player.hand.size();
            this.addToTop(new DrawCardAction(handSize));
            for(int i = handSize - 1; i >= 0; i--){
                AbstractCard c = AbstractDungeon.player.hand.group.get(i);
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.hand.moveToExhaustPile(c);
                        CardCrawlGame.dungeon.checkForPactAchievement();
                        c.exhaustOnUseOnce = false;
                        c.freeToPlayOnce = false;
                        isDone = true;
                    }
                });
            }
            float monsoonDuration = 1.5f;
            if(AbstractDungeon.player.drawPile.size() < handSize && !AbstractDungeon.player.discardPile.isEmpty()){
                monsoonDuration += 0.5f;
            }
            AbstractDungeon.actionManager.addToTop(new VFXAction(new MonsoonEffect(monsoonDuration)));
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SetupAction");
        TEXT = uiStrings.TEXT;
    }
}

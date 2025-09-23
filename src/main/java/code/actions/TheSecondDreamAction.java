package code.actions;


import basemod.helpers.CardModifierManager;
import code.effects.MonsoonEffect;
import code.powers.ExtraTurnPower;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class TheSecondDreamAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public TheSecondDreamAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(CardUtil.etherealCardsPlayedThisTurn() >= 3){
                this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExtraTurnPower(AbstractDungeon.player, 1)));
                this.addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        CardUtil.theSecondDreamActivatedThisTurn = true;
                        isDone = true;
                    }
                });
                BorderLongFlashEffect effect = new BorderLongFlashEffect(Color.GOLD.cpy());
                this.addToTop(new VFXAction(effect));
            }
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SetupAction");
        TEXT = uiStrings.TEXT;
    }
}

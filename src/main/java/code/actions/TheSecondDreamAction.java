package code.actions;


import code.cards.collectible.rare.skill.TheSecondDream;
import code.powers.ExtraTurnPower;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class TheSecondDreamAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public static long lastActivatedSystemTime = 0;

    private static final CardStrings theSecondDreamCardStrings = CardCrawlGame.languagePack.getCardStrings(TheSecondDream.ID);

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
                        if(!CardUtil.isTimeStopped()){
                            lastActivatedSystemTime = System.currentTimeMillis();
                        }
                        CardUtil.theSecondDreamActivatedThisTurn = true;
                        isDone = true;
                    }
                });
                BorderLongFlashEffect effect = new BorderLongFlashEffect(Color.GOLD.cpy());
                this.addToTop(new VFXAction(effect));
            }else{
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, theSecondDreamCardStrings.EXTENDED_DESCRIPTION[MathUtils.random(0, theSecondDreamCardStrings.EXTENDED_DESCRIPTION.length - 1)], true));
            }
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("SetupAction");
        TEXT = uiStrings.TEXT;
    }
}

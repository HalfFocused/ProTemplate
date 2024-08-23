package code.powers;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import code.TheDisplaced;
import code.actions.AllEnemiesLoseHPAction;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.UUID;

public class TheSecondDreamPower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("TheSecondDreamPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TheSecondDreamPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, -1);
        priority = 1;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onExhaust(AbstractCard exhausted) {
        this.flash();
        if(exhausted.cost != -2) {
            AbstractCard card = exhausted.makeStatEquivalentCopy();
            card.purgeOnUse = true;
            card.current_x = exhausted.current_x;
            card.current_y = exhausted.current_y;
            card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float) Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();

            CardModifierManager.addModifier(card, new AbstractCardModifier() {
                @Override
                public AbstractCardModifier makeCopy() {
                    return null;
                }

                @Override
                public boolean isInherent(AbstractCard card){
                    return true;
                }

                @Override
                public String identifier(AbstractCard card) {
                    return ModFile.makeID("PlayableOutOfTurn");
                }

                @Override
                public boolean removeOnCardPlayed(AbstractCard card) {
                    return true;
                }

            });

            this.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    CardUtil.theSecondDream = true;
                    ReflectionHacks.privateMethod(GameActionManager.class, "getNextAction").invoke(AbstractDungeon.actionManager);
                    CardUtil.theSecondDream = false;
                    isDone = true;
                }
            });
            this.addToTop(new NewQueueCardAction(card, true, true, true));

        }
    }
}

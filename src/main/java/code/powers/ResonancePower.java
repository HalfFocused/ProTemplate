package code.powers;

import code.ModFile;
import code.actions.PredictAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ResonancePower extends AbstractEasyPower {
    public AbstractCreature source;

    public static final String POWER_ID = ModFile.makeID("ResonancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int triggersThisTurn = 0;

    public ResonancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        description = (amount == 1) ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurn() {
        triggersThisTurn = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK) {
            triggersThisTurn++;
            if (triggersThisTurn <= amount) {
                flash();
                addToBot(new PredictAction(1, card1 -> card1.type == AbstractCard.CardType.ATTACK, new AbstractGameAction() {
                    @Override
                    public void update() {
                        System.out.println("callback");
                    for(AbstractCard c : PredictAction.resultingCards){
                        System.out.println("name " + c.name);
                        AbstractDungeon.player.hand.group.remove(c);
                        AbstractDungeon.getCurrRoom().souls.remove(c);
                        AbstractDungeon.player.limbo.group.add(c);
                        c.current_y = -200.0F * Settings.scale;
                        c.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                        c.target_y = (float)Settings.HEIGHT / 2.0F;
                        c.targetAngle = 0.0F;
                        c.lighten(false);
                        c.drawScale = 0.12F;
                        c.targetDrawScale = 0.75F;
                        c.applyPowers();
                        this.addToTop(new NewQueueCardAction(c, action.target, false, true));
                        this.addToTop(new UnlimboAction(c));
                    }
                    isDone = true;
                    }
                }));
            }
        }
    }
}

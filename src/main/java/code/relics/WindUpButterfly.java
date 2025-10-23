package code.relics;

import code.TheDisplaced;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.ModFile.makeID;

public class WindUpButterfly extends AbstractEasyRelic {
    public static final String ID = makeID("WindUpButterfly");

    public WindUpButterfly() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atTurnStart() {
        if (this.counter == -2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
        }
        this.stopPulse();
        this.counter = -1;
    }

    public void onExhaust(AbstractCard card) {
        this.counter = -2;
        if (!this.pulse) {
            beginLongPulse();
        }
    }

    public void onVictory() {
        this.stopPulse();
        this.counter = -1;
    }
}

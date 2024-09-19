package code.relics;

import code.TheDisplaced;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.ModFile.makeID;

public class AstronomicalClock extends AbstractEasyRelic {
    public static final String ID = makeID("AstronomicalClock");

    public AstronomicalClock() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        ++this.counter;
        if(this.counter == 2){
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
            this.addToBot(new GainEnergyAction(1));
            this.grayscale = true;
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

}

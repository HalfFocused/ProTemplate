package code.relics;

import code.TheDisplaced;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.ModFile.makeID;

public class TheClockworkUniverse extends AbstractEasyRelic {
    public static final String ID = makeID("TheClockworkUniverse");

    public TheClockworkUniverse() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        ++this.counter;
        if(this.counter <= 3){
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
            this.addToBot(new GainEnergyAction(1));
            if(this.counter == 3){
                this.grayscale = true;
            }
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public boolean canSpawn(){
        return AbstractDungeon.player.hasRelic(AstronomicalClock.ID) && super.canSpawn();
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(AstronomicalClock.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(AstronomicalClock.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
}

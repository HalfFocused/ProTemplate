package code.relics;

import code.TheDisplaced;
import code.powers.ExtraTurnPower;
import code.powers.NextTurnSetEnergyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.ModFile.makeID;

public class TheClockworkUniverse extends AbstractEasyRelic {
    public static final String ID = makeID("TheClockworkUniverse");

    public TheClockworkUniverse() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atBattleStart() {
        flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        //this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NextTurnSetEnergyPower(AbstractDungeon.player, 3)));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExtraTurnPower(AbstractDungeon.player, 1)));
        this.grayscale = true;
    }

    public void onVictory() {
        this.grayscale = false;
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

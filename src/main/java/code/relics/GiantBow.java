package code.relics;

import code.TheDisplaced;
import code.actions.WarpAction;
import code.util.charUtil.WarpHook;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.ModFile.makeID;

public class GiantBow extends AbstractEasyRelic implements WarpHook {
    public static final String ID = makeID("GiantBow");

    public GiantBow() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }
    private static boolean usedThisCombat = false;

    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public void onVictory() {
        this.pulse = false;
    }
    @Override
    public void onWarp() {
        if(!usedThisCombat){
            this.flash();
            this.pulse = false;
            this.addToBot(new WarpAction(1));
            this.grayscale = true;
            usedThisCombat = true;
        }
    }
}

package code.relics;

import code.TheDisplaced;

import static code.ModFile.makeID;

public class ContinuityRune extends AbstractEasyRelic  {
    public static final String ID = makeID("ContinuityRune");

    public ContinuityRune() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }
}

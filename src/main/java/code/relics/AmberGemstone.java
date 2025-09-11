package code.relics;

import code.TheDisplaced;

import static code.ModFile.makeID;

public class AmberGemstone extends AbstractEasyRelic  {
    public static final String ID = makeID("AmberGemstone");

    public AmberGemstone() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }
}

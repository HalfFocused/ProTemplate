package code.potions;

import code.ModFile;
import code.TheDisplaced;
import code.actions.WarpAction;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static code.ModFile.makeID;

public class Adrenaline extends AbstractEasyPotion {
    public static String ID = makeID("Adrenaline");

    public Adrenaline() {
        super(ID, PotionRarity.UNCOMMON, PotionSize.BOLT, new Color(0.7f, 0.3f, 0.2f, 1f), new Color(0.3f, 0.6f, 0.15f, 1f), new Color(0.8f, 0.35f, 0.8f, 1f), TheDisplaced.Enums.THE_DISPLACED, ModFile.displacedColor);
    }

    public int getPotency(int ascensionlevel) {
        return 3;
    }

    public void use(AbstractCreature creature) {
        addToBot(new WarpAction(potency));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }
}
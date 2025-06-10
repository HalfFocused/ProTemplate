package code.potions;

import code.TheDisplaced;
import code.ModFile;
import code.powers.ForetellPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

public class WitherPotion extends AbstractEasyPotion {
    public static String ID = makeID("WitherPotion");

    public WitherPotion() {
        super(ID, PotionRarity.COMMON, PotionSize.SPIKY, new Color(0.2f, 0.2f, 0.2f, 1f), new Color(0.4f, 0.4f, 0.4f, 1f), null, TheDisplaced.Enums.THE_DISPLACED, ModFile.displacedColor);
        this.isThrown = true;
        this.targetRequired = true;
    }

    public int getPotency(int ascensionlevel) {
        return 6;
    }

    public void use(AbstractCreature creature) {
        applyToEnemy((AbstractMonster) creature, new ForetellPower(creature, potency));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }
}
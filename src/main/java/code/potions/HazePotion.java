package code.potions;

import code.ModFile;
import code.TheDisplaced;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static code.ModFile.makeID;

public class HazePotion extends AbstractEasyPotion {
    public static String ID = makeID("HazePotion");

    public HazePotion() {
        super(ID, PotionRarity.UNCOMMON, PotionSize.JAR, new Color(0.7f, 0.3f, 0.2f, 1f), new Color(0.3f, 0.6f, 0.15f, 1f), new Color(0.8f, 0.35f, 0.8f, 1f), TheDisplaced.Enums.THE_DISPLACED, ModFile.displacedColor);
    }

    public int getPotency(int ascensionlevel) {
        return 5;
    }

    public void use(AbstractCreature creature) {
        this.addToBot(new DrawCardAction(potency));
        this.addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 2, true, true));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }
}
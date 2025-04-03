package code.potions;

import code.ModFile;
import code.TheDisplaced;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.ModFile.makeID;

public class GreenTea extends AbstractEasyPotion {
    public static String ID = makeID("GreenTea");

    public GreenTea() {
        super(ID, PotionRarity.RARE, PotionSize.BOTTLE, new Color(0.1f, 0.9f, 0.25f, 1f), new Color(0.3f, 0.6f, 0.15f, 1f), new Color(0.9f, 1f, 0.9f, 1f), TheDisplaced.Enums.THE_DISPLACED, ModFile.displacedColor);
    }

    public int getPotency(int ascensionlevel) {
        return 1;
    }

    public void use(AbstractCreature creature) {
        this.addToBot(new MoveCardsAction(AbstractDungeon.player.hand, AbstractDungeon.player.exhaustPile, card->true, potency, cardList -> {
            for(AbstractCard c : cardList){
                c.setCostForTurn(0);
            }
        }));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + (potency == 1 ? strings.DESCRIPTIONS[1] : strings.DESCRIPTIONS[2]);
    }
}
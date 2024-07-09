package code.relics;

import code.TheDisplaced;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static code.ModFile.makeID;

public class Raincoat extends AbstractEasyRelic  {
    public static final String ID = makeID("Raincoat");

    private final int NUM_CARDS = 5;
    public Raincoat() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
        this.counter = 0;
    }

    public void onPlayCard(AbstractCard drawnCard) {
        if(drawnCard.isEthereal){
            counter++;
            if (this.counter % NUM_CARDS == 0) {// 38
                this.counter = 0;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 41
                this.addToBot(new GainEnergyAction(1));// 42
            }
        }
    }

}

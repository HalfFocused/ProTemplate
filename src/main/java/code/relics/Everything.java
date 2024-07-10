package code.relics;

import code.ModFile;
import code.TheDisplaced;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

import static code.ModFile.makeID;

public class Everything extends AbstractEasyRelic {
    public static final String ID = makeID("Everything");

    private boolean choosing = false;
    public Everything() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    @Override
    public void onEquip() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.color == TheDisplaced.Enums.DISPLACED_COLOR) {
                AbstractCard option = card.makeCopy();
                for (AbstractRelic relic : AbstractDungeon.player.relics) {
                    relic.onPreviewObtainCard(option);
                    UnlockTracker.markCardAsSeen(option.cardID);
                }
                group.addToBottom(option);
            }
            choosing = true;
            AbstractDungeon.gridSelectScreen.open(group, 1, "Choose a card to obtain.", false);
        }
    }

    @Override
    public void update()
    {
        super.update();
        if (choosing && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()){
            choosing = false;
            AbstractCard selected = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            UnlockTracker.markCardAsSeen(selected.cardID);
            AbstractDungeon.effectsQueue.add(new FastCardObtainEffect(selected, selected.current_x, selected.current_y));
        }
    }
}

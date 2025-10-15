package code.relics;

import code.ModFile;
import code.TheDisplaced;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.*;

import static code.ModFile.makeID;

public class Everything extends AbstractEasyRelic {
    public static final String ID = makeID("Everything");

    private static final int UPGRADES = 5;
    public Everything() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));// 99
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();

        for(AbstractCard c : AbstractDungeon.player.masterDeck.group){
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }

        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        for(int i = 0; i < UPGRADES && i < upgradableCards.size(); i++){
            AbstractCard upgradedCard = upgradableCards.get(i);
            upgradedCard.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradedCard);
            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(upgradedCard.makeStatEquivalentCopy()));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + UPGRADES + DESCRIPTIONS[1];
    }
}

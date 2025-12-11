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

    public Everything() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }
}

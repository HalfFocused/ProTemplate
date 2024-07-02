package code.relics;

import code.TheDisplaced;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static code.ModFile.makeID;

public class AmberMedallion extends AbstractEasyRelic  {
    public static final String ID = makeID("AmberMedallion");

    public AmberMedallion() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }
}

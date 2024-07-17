package code.relics;

import code.TheDisplaced;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.sun.org.apache.xpath.internal.operations.Mod;

import static code.ModFile.makeID;

public class Locket extends AbstractEasyRelic  {
    public static final String ID = makeID("Locket");

    public Locket() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    private static final CardGroup filtered = CardUtil.filteredRandomCard((card)->(card.color == TheDisplaced.Enums.DISPLACED_COLOR && card.isEthereal));


    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(filtered.getRandomCard(AbstractDungeon.cardRandomRng)));
    }
}

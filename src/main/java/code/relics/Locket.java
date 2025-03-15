package code.relics;

import basemod.helpers.CardModifierManager;
import code.TheDisplaced;
import code.util.charUtil.CardUtil;
import code.util.charUtil.mods.EtherealModifier;
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

    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        CardModifierManager.addModifier(c, new EtherealModifier());
        this.addToBot(new MakeTempCardInHandAction(c));
    }
}

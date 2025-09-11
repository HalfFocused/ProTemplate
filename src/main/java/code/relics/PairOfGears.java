package code.relics;

import code.TheDisplaced;
import code.actions.PredictAction;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import javax.smartcardio.Card;

import static code.ModFile.makeID;

public class PairOfGears extends AbstractEasyRelic  {
    public static final String ID = makeID("PairOfGears");
    private boolean activated = false;

    public PairOfGears() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atBattleStartPreDraw() {
        this.activated = false;
    }

    @Override
    public void atTurnStartPostDraw() {
        if(!activated) {
            activated = true;
            this.addToBot(new DrawPileToHandAction(1, AbstractCard.CardType.POWER));
        }
    }
}

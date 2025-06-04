package code.cards.collectible.uncommon.skill;

import basemod.devcommands.draw.Draw;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import code.util.charUtil.ForgetCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

public class LowVisibility extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("LowVisibility");
    // intellij stuff skill, ALL, uncommon, , , 10, 3, 1, 1

    public LowVisibility() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        isEthereal = true;
        baseBlock = block = 12;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public void onForget() {
        this.addToTop(new SelectCardsAction(
            AbstractDungeon.player.discardPile.group,
            1,
            cardStrings.EXTENDED_DESCRIPTION[0],
            false,
            card->true,
            cards -> {
                for(AbstractCard card : cards){
                    card.retain = true;
                    addToBot(new DiscardToHandAction(card));
                }
            }
        ));
    }
}
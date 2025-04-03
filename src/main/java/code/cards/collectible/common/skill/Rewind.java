package code.cards.collectible.common.skill;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rewind extends AbstractEasyCard {
    public final static String ID = makeID(Rewind.class.getSimpleName());

    public Rewind() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        selfRetain = true;
        exhaust = true;
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SelectCardsAction(p.discardPile.group, 1, cardStrings.EXTENDED_DESCRIPTION[0], cards -> {
            for(AbstractCard card : cards){
                this.addToBot(new LapseCardAction(card, AbstractDungeon.player.discardPile));
            }
        }));
    }

    public void upp() {
        exhaust = false;
    }
}
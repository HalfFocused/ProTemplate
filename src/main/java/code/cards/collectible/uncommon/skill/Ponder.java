package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Ponder extends AbstractEasyCard {
    public final static String ID = makeID(Ponder.class.getSimpleName());

    public Ponder() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsAction(
                AbstractDungeon.player.drawPile.group,
                1,
                cardStrings.EXTENDED_DESCRIPTION[0],
                cards -> {
                    for(AbstractCard c : cards){
                        AbstractDungeon.player.drawPile.removeCard(c);
                        AbstractDungeon.player.drawPile.addToTop(c);
                    }
                }
        ));
        addToBot(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
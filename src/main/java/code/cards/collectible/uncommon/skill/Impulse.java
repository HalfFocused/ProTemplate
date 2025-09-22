package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;
import code.powers.ForetoldPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Impulse extends AbstractEasyCard {
    public final static String ID = makeID(Impulse.class.getSimpleName());

    public Impulse() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new WaitAction(0.1f));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToTop(new SelectCardsInHandAction(1,
                        cardStrings.EXTENDED_DESCRIPTION[0],
                        false,
                        false,
                        card->DrawCardAction.drawnCards.contains(card),
                        selectedCards-> {
                            for(AbstractCard c : selectedCards){
                                addToTop(new ExhaustSpecificCardAction(c, p.hand));
                            }
                        }
                        ));
                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
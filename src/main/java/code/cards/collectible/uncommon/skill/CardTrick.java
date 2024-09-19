package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CardTrick extends AbstractEasyCard {
    public final static String ID = makeID("CardTrick");
    // intellij stuff skill, self, uncommon, , , , , , 

    public CardTrick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard card : DrawCardAction.drawnCards){
                    if(card.cost > 0) {
                        this.addToTop(new GainEnergyAction(card.cost));
                    }
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
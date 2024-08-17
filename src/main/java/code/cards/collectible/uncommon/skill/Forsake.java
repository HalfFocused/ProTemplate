package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.ForsakePower;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Forsake extends AbstractEasyCard {
    public final static String ID = makeID(Forsake.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public Forsake() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                CardUtil.cardsDrawnWithForsakeThisTurn.addAll(DrawCardAction.drawnCards);
                isDone = true;
            }
        }));
        addToBot(new ApplyPowerAction(p,p, new ForsakePower(p)));
    }

    public void upp() {
        isEthereal = false;
    }
}
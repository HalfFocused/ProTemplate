package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SuddenClarity extends AbstractEasyCard {
    public final static String ID = makeID(SuddenClarity.class.getSimpleName());

    public SuddenClarity() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c : DrawCardAction.drawnCards){
                    if(c.isEthereal){
                        c.setCostForTurn(0);
                    }
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
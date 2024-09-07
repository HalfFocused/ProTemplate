package code.cards.collectible.rare.skill;

import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class BeautyRains extends AbstractEasyCard {
    public final static String ID = makeID("BeautyRains");
    // intellij stuff skill, self, rare, , , , , 3, 2

    public BeautyRains() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PredictAction(magicNumber, card -> {
            for(AbstractCard c : PredictAction.getSimulatedHand()){
                if(c.type == card.type && !(c.uuid == card.uuid)){
                    return false;
                }
            }
            return true;
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
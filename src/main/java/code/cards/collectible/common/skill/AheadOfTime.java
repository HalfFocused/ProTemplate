package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.DisplacedTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AheadOfTime extends AbstractEasyCard {
    public final static String ID = makeID("AheadOfTime");
    // intellij stuff skill, self, uncommon, , , , , 2, 

    public AheadOfTime() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(!p.drawPile.isEmpty()){
                    addToTop(new MakeTempCardInHandAction(p.drawPile.getTopCard().makeStatEquivalentCopy()));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}
package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Endurance extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(Endurance.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 10, 4, , 

    public Endurance() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        isEthereal = true;
        baseBlock = block = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void onForget() {
        AbstractCard copy = makeStatEquivalentCopy();
        if(baseBlock >= 2){
            copy.baseBlock = baseBlock - 2;
        }else{
            copy.baseBlock = 0;
        }
        addToBot(new MakeTempCardInDrawPileAction(copy, 1, true, true));
    }
}
package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HollowGrasp extends AbstractEasyCard {
    public final static String ID = makeID(HollowGrasp.class.getSimpleName());

    public HollowGrasp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 13;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(m, p, magicNumber, AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new MakeTempCardInDrawPileAction(new Dazed(), 1, true, true));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}
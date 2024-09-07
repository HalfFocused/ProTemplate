package code.cards.collectible.uncommon.attack;

import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SawItComing extends AbstractEasyCard {
    public final static String ID = makeID("SawItComing");
    // intellij stuff skill, self, uncommon, , , 8, 3, 2, 1

    public SawItComing() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        addToBot(new PredictAction(magicNumber, card-> card.baseBlock >= 0));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
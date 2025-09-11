package code.cards.collectible.common.attack;

import static code.ModFile.makeID;

import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HazyStrike extends AbstractEasyCard {
    public final static String ID = makeID(HazyStrike.class.getSimpleName());


    public HazyStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        tags.add(CardTags.STRIKE);
        baseDamage = damage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new MakeTempCardInDiscardAction(new Dazed(), 1));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
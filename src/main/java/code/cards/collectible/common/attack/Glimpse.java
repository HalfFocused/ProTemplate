package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Glimpse extends AbstractEasyCard {
    public final static String ID = makeID(Glimpse.class.getSimpleName());
    // intellij stuff attack, enemy, common, 12, 4, , , , 

    public Glimpse() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 1, true, true));
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("temporary")); //TODO: Account for localization files
    }
}
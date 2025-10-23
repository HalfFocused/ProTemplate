package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Euphoria extends AbstractEasyCard {
    public final static String ID = makeID(Euphoria.class.getSimpleName());

    public Euphoria() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                if(!DrawCardAction.drawnCards.isEmpty() && DrawCardAction.drawnCards.get(0).isEthereal){
                    addToTop(new DrawCardAction(magicNumber));
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
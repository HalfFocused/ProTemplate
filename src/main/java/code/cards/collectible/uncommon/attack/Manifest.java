package code.cards.collectible.uncommon.attack;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Manifest extends AbstractEasyCard {
    public final static String ID = makeID("Manifest");
    // intellij stuff attack, enemy, uncommon, 11, 2, , , 1, 1

    public Manifest() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c : DrawCardAction.drawnCards){
                    c.setCostForTurn(0);
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
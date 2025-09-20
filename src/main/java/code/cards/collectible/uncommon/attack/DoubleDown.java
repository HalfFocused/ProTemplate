package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DoubleDown extends AbstractEasyCard {
    public final static String ID = makeID(DoubleDown.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 3, 1, 3, 1, , 

    public DoubleDown() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, CardUtil.randomSlash());
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractPower pow : m.powers){
                    if(pow.type == AbstractPower.PowerType.DEBUFF){
                        dmgTop(m, CardUtil.randomSlash());
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
    }
}
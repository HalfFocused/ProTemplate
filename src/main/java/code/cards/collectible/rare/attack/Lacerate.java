package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Lacerate extends AbstractEasyCard {
    public final static String ID = makeID(Lacerate.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 3, 

    public Lacerate() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractPower pow : m.powers){
                    if(CardUtil.isPowerTurnBased(pow)){
                        addToTop(new ApplyPowerAction(m, p, pow, magicNumber));
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
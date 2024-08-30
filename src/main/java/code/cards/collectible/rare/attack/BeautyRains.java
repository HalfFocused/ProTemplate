package code.cards.collectible.rare.attack;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BeautyRains extends AbstractEasyCard {
    public final static String ID = makeID("BeautyRains");
    // intellij stuff attack, all_enemy, rare, 16, 6, , , 2, 

    public BeautyRains() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard card : p.hand.group){
                    addToTop(new LapseCardAction(card, p.hand));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}
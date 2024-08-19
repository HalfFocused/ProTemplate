package code.cards.collectible.uncommon.attack;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Manifest extends AbstractEasyCard {
    public final static String ID = makeID("Manifest");
    // intellij stuff attack, enemy, uncommon, 11, 2, , , 1, 1

    public Manifest() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.drawPile.size() + p.discardPile.size() == 0) {// 26
                    this.isDone = true;
                    return;
                }
                if (p.drawPile.size() > magicNumber) {
                    for(int i = 0; i < magicNumber; i++){
                        this.addToTop(new LapseCardAction(p.drawPile.getNCardFromTop(i), p.drawPile));
                    }
                }else{
                    for(AbstractCard card : p.drawPile.group){
                        this.addToTop(new LapseCardAction(card, p.drawPile));
                    }
                }
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);

    }
}
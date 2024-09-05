package code.cards.collectible.uncommon.attack;

import code.actions.RhythmicStepsAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RhythmicSteps extends AbstractEasyCard {
    public final static String ID = makeID("RhythmicSteps");
    // intellij stuff attack, all_enemy, uncommon, 4, 4, , , , 

    public RhythmicSteps() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        this.addToBot(new DrawCardAction(1, new RhythmicStepsAction()));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
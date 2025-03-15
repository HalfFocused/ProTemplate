package code.cards.collectible.common.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Distort extends AbstractEasyCard {
    public final static String ID = makeID(Distort.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , 2, 2

    public Distort() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), magicNumber));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(upgraded) {
                    this.addToTop(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
                }
                isDone = true;
            }
        });
        this.addToBot(new DrawCardAction(secondMagic));
    }

    public void upp() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }
}
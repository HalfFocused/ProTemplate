package code.cards.collectible.common.attack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import code.ModFile;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.swing.*;

public class Glimpse extends AbstractEasyCard {
    public final static String ID = makeID("Glimpse");
    // intellij stuff attack, enemy, common, 12, 4, , , , 

    public Glimpse() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new MakeTempCardInDiscardAction(cardsToPreview, 1));
    }

    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("dream")); //TODO: Account for localization files
    }
}
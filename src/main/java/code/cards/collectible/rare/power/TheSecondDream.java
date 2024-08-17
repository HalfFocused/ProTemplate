package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.TheSecondDreamPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheSecondDream extends AbstractEasyCard {
    public final static String ID = makeID(TheSecondDream.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , , 

    public TheSecondDream() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p, new TheSecondDreamPower(p)));
    }

    public void upp() {
        isEthereal = false;
    }
}
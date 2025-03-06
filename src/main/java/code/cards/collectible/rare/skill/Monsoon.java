package code.cards.collectible.rare.skill;

import code.actions.MonsoonAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Monsoon extends AbstractEasyCard {
    public final static String ID = makeID("Monsoon");
    // intellij stuff skill, self, rare, , , , , 3, 2

    public Monsoon() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        //CardModifierManager.addModifier(this, new FlashbackModifier(FlashbackModifier.BEAUTY_RAINS));
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MonsoonAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
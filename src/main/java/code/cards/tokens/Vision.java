package code.cards.tokens;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.charUtil.CardUtil.randomSlash;

import code.util.charUtil.mods.DreamModifier;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vision extends AbstractEasyCard {
    public final static String ID = makeID(Vision.class.getSimpleName());

    public Vision() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 16;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new DreamModifier(true,3));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, randomSlash());
        //this.addToBot(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
    }
}
package code.cards.tokens;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.mods.DreamModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DreamBlade extends AbstractEasyCard {
    public final static String ID = makeID(DreamBlade.class.getSimpleName());
    // intellij stuff attack, enemy, special, 12, 4, , , , 

    public DreamBlade() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 12;
        CardModifierManager.addModifier(this, new DreamModifier(true,3));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeDamage(4);
    }
}
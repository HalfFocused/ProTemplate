package code.cards.collectible.uncommon.attack;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.AbstractExploitCard;
import code.util.charUtil.mods.FlashbackModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleDown extends AbstractExploitCard {
    public final static String ID = makeID(DoubleDown.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 3, 1, 3, 1, , 

    public DoubleDown() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 1);
        baseDamage = damage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        exploitEffects(m, gameActionManager -> {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        });
    }

    public void upp() {
        upgradeDamage(2);
    }
}
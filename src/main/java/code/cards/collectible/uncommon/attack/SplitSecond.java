package code.cards.collectible.uncommon.attack;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.OnWarpCard;
import code.util.charUtil.mods.FlashbackModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SplitSecond extends AbstractEasyCard {
    public final static String ID = makeID(SplitSecond.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 3, 1, 3, 1, , 

    public SplitSecond() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseBlock = block = 2;
        CardModifierManager.addModifier(this, new FlashbackModifier(FlashbackModifier.SPLIT_SECOND));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(1);
    }
}
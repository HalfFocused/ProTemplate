package code.cards.collectible.common.skill;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.mods.FlashbackModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NeverEnough extends AbstractEasyCard {
    public final static String ID = makeID("NeverEnough");

    public NeverEnough() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 8;
        CardModifierManager.addModifier(this, new FlashbackModifier(FlashbackModifier.NEVER_ENOUGH));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}
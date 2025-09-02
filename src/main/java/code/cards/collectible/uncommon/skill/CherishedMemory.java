package code.cards.collectible.uncommon.skill;

import code.actions.FlashbackAction;
import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class CherishedMemory extends AbstractEasyCard {
    public final static String ID = makeID("CherishedMemory");
    // intellij stuff skill, self, uncommon, , , 8, 3, 2, 1

    public CherishedMemory() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new FlashbackAction(magicNumber, card -> card.rarity == CardRarity.RARE || card.rarity == CardRarity.UNCOMMON));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
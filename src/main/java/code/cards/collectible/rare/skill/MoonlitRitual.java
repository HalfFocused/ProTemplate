package code.cards.collectible.rare.skill;

import code.actions.BlessRandomCardsInDrawPileAction;
import code.cards.AbstractEasyCard;
import code.util.charUtil.mods.BlessingModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class MoonlitRitual extends AbstractEasyCard {
    public final static String ID = makeID(MoonlitRitual.class.getSimpleName());
    // intellij stuff SKILL, SELF, RARE, , , , , 1, 

    public MoonlitRitual() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        isInnate = false;
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        BlessingModifier blessingModifier = new BlessingModifier("MoonlitRitualBlessing",
                cardStrings.EXTENDED_DESCRIPTION[0],
                cardStrings.EXTENDED_DESCRIPTION[1],
                action -> {
                    action.addToBottom(new GainEnergyAction(1));
                }, null, null);
        AbstractDungeon.actionManager.addToBottom(new BlessRandomCardsInDrawPileAction(blessingModifier, 1, null));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        isInnate = true;
    }
}
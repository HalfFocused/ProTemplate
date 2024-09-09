package code.cards.collectible.uncommon.skill;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.mods.EtherealModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fathom extends AbstractEasyCard {
    public final static String ID = makeID("Fathom");
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public Fathom() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c : DrawCardAction.drawnCards){
                    CardModifierManager.addModifier(c, new EtherealModifier());
                }
                isDone = true;
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}
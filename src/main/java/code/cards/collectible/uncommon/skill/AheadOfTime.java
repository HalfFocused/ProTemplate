package code.cards.collectible.uncommon.skill;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.mods.EtherealModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AheadOfTime extends AbstractEasyCard {
    public final static String ID = makeID(AheadOfTime.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 2, 

    public AheadOfTime() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(!p.drawPile.isEmpty()){
                    AbstractCard c = p.drawPile.getTopCard().makeStatEquivalentCopy();
                    CardModifierManager.addModifier(c, new EtherealModifier());
                    addToTop(new MakeTempCardInHandAction(c));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}
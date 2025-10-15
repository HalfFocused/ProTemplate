package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LetGo extends AbstractEasyCard {
    public final static String ID = makeID(LetGo.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 2, 3

    public LetGo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(!p.drawPile.isEmpty()) {
                    AbstractCard toExhaust = p.drawPile.getTopCard();
                    toExhaust.current_x = CardGroup.DRAW_PILE_X;
                    toExhaust.current_y = CardGroup.DRAW_PILE_Y;
                    toExhaust.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                    toExhaust.target_y = (float) Settings.HEIGHT / 2.0F;
                    addToTop(new ExhaustSpecificCardAction(toExhaust, p.drawPile));
                }
                isDone = true;
            }
        });
        addToBot(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
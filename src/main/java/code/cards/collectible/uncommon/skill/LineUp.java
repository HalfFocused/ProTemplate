package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LineUp extends AbstractEasyCard {
    public final static String ID = makeID(LineUp.class.getSimpleName());

    public LineUp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        addToBot(new SelectCardsAction(
            AbstractDungeon.player.drawPile.group,
            1,
            cardStrings.EXTENDED_DESCRIPTION[0],
            cards -> {
                for(AbstractCard c : cards){
                    AbstractDungeon.player.drawPile.removeCard(c);
                    AbstractDungeon.player.drawPile.addToTop(c);
                }
            }
        ));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
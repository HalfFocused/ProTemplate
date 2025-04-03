package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Euphoria extends AbstractEasyCard {
    public final static String ID = makeID("Euphoria");

    public Euphoria() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        this.addToBot(new SelectCardsInHandAction(1,
            cardStrings.EXTENDED_DESCRIPTION[0],
            abstractCards -> {
                for(AbstractCard card : abstractCards){
                    for(int i = 0; i < magicNumber; i++){
                        addToTop(new MakeTempCardInDrawPileAction(card.makeStatEquivalentCopy(), 1, true, true, false));
                    }
                    addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                }
            }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
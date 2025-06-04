package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Keepsake extends AbstractEasyCard {
    public final static String ID = makeID("Keepsake");

    public Keepsake() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 1;
        selfRetain = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, CardUtil.randomSlash());
        this.addToTop(new SelectCardsAction(
                AbstractDungeon.player.discardPile.group,
                magicNumber,
                cardStrings.EXTENDED_DESCRIPTION[0],
                false,
                card->true,
                cards -> {
                    for(AbstractCard card : cards){
                        addToBot(new DiscardToHandAction(card));
                    }
                }
        ));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
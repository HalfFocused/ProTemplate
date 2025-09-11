package code.cards.collectible.uncommon.skill;

import code.actions.LapseCardAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EtchedInBlood extends AbstractEasyCard {
    public final static String ID = makeID(EtchedInBlood.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public EtchedInBlood() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], cards -> {
            for(AbstractCard card : cards){
                this.addToBot(new LapseCardAction(card, AbstractDungeon.player.hand));
            }
        }));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
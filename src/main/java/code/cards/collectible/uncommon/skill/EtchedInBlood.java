package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.EtchedInBloodPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;
import java.util.function.Consumer;

public class EtchedInBlood extends AbstractEasyCard {
    public final static String ID = makeID("EtchedInBlood");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public EtchedInBlood() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    private Consumer<List<AbstractCard>> exhaustAndApplyPower = cards -> {
        for(AbstractCard card : cards){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EtchedInBloodPower(AbstractDungeon.player, card, magicNumber)));
            this.addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
        }
    };

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], exhaustAndApplyPower));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
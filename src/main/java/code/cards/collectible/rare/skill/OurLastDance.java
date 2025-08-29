package code.cards.collectible.rare.skill;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import code.actions.FlashbackAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OurLastDance extends AbstractEasyCard {
    public final static String ID = makeID("OurLastDance");

    public OurLastDance() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FlashbackAction(magicNumber, null, selectedCards -> {
            for(AbstractCard choice : selectedCards){
                choice.setCostForTurn(0);
                CardModifierManager.addModifier(choice, new ExhaustMod());
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
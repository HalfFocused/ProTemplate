package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.cards.tokens.Release;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Restraint extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID("Restraint");
    // intellij stuff skill, self, uncommon, , , , , 2, 

    public Restraint() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.cardsToPreview = new Release();
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void onForget() {
        this.addToBot(new MakeTempCardInHandAction(new Release()));
    }
}
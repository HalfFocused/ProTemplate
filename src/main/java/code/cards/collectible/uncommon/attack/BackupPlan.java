package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BackupPlan extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(BackupPlan.class.getSimpleName());

    public BackupPlan() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        isEthereal = true;
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }

    @Override
    public void onForget() {
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters){
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -this.magicNumber), -this.magicNumber));// 36
            if (m != null && !m.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GainStrengthPower(m, this.magicNumber), this.magicNumber));// 39
            }
        }
    }
}
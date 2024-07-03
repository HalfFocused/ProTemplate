package code.cards.collectible.common.skill;

import basemod.ReflectionHacks;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Patience extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(Patience.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, , 

    public Patience() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    @Override
    public void onForget() {
        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            VulnerablePower endOfTurnVulnerable = new VulnerablePower(mo, this.magicNumber, false);
            ReflectionHacks.setPrivate(endOfTurnVulnerable, VulnerablePower.class, "justApplied", true);
            this.addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, endOfTurnVulnerable, this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.MomentOfTruthPower;
import code.powers.UnfamiliarMemoryPower;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.ApplyBulletTimeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UnfamiliarMemory extends AbstractEasyCard {
    public final static String ID = makeID(UnfamiliarMemory.class.getSimpleName());

    public UnfamiliarMemory() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower power = AbstractDungeon.player.getPower(UnfamiliarMemoryPower.POWER_ID);
                if(power == null){
                    addToTop(new ApplyPowerAction(p,p, new UnfamiliarMemoryPower(p,1),1));
                }else{
                    addToTop(new RemoveSpecificPowerAction(p,p, power));
                    addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
                }
                isDone = true;
            }
        });
    }

    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractPower pow : AbstractDungeon.player.powers){
            if(pow instanceof UnfamiliarMemoryPower){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void applyPowers(){
        super.applyPowers();
        this.exhaustOnUseOnce = false;
        for(AbstractPower pow : AbstractDungeon.player.powers){
            if (pow instanceof UnfamiliarMemoryPower) {
                this.exhaustOnUseOnce = true;
                break;
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
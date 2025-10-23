package code.cards.tokens;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.charUtil.CardUtil.randomSlash;

import code.powers.TheStarsAlignedPower;
import code.powers.TrueSightPower;
import code.util.charUtil.mods.TemporaryModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Vision extends AbstractEasyCard {
    public final static String ID = makeID(Vision.class.getSimpleName());

    private boolean playedBefore = false;

    public Vision() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 16;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new TemporaryModifier(true,3));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, randomSlash());
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                playedBefore = true;
                isDone = true;
            }
        });
    }

    @Override
    public void applyPowers(){

        if(!freeToPlayOnce && !playedBefore && AbstractDungeon.player.hasPower(TheStarsAlignedPower.POWER_ID)){
            freeToPlayOnce = true;
        }

        AbstractPower trueSightPower = AbstractDungeon.player.getPower(TrueSightPower.POWER_ID);
        if(trueSightPower != null){
            int realBaseDamage = this.baseDamage;
            this.baseDamage += trueSightPower.amount;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }else{
            super.applyPowers();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower trueSightPower = AbstractDungeon.player.getPower(TrueSightPower.POWER_ID);

        if(trueSightPower != null) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage += trueSightPower.amount;
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }else{
            super.calculateCardDamage(mo);
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}
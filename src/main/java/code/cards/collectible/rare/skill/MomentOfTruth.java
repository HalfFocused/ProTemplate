package code.cards.collectible.rare.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.MomentOfTruthPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ApplyBulletTimeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MomentOfTruth extends AbstractEasyCard {
    public final static String ID = makeID(MomentOfTruth.class.getSimpleName());
    // intellij stuff skill, self, rare, , , 4, 3, , 

    public MomentOfTruth() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower power = AbstractDungeon.player.getPower(MomentOfTruthPower.POWER_ID);
                if(power == null){
                    addToTop(new ApplyPowerAction(p,p, new MomentOfTruthPower(p,1),1));
                }else{
                    addToTop(new ApplyBulletTimeAction());
                    addToTop(new RemoveSpecificPowerAction(p,p, power));
                }
                isDone = true;
            }
        });
    }

    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractPower pow : AbstractDungeon.player.powers){
            if(pow instanceof MomentOfTruthPower){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
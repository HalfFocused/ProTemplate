package code.cards.collectible.rare.skill;

import code.actions.AllEnemiesLoseHPAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.powers.LikeClockworkPower;
import code.util.charUtil.mods.DreamModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sun.util.resources.cldr.fr.CalendarData_fr_MG;

public class LikeClockwork extends AbstractEasyCard {
    public final static String ID = makeID(LikeClockwork.class.getSimpleName());
    // intellij stuff skill, self, rare, , , 4, 3, , 

    public LikeClockwork() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 9;
        //card draw
        baseMagicNumber = magicNumber = 2;
        //energy gain
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower power = AbstractDungeon.player.getPower(LikeClockworkPower.POWER_ID);
                if(power == null){
                    addToTop(new ApplyPowerAction(p,p, new LikeClockworkPower(p,1),1));
                }else{
                    addToTop(new DrawCardAction(magicNumber));
                    addToTop(new GainEnergyAction(secondMagic));
                    addToTop(new RemoveSpecificPowerAction(p,p, power));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}
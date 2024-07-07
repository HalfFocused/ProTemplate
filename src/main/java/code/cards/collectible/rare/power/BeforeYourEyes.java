package code.cards.collectible.rare.power;

import code.cards.AbstractEasyCard;
import code.effects.BeforeYourEyesEffect;
import code.powers.BeforeYourEyesPower;
import code.powers.TimeStopPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;

import static code.ModFile.makeID;

public class BeforeYourEyes extends AbstractEasyCard {
    public final static String ID = makeID(BeforeYourEyes.class.getSimpleName());

    public BeforeYourEyes() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(int i = 0; i < 10; i++){
                    AbstractDungeon.effectsQueue.add(new BeforeYourEyesEffect());
                }
                this.isDone = true;
            }
        });

        addToBot(new ApplyPowerAction(p, p, new BeforeYourEyesPower(p, magicNumber)));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
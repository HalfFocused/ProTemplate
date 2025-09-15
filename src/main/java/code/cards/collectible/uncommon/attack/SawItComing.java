package code.cards.collectible.uncommon.attack;

import code.actions.FlashbackAction;
import code.actions.PredictAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SawItComing extends AbstractEasyCard {
    public final static String ID = makeID(SawItComing.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, 2, 1

    public SawItComing() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseBlock = block = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(!m.isDeadOrEscaped() && m.hasPower(ForetoldPower.POWER_ID)){
                    blckTop();
                }
                isDone = true;
            }
        });
    }

    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
            if(mo.hasPower(ForetoldPower.POWER_ID)){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}
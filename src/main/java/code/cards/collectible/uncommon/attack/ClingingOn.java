package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClingingOn extends AbstractEasyCard {
    public final static String ID = makeID("ClingingOn");

    public ClingingOn() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseBlock = block = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        blck();
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        exhaust = !CardUtil.hasPlayedEtherealCardThisTurn();
    }

    public void triggerOnGlowCheck(){
        this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        if(CardUtil.hasPlayedEtherealCardThisTurn()){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
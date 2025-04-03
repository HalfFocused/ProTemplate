package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.effects.RecklessAbandonEffect;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RecklessAbandon extends AbstractEasyCard {
    public final static String ID = makeID("RecklessAbandon");
    // intellij stuff attack, all_enemy, rare, 16, 4, , , , 

    public RecklessAbandon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = 14;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < CardUtil.etherealCardsInHand() + 1; ++i) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractMonster hit = AbstractDungeon.getRandomMonster();
                    calculateCardDamage(hit);
                    dmgTop(hit, randomSlash());
                    this.addToTop(new VFXAction(new RecklessAbandonEffect(hit.hb.cX, hit.hb.cY, Color.RED.cpy())));
                    this.addToTop(new SFXAction("ATTACK_HEAVY", MathUtils.random(0.2F, 0.5F)));
                    isDone = true;
                }
            });
        }
    }
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        int times = CardUtil.etherealCardsInHand() + 1;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + times;
        if(times == 1){
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        }else{
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        this.initializeDescription();
    }

    private AbstractGameAction.AttackEffect randomSlash(){
        switch (MathUtils.random(3)){
            case 1:
                return AbstractGameAction.AttackEffect.SLASH_HEAVY;
            case 2:
                return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
            case 3:
                return AbstractGameAction.AttackEffect.SMASH;
        }
        return AbstractGameAction.AttackEffect.SLASH_HEAVY;
    }
}
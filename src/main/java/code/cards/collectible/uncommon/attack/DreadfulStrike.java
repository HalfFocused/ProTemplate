package code.cards.collectible.uncommon.attack;

import code.TheDisplaced;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class DreadfulStrike extends AbstractEasyCard {
    public final static String ID = makeID(DreadfulStrike.class.getSimpleName());
    // intellij stuff attack, enemy, common, 14, 4, , , , 

    public DreadfulStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 14;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        }

        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else {
            if(!CardUtil.hasEtherealCardInHand(p)) {
                if(AbstractDungeon.player instanceof TheDisplaced && !CardUtil.inTheSecondDream()){
                    this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[MathUtils.random(0, cardStrings.EXTENDED_DESCRIPTION.length - 1)];
                }else{
                    this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                }
                return false;
            }
        }
        return true;
    }
    public void upp() {
        upgradeDamage(4);
    }
}
package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.OnWarpCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flashback extends AbstractEasyCard implements OnWarpCard {
    public final static String ID = makeID(Flashback.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 3, 1, 3, 1, , 

    public Flashback() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 2;
        baseBlock = block = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(1);
    }

    @Override
    public void onWarp(CardGroup group) {
        if(group.type == CardGroup.CardGroupType.DISCARD_PILE){
            this.addToBot(new DiscardToHandAction(this));
        }
    }
}
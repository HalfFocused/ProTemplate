package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.OnWarpCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CatchUp extends AbstractEasyCard implements OnWarpCard {
    public final static String ID = makeID(CatchUp.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 18, 6, , , , 

    public CatchUp() {
        super(ID, 5, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 16;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void onWarp(CardGroup group) {
        this.updateCost(-1);
    }
}
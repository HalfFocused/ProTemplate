package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.CardUtil;
import code.util.charUtil.OnWarpCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CatchUp extends AbstractEasyCard implements OnWarpCard {
    public final static String ID = makeID(CatchUp.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 18, 6, , , , 

    public CatchUp() {
        super(ID, 5, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 18;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public void onWarp(CardGroup group) {
        this.updateCost(-1);
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard tmp = new CatchUp();
        if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            tmp.updateCost(-CardUtil.warpsThisCombat);
        }

        return tmp;
    }
}
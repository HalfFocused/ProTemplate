package code.cards.collectible.uncommon.attack;

import code.TheDisplaced;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BranchTimeline extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(BranchTimeline.class.getSimpleName());

    public BranchTimeline() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void onForget() {
        this.addToBot(new MakeTempCardInHandAction(CardUtil.filteredRandomCard(card->(card.color == TheDisplaced.Enums.DISPLACED_COLOR && card.type == CardType.ATTACK)).getRandomCard(AbstractDungeon.cardRandomRng)));
        this.addToBot(new MakeTempCardInHandAction(CardUtil.filteredRandomCard(card->(card.color == TheDisplaced.Enums.DISPLACED_COLOR && card.type == CardType.SKILL)).getRandomCard(AbstractDungeon.cardRandomRng)));
    }
}
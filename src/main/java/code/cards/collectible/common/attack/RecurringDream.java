package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RecurringDream extends AbstractEasyCard {
    public final static String ID = makeID(RecurringDream.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 18, 6, , , , 

    public RecurringDream() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 13;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        for(AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(card instanceof RecurringDream){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }
        for(AbstractCard card : CardUtil.cardsPlayedLastTurn){
            if(card instanceof RecurringDream){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (card instanceof RecurringDream) {
                setCostForTurn(0);
                return;
            }
        }
        for (AbstractCard card : CardUtil.cardsPlayedLastTurn) {
            if (card instanceof RecurringDream) {
                setCostForTurn(0);
                return;
            }
        }
    }
    public void upp() {
        upgradeDamage(4);
    }
}
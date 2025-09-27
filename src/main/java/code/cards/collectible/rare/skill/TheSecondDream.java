package code.cards.collectible.rare.skill;

import code.actions.TheSecondDreamAction;
import code.cards.AbstractEasyCard;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class TheSecondDream extends AbstractEasyCard {
    public final static String ID = makeID(TheSecondDream.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 10, 4, , , ,

    public TheSecondDream() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        //baseDamage = damage = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TheSecondDreamAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        if(CardUtil.etherealCardsPlayedThisTurn() >= 3){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    /*
    public void applyPowers() {
        MultiCardPreview.clear(this);
        if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.actionManager != null) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                if (c.isEthereal) {
                    MultiCardPreview.add(this, c.makeStatEquivalentCopy());
                }
            }
        }
    }
     */
}
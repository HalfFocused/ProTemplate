package code.cards.collectible.uncommon.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UnfamiliarMemory extends AbstractEasyCard {
    public final static String ID = makeID(UnfamiliarMemory.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public UnfamiliarMemory() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for(AbstractCard c : CardUtil.cardsPlayedLastTurn){
                    if(c instanceof UnfamiliarMemory){
                        addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
                        break;
                    }
                }
                isDone = true;
            }
        });
    }

    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractCard c : CardUtil.cardsPlayedLastTurn){
            if(c instanceof UnfamiliarMemory){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
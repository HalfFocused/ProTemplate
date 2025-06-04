package code.cards.collectible.uncommon.skill;

import code.actions.PredictAction;
import code.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class PossibilityStorm extends AbstractEasyCard {
    public final static String ID = makeID("PossibilityStorm");
    // intellij stuff skill, self, uncommon, , , 8, 3, 2, 1

    public PossibilityStorm() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new PredictAction(magicNumber, card-> {
            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
                if(card.canUse(p, m)){
                    return true;
                }
            }
            return false;
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
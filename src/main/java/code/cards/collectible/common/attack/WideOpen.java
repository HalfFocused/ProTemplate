package code.cards.collectible.common.attack;

import static code.ModFile.makeID;

import code.cards.AbstractExploitCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WideOpen extends AbstractExploitCard {
    public final static String ID = makeID(WideOpen.class.getSimpleName());


    public WideOpen() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        exploitEffects(m, gameActionManager -> {
            gameActionManager.addToBottom(new DrawCardAction(magicNumber));
        });
    }

    public void upp() {
        upgradeDamage(2);
    }
}
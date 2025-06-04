package code.cards.collectible.uncommon.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class HowlingWinds extends AbstractEasyCard {
    public final static String ID = makeID("HowlingWinds");
    // intellij stuff skill, enemy, uncommon, , , , , 2, 1

    public HowlingWinds() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(CardUtil.hasPlayedEtherealCardThisTurn()){
                    addToTop(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
                }
                isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();

        if(CardUtil.hasPlayedEtherealCardThisTurn()){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
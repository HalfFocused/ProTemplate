package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.ForgetCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class Her extends AbstractEasyCard implements ForgetCard {
    public final static String ID = makeID(Her.class.getSimpleName());
    // intellij stuff attack, all_enemy, rare, 16, 6, , , 2, 

    public Her() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 20;
        baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        isEthereal = true;
        this.cardsToPreview = new VoidCard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SMASH);
    }

    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public void onForget() {
        this.addToTop(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, cardStrings.EXTENDED_DESCRIPTION[MathUtils.random(0, cardStrings.EXTENDED_DESCRIPTION.length - 1)], true));

    }
}
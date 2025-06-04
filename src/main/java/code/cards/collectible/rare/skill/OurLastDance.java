package code.cards.collectible.rare.skill;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OurLastDance extends AbstractEasyCard {
    public final static String ID = makeID("OurLastDance");

    public OurLastDance() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SelectCardsAction(
            AbstractDungeon.player.discardPile.group,
            magicNumber,
            cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1],
            true,
            card->true,
            selectedCards->{
                for(AbstractCard card : selectedCards){
                    AbstractDungeon.player.discardPile.group.remove(card);
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    AbstractDungeon.player.limbo.group.add(card);
                    card.current_y = -200.0F * Settings.scale;
                    card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    card.target_y = (float) Settings.HEIGHT / 2.0F;
                    card.targetAngle = 0.0F;
                    card.lighten(false);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.applyPowers();
                    card.exhaustOnUseOnce = true;
                    this.addToTop(new NewQueueCardAction(card, true, true, true));
                    this.addToTop(new UnlimboAction(card));
                }
            }
        ));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
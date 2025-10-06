package code.cards.tokens;

import basemod.helpers.CardModifierManager;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.charUtil.CardUtil.randomSlash;

import code.powers.TheStarsAlignedPower;
import code.util.charUtil.mods.TemporaryModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Vision extends AbstractEasyCard {
    public final static String ID = makeID(Vision.class.getSimpleName());

    private boolean playedBefore = false;

    public Vision() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 16;
        baseMagicNumber = magicNumber = 1;
        /*this check is a little unnecessary since the card will be updated
        immediately once hitting the zone it enters.
        but it makes the display of the card cost 0 as it is created.
         */
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(TheStarsAlignedPower.POWER_ID)) {
            freeToPlayOnce = true;
        }
        CardModifierManager.addModifier(this, new TemporaryModifier(true,3));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, randomSlash());
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                playedBefore = true;
                isDone = true;
            }
        });
    }

    @Override
    public void update(){
        super.update();
        if(!freeToPlayOnce && !playedBefore && AbstractDungeon.player.hasPower(TheStarsAlignedPower.POWER_ID)){
            freeToPlayOnce = true;
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}
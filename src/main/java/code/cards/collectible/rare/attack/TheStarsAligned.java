package code.cards.collectible.rare.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.cards.tokens.Vision;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Objects;

public class TheStarsAligned extends AbstractEasyCard {
    public final static String ID = makeID(TheStarsAligned.class.getSimpleName());

    public TheStarsAligned() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 1;
        this.cardsToPreview = new Vision();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> visions = new ArrayList<>();
                for(AbstractCard c : p.drawPile.group){
                    if(Objects.equals(c.cardID, Vision.ID)){
                        visions.add(c);
                    }
                }
                for(int i = 0; i < magicNumber; i++) {
                    if (!visions.isEmpty()) {
                        AbstractCard card = visions.get(AbstractDungeon.cardRandomRng.random(0, visions.size() - 1));
                        visions.remove(card);
                        AbstractDungeon.player.drawPile.group.remove(card);
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
                        this.addToTop(new NewQueueCardAction(card, true, false, true));
                        this.addToTop(new UnlimboAction(card));
                    }else{
                        break;
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void initializeDescription(){
        super.initializeDescription();
        this.keywords.add(makeID("temporary")); //TODO: Account for localization files
    }
}
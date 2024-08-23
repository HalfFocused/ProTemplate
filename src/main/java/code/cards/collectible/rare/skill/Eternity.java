package code.cards.collectible.rare.skill;

import basemod.ReflectionHacks;
import code.TheDisplaced;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;
import static code.util.Wiz.*;

import code.util.CardArtRoller;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class Eternity extends AbstractEasyCard {
    public final static String ID = makeID(Eternity.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , ,
    public Eternity() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard card = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = current_x;
                tmp.current_y = current_y;
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = (float)Settings.HEIGHT / 2.0F;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, tmp.energyOnUse, true, true), true);
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBaseCost(1);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else {
            if(AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
                if(AbstractDungeon.player instanceof TheDisplaced){
                    this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[MathUtils.random(1, cardStrings.EXTENDED_DESCRIPTION.length - 1)];
                }else{
                    this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void applyPowers(){
        if(!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()){
            AbstractCard lastCard = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
            this.target = lastCard.target;
            if(!(lastCard instanceof Eternity)) { //Without this, you crash with multiple Eternities in hand.
                this.cardsToPreview = lastCard.makeStatEquivalentCopy();
                this.cardsToPreview.applyPowers();
            }else{
                this.cardsToPreview = null;
            }
        }else{
            this.target = CardTarget.SELF;
        }


        if(!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + colorString(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).name);
            this.initializeDescription();
        }
    }

    private static String colorString(String input) {
        StringBuilder spaceBuffer = new StringBuilder();
        StringBuilder newMsg = new StringBuilder();
        String[] var2 = input.split(" ");
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String word = var2[var4];
            newMsg.append("*").append(word).append(' ');
            spaceBuffer.append('\u00A0');
        }
        spaceBuffer.append(' ');

        return spaceBuffer + (newMsg.toString().trim());
    }
}
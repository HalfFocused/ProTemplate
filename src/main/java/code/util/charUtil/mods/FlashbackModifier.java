package code.util.charUtil.mods;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import code.ModFile;
import code.util.TexLoader;
import code.util.Wiz;
import code.util.charUtil.CardUtil;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class FlashbackModifier extends AbstractCardModifier {
    public static final int NEVER_ENOUGH = 0;
    public static final int SPLIT_SECOND = 1;
    public static final int LAST_LAUGH = 2;
    public static final int THE_STARS_ALIGNED = 3;

    static ArrayList<AbstractCard> previewCards = new ArrayList<>();

    public static final String ID = ModFile.makeID("Flashback");

    private final int type;

    public FlashbackModifier(int typeIn){
        type = typeIn;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FlashbackModifier(type);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean isInherent(AbstractCard card) {
        return true;
    }

    public static void flashback(int type){
        for(AbstractCard card : AbstractDungeon.player.discardPile.group){
            if(!CardModifierManager.getModifiers(card, ID).isEmpty()) {
                AbstractCardModifier mod = CardModifierManager.getModifiers(card, ID).get(0);
                if(mod instanceof FlashbackModifier){
                    if(((FlashbackModifier) mod).type == type){
                        AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(card));
                    }
                }
            }
        }
    }

    @Override
    public void onInitialApplication(AbstractCard card){

    }

    @Override
    public boolean shouldApply(AbstractCard card){

        return true;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {

    }

    @Override
    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {

    }
}

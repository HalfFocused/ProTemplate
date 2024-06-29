package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import code.ModFile;
import code.actions.BlessRandomCardsInDrawPileAction;
import code.cards.collectible.rare.skill.MoonlitRitual;
import code.cards.collectible.uncommon.attack.Imprinting;
import code.util.charUtil.CardUtil;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BlessingModifier extends AbstractCardModifier {

    private final String blessingId;
    private final String blessingName;
    private final String blessingDescription;
    private final Consumer<GameActionManager> onPlayActions;
    private final Consumer<AbstractCard> onAppliedActions;
    private final Consumer<AbstractCard> onRemoveActions;

    public BlessingModifier(String blessingIdIn, String blessingNameIn, String blessingDescriptionIn, Consumer<GameActionManager> onPlayActionsIn, Consumer<AbstractCard> onAppliedActionsIn, Consumer<AbstractCard> onRemovedActionsIn){
        blessingId = blessingIdIn;
        blessingName = blessingNameIn;
        blessingDescription = blessingDescriptionIn;
        onPlayActions = onPlayActionsIn;
        onAppliedActions = onAppliedActionsIn;
        onRemoveActions = onRemovedActionsIn;
    }
    /*
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + "thestargazer:" + blessingName + ".";
    }

     */

        @Override
    public AbstractCardModifier makeCopy() {
        return new BlessingModifier(blessingId, blessingName, blessingDescription, onPlayActions, onAppliedActions, onRemoveActions);
    }

    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return Collections.singletonList(new TooltipInfo(blessingName, blessingDescription));
    }


    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if(onPlayActions != null) {
            onPlayActions.accept(AbstractDungeon.actionManager);

            /*
             * Hardcoded moonlit ritual stuff because lambda expressions cannot be self referential.
             */

            if(identifier(card).equals("MoonlitRitualBlessing")){
                BlessingModifier blessingModifier = new BlessingModifier("MoonlitRitualBlessing",
                        CardCrawlGame.languagePack.getCardStrings(ModFile.makeID(MoonlitRitual.class.getSimpleName())).EXTENDED_DESCRIPTION[0],
                        CardCrawlGame.languagePack.getCardStrings(ModFile.makeID(MoonlitRitual.class.getSimpleName())).EXTENDED_DESCRIPTION[1],
                        actionManager -> {
                            actionManager.addToBottom(new GainEnergyAction(1));
                        }, null, null);
                AbstractDungeon.actionManager.addToBottom(new BlessRandomCardsInDrawPileAction(blessingModifier, 1, null));
            }
        }
    }

    public void onInitialApplication(AbstractCard card) {
        if(onAppliedActions != null) {
            onAppliedActions.accept(card);
        }
    }

    public void onRemove(AbstractCard card) {
        if(onRemoveActions != null) {
            onRemoveActions.accept(card);
        }
    }



    @Override
    public boolean removeOnCardPlayed(AbstractCard card){
        return !(card instanceof Imprinting) || identifier(card).equals("MoonlitRitualBlessing");
    }

    public boolean removeAtEndOfTurn(AbstractCard card) {
        return false;
    }

    @Override
    public String identifier(AbstractCard card) {
        return blessingId;// 40
    }
}

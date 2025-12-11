package code.patches;

import code.util.charUtil.mods.EtherealModifier;
import basemod.helpers.CardModifierManager;
import code.relics.AmberGemstone;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(
        clz=AbstractCard.class,
        method="makeStatEquivalentCopy"
)
public class MakeCopyPatch {
    public static SpireReturn<AbstractCard> Prefix(AbstractCard __instance) {
        if((__instance.cardID.equals(Dazed.ID) || __instance.cardID.equals(VoidCard.ID)) && AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.player.hasRelic(AmberGemstone.ID) && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            AbstractCard newCard = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if(!newCard.isEthereal){
                CardModifierManager.addModifier(newCard, new EtherealModifier());
            }
            AbstractDungeon.player.getRelic(AmberGemstone.ID).flash();
            return SpireReturn.Return(newCard);
        }
        return SpireReturn.Continue();
    }
}

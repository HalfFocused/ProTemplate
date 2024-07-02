package code.patches;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import code.relics.AmberMedallion;
import code.util.charUtil.CardUtil;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

@SpirePatch(
        clz=AbstractCard.class,
        method="makeStatEquivalentCopy"
)
public class MakeCopyPatch {
    public static SpireReturn<AbstractCard> Prefix(AbstractCard __instance) {
        if(__instance.cardID.equals(Dazed.ID) && AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.player.hasRelic(AmberMedallion.ID) && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            AbstractCard newCard = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if(!newCard.isEthereal){
                CardModifierManager.addModifier(newCard, new EtherealMod());
            }
            AbstractDungeon.player.getRelic(AmberMedallion.ID).flash();
            return SpireReturn.Return(newCard);
        }
        return SpireReturn.Continue();
    }
}

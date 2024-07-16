package code.relics;

import code.TheDisplaced;
import code.util.charUtil.LinkedRewardItem;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

import static code.ModFile.makeID;

public class ShatteredGlass extends AbstractEasyRelic  {
    public static final String ID = makeID("ShatteredGlass");

    public ShatteredGlass() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    @Override
    public void onTrigger()
    {
        int indexOfSapphire = -1;
        LinkedRewardItem sapphireKeyReward = null;
        List<RewardItem> relicRewards = new ArrayList<>();
        List<RewardItem> potionRewards = new ArrayList<>();
        for (RewardItem reward : AbstractDungeon.getCurrRoom().rewards) {
            if(reward.type == RewardItem.RewardType.POTION){
                potionRewards.add(reward);
            } else if (reward.type == RewardItem.RewardType.RELIC) {
                relicRewards.add(reward);
            } else if (reward.type == RewardItem.RewardType.SAPPHIRE_KEY) {
                indexOfSapphire = AbstractDungeon.getCurrRoom().rewards.indexOf(reward);
                sapphireKeyReward = new LinkedRewardItem(reward);
            }
        }

        if (sapphireKeyReward != null) {
            AbstractDungeon.getCurrRoom().rewards.set(indexOfSapphire, sapphireKeyReward);
        }

        boolean doFlash = false;

        for (RewardItem reward : potionRewards) {
            AbstractPotion.PotionRarity rarity = reward.potion.rarity;
            if (rarity != AbstractPotion.PotionRarity.PLACEHOLDER) {
                AbstractPotion newPotion = reward.potion.makeCopy();
                do {
                    newPotion = AbstractDungeon.returnRandomPotion(rarity, false).makeCopy();
                } while ((newPotion.ID.equals(reward.potion.ID)));
                doFlash = true;
                LinkedRewardItem replaceReward = new LinkedRewardItem(reward);
                LinkedRewardItem newReward = new LinkedRewardItem(replaceReward, newPotion);
                int indexOf = AbstractDungeon.getCurrRoom().rewards.indexOf(reward);
                AbstractDungeon.getCurrRoom().rewards.add(indexOf + 1, newReward);
                AbstractDungeon.getCurrRoom().rewards.set(indexOf, replaceReward);
            }
        }

        for (RewardItem reward : relicRewards) {
            RelicTier tier = reward.relic.tier;
            if (tier != RelicTier.SPECIAL && tier != RelicTier.DEPRECATED && tier != RelicTier.STARTER) {
                AbstractRelic newRelic = AbstractDungeon.returnRandomRelic(tier);
                if (newRelic != null) {
                    doFlash = true;
                    LinkedRewardItem replaceReward = new LinkedRewardItem(reward);
                    LinkedRewardItem newReward = new LinkedRewardItem(replaceReward, newRelic);
                    boolean linkedWithSapphire = reward.relicLink != null && reward.relicLink.type == RewardItem.RewardType.SAPPHIRE_KEY;
                    if (sapphireKeyReward != null && linkedWithSapphire) {
                        sapphireKeyReward.addRewardLink(replaceReward);
                        sapphireKeyReward.addRewardLink(newReward);
                    }
                    int indexOf = AbstractDungeon.getCurrRoom().rewards.indexOf(reward);
                    AbstractDungeon.getCurrRoom().rewards.add(indexOf + 1, newReward);
                    AbstractDungeon.getCurrRoom().rewards.set(indexOf, replaceReward);
                }
            }
        }

        if (doFlash) {
            flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player,  this));
        }
    }

    @Override
    public void onChestOpenAfter(boolean bossChest)
    {
        if (!bossChest) {
            onTrigger();
        }
    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 34;
    }
}

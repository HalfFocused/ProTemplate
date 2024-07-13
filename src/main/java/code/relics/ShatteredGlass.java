package code.relics;

import code.TheDisplaced;
import code.util.charUtil.LinkedRewardItem;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

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
        for (RewardItem reward : AbstractDungeon.getCurrRoom().rewards) {
            if (reward.type == RewardItem.RewardType.RELIC) {
                relicRewards.add(reward);
            } else if (reward.type == RewardItem.RewardType.SAPPHIRE_KEY) {
                indexOfSapphire = AbstractDungeon.getCurrRoom().rewards.indexOf(reward);
                sapphireKeyReward = new LinkedRewardItem(reward);
            }
        }

        if (sapphireKeyReward != null) {
            // Replace original
            AbstractDungeon.getCurrRoom().rewards.set(indexOfSapphire, sapphireKeyReward);
        }

        boolean doFlash = false;
        for (RewardItem reward : relicRewards) {
            RelicTier tier = reward.relic.tier;
            if (tier != RelicTier.SPECIAL && tier != RelicTier.DEPRECATED && tier != RelicTier.STARTER) {
                AbstractRelic newRelic = AbstractDungeon.returnRandomRelic(tier);
                if (newRelic != null) {
                    doFlash = true;
                    LinkedRewardItem replaceReward = new LinkedRewardItem(reward);
                    LinkedRewardItem newReward = new LinkedRewardItem(replaceReward, newRelic);
                    // Link with sapphire key
                    boolean linkedWithSapphire = reward.relicLink != null && reward.relicLink.type == RewardItem.RewardType.SAPPHIRE_KEY;
                    if (sapphireKeyReward != null && linkedWithSapphire) {
                        sapphireKeyReward.addRelicLink(replaceReward);
                        sapphireKeyReward.addRelicLink(newReward);
                    }
                    int indexOf = AbstractDungeon.getCurrRoom().rewards.indexOf(reward);
                    // Insert after existing reward
                    AbstractDungeon.getCurrRoom().rewards.add(indexOf + 1, newReward);
                    // Replace original
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
}

package code.relics;

import code.TheDisplaced;
import code.powers.ForetoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class PurpleBow extends AbstractEasyRelic {
    public static final String ID = makeID("PurpleBow");

    public PurpleBow() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheDisplaced.Enums.DISPLACED_COLOR);
    }

    public void atBattleStart() {
        flash();
        for(AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
            this.addToBot(new RelicAboveCreatureAction(mo, this));
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new ForetoldPower(mo, 1), 1, true));
        }
    }

}

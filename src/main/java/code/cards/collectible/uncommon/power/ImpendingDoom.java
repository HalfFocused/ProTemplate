package code.cards.collectible.uncommon.power;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.powers.ImpendingDoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ImpendingDoom extends AbstractEasyCard {
    public final static String ID = makeID(ImpendingDoom.class.getSimpleName());

    public ImpendingDoom() {
        super(ID, -1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int effect = EnergyPanel.totalCount;
                if (energyOnUse != -1) {
                    effect = energyOnUse;
                }
                if(upgraded){
                    effect += 1;
                }

                if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {
                    effect += 2;
                    AbstractDungeon.player.getRelic(ChemicalX.ID).flash();
                }
                this.addToTop(new ApplyPowerAction(p, p, new ImpendingDoomPower(p, effect), effect));
                if (!freeToPlayOnce) {
                    p.energy.use(EnergyPanel.totalCount);
                }
                isDone = true;
            }
        });
    }

    public void upp() {
    }
}
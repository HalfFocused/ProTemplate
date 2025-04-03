package code.cards.collectible.common.skill;

import code.actions.ApplyCardModAction;
import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.mods.EtherealModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class FadeAway extends AbstractEasyCard {
    public final static String ID = makeID(FadeAway.class.getSimpleName());
    // intellij stuff skill, self, common, , , 8, 2, , 

    public FadeAway() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> hits = new ArrayList<>();
                for(AbstractCard c : p.hand.group){
                    if(!c.isEthereal){
                        hits.add(c);
                    }
                }
                if(!hits.isEmpty()){
                    AbstractCard chosenCard = hits.get(AbstractDungeon.cardRandomRng.random(0, hits.size() - 1));
                    addToTop(new ApplyCardModAction(chosenCard, new EtherealModifier()));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}
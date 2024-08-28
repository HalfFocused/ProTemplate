package code.actions;

import basemod.ReflectionHacks;
import code.effects.WarpEffect;
import code.util.charUtil.CardUtil;
import code.util.charUtil.OnWarpCard;
import code.util.charUtil.WarpHook;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class WarpAction extends AbstractGameAction {

    AbstractCreature source;
    int num;
    boolean firstOne;
    public WarpAction(int numIn) {
        this.duration = Settings.ACTION_DUR_FAST;
        num = numIn;
        firstOne = CardUtil.queuedWarps == 0;
        CardUtil.queuedWarps = num;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            addToTop(new AbstractGameAction() {
                public void update() {
                    AbstractDungeon.getCurrRoom().skipMonsterTurn = true;
                    AbstractDungeon.actionManager.turnHasEnded = true;
                    ReflectionHacks.privateMethod(GameActionManager.class, "getNextAction").invoke(AbstractDungeon.actionManager);
                    ReflectionHacks.privateMethod(GameActionManager.class, "callEndOfTurnActions").invoke(AbstractDungeon.actionManager);
                    addToTop(new AbstractGameAction() { //All the warp hooks LMAO
                        @Override
                        public void update() {
                            for(AbstractRelic relic : AbstractDungeon.player.relics){
                                if(relic instanceof WarpHook){
                                    ((WarpHook) relic).onWarp();
                                }
                            }
                            for(AbstractPower power : AbstractDungeon.player.powers){
                                if(power instanceof WarpHook){
                                    ((WarpHook) power).onWarp();
                                }
                            }
                            for(AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
                                for(AbstractPower power : monster.powers){
                                    if(power instanceof WarpHook){
                                        ((WarpHook) power).onWarp();
                                    }
                                }
                            }
                            for(AbstractCard card : AbstractDungeon.player.drawPile.group){
                                if(card instanceof OnWarpCard){
                                    ((OnWarpCard) card).onWarp(AbstractDungeon.player.drawPile);
                                }
                            }
                            for(AbstractCard card : AbstractDungeon.player.hand.group){
                                if(card instanceof OnWarpCard){
                                    ((OnWarpCard) card).onWarp(AbstractDungeon.player.hand);
                                }
                            }
                            for(AbstractCard card : AbstractDungeon.player.discardPile.group){
                                if(card instanceof OnWarpCard){
                                    ((OnWarpCard) card).onWarp(AbstractDungeon.player.discardPile);
                                }
                            }
                            CardUtil.warpsThisCombat++;
                            this.isDone = true;
                        }
                    });
                    AbstractDungeon.getCurrRoom().endTurn();
                    AbstractDungeon.player.isEndingTurn = false;
                    this.isDone = true;
                }
            });

            if(firstOne) {
                addToTop(new SFXAction("POWER_TIME_WARP", 0.05F));
                //AbstractDungeon.actionManager.addToTop(new VFXAction(new WarpEffect(CardUtil.queuedWarps)));
            }
            addToTop(new VFXAction(new WhirlwindEffect(), 0.0F));

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }

    protected void tickDuration() {
        this.duration -= Gdx.graphics.getDeltaTime();// 78
        if (this.duration < 0.0F) {// 79
            this.isDone = true;// 80
        }
    }

}
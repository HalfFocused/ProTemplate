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
                    /*
                        "I wonder if you could convince the game action manager to just do all this for you
                        (and thus catching patched in hooks) by setting turnHasEnded and skipMonsterTurn"
                        - Mistress Autumn (6/24/2024, 5:03PM EST)

                        Without this message I wouldn't have slammed my head against the deck for 4 days straight.
                        But I also wouldn't have Warp. It's truly amazing how much was spurred into being
                        by a simple sentence.

                        Now to figure out IRL time travel, so I can go back and make a sensible character mod.
                        For anyone looking at this code to see how I did it, reconsider. If you're insistent,
                        well, I will try to explain.
                     */
                    //Step 1: Trick the game into thinking the turn has ended
                    AbstractDungeon.getCurrRoom().skipMonsterTurn = true;
                    AbstractDungeon.actionManager.turnHasEnded = true;
                    /*
                        Step 2: call the things that happen at the end of turn.
                        getNextAction will queue up the start of turn stuff.
                        callEndOfTurnActions will, well, queue up the stuff that happens at the end of turn for the player.

                        "Hey, why do you queue the start of turn stuff and then the end of turn stuff. isn't that backwards?"
                        yeah! it should be! I don't know why it isn't! I knew when I wrote it though. probably.
                     */
                    ReflectionHacks.privateMethod(GameActionManager.class, "getNextAction").invoke(AbstractDungeon.actionManager);
                    ReflectionHacks.privateMethod(GameActionManager.class, "callEndOfTurnActions").invoke(AbstractDungeon.actionManager);
                    addToTop(new AbstractGameAction() {
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
                    /*
                        This handles the end of the player turn and WOULD queue up the monster's turn if
                        skipMonsterTurn wasn't true.
                     */
                    AbstractDungeon.getCurrRoom().endTurn();
                    this.isDone = true;
                    /*
                    "Wow! That didn't seem that bad, Half!"
                    go to patches > warp. this is the least confusing part of this all.
                     */
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
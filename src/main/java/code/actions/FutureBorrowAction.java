package code.actions;

import code.effects.FutureHazeEffect;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheBeyondScene;
import com.megacrit.cardcrawl.scenes.TheCityScene;
import com.megacrit.cardcrawl.scenes.TheEndingScene;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.DeathScreenFloatyEffect;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FutureBorrowAction extends AbstractGameAction {
    //Some important values from AbstractRelic that are private
    private static final float START_X = 64.0F * Settings.scale;
    private static final float START_Y = Settings.HEIGHT - 102.0F * Settings.scale;

    private static final int EXORDIUM = 1;
    private static final int THE_CITY = 2;
    private static final int THE_BEYOND = 3;
    private static final int THE_ENDING = 4;

    public static AbstractGameEffect EMITTER = null;

    AbstractScene scene;
    AbstractRoom room;
    CardGroup hand;
    CardGroup draw;
    CardGroup discard;
    CardGroup exhaust;
    AbstractGameEffect emitter;
    int health;
    ArrayList<AbstractRelic> relics;
    int energy;

    public FutureBorrowAction(int cards) {
        this.amount = cards;
        this.source = AbstractDungeon.player;
    }

    @Override
    public void update() {
        //Store a backup of everything we intend to modify
        hand = AbstractDungeon.player.hand;
        draw = AbstractDungeon.player.drawPile;
        discard = AbstractDungeon.player.discardPile;
        exhaust = AbstractDungeon.player.exhaustPile;
        health = AbstractDungeon.player.currentHealth;
        relics = new ArrayList<>(AbstractDungeon.player.relics);
        energy = EnergyPanel.totalCount;
        //TODO other things you could store and mess with: potions, turn number (for minty spire), floor number, player powers, keys

        scene = AbstractDungeon.scene;
        room = AbstractDungeon.getCurrRoom();

        for(int i = 0; i < 60; i++){
            AbstractDungeon.topLevelEffectsQueue.add(new FutureHazeEffect(true));
        }

        //TODO this does absolutely nothing lol, likely due to effects not processing while the screen is up?
        // Either remove or find a workaround if you want to have vfx while the action is happening
        EMITTER = new AbstractGameEffect() {
            {
                this.color = new Color();
            }
            @Override
            public void render(SpriteBatch spriteBatch) {
                if(MathUtils.random(1,5) == 5) {
                    AbstractDungeon.topLevelEffectsQueue.add(new FutureHazeEffect(false));
                }
            }

            @Override
            public void dispose() {}
        };

        AbstractDungeon.effectList.add(EMITTER);

        makeScene();
        makeRoom();

        //TODO can play with these numbers and generation if you want,
        // the only one that actually matters is the amount passed to the action since you will be picking from hand,
        // I made no attempt to mimic a real deck and dont even have strikes or defends
        CardGroup newHand = new CardGroup(CardGroup.CardGroupType.HAND);
        newHand.group.addAll(generateCardChoices(amount, c -> (c.rarity != AbstractCard.CardRarity.RARE || MathUtils.randomBoolean())));
        AbstractDungeon.player.hand = newHand;

        ArrayList<AbstractCard> fakeMasterDeck = new ArrayList<>(AbstractDungeon.player.masterDeck.group);

        CardGroup newExhaust = new CardGroup(CardGroup.CardGroupType.EXHAUST_PILE);
        newExhaust.group.addAll(generateCardChoices(MathUtils.random(0, 3), c -> c.exhaust || c.isEthereal));
        for(int i = 0; i < MathUtils.random(1, fakeMasterDeck.size() / 5); i++){
            AbstractCard c = fakeMasterDeck.remove(MathUtils.random(0, fakeMasterDeck.size() - 1));
            newExhaust.addToRandomSpot(c);
        }
        AbstractDungeon.player.exhaustPile = newExhaust;

        fakeMasterDeck.addAll(generateCardChoices(MathUtils.random(4, 7), c -> c.rarity != AbstractCard.CardRarity.RARE));
        fakeMasterDeck.addAll(generateCardChoices(MathUtils.random(1, 2), c -> c.rarity == AbstractCard.CardRarity.RARE));

        CardGroup newDraw = new CardGroup(CardGroup.CardGroupType.DRAW_PILE);
        CardGroup newDiscard = new CardGroup(CardGroup.CardGroupType.DISCARD_PILE);

        while(!fakeMasterDeck.isEmpty()){
            AbstractCard c = fakeMasterDeck.remove(MathUtils.random(0, fakeMasterDeck.size() - 1));
            if(MathUtils.randomBoolean()){
                newDraw.addToRandomSpot(c);
            }else{
                newDiscard.addToRandomSpot(c);
            }
        }

        AbstractDungeon.player.drawPile = newDraw;

        AbstractDungeon.player.discardPile = newDiscard;



        //TODO crashes if player max hp is less than 10 lol
        AbstractDungeon.player.currentHealth = MathUtils.random(10, AbstractDungeon.player.maxHealth);
        //TODO this animates the hp bar as if you actually took damage, probably want to instead set some of the stuff directly,
        // might need some reflectionhacks for that
        AbstractDungeon.player.healthBarUpdatedEvent();

        //TODO this is basically all awful, lol
        // makes no attempts to not spawn multiple copies, doesnt spawn boss relics, doesnt factor in how many elites/bosses should be left
        // if it generates clickable/button relics that could be a problem if they can be clicked with the screen open
        ArrayList<AbstractRelic> newRelics = new ArrayList<>(AbstractDungeon.player.relics);
        int toSpawn = MathUtils.random(4, 8);
        for (int i = 0; i < toSpawn; i++) {
            String key = AbstractDungeon.commonRelicPool.get(MathUtils.random(AbstractDungeon.commonRelicPool.size() - 1));
            if (MathUtils.randomBoolean()) {
                key = AbstractDungeon.uncommonRelicPool.get(MathUtils.random(AbstractDungeon.uncommonRelicPool.size() - 1));
                if (MathUtils.randomBoolean()) {
                    key = AbstractDungeon.rareRelicPool.get(MathUtils.random(AbstractDungeon.rareRelicPool.size() - 1));
                }
            }
            //At least this part works okay
            AbstractRelic relic = RelicLibrary.getRelic(key).makeCopy();
            relic.currentX = START_X + newRelics.size() * AbstractRelic.PAD_X;
            relic.currentY = START_Y;
            relic.targetX = relic.currentX;
            relic.targetY = relic.currentY;
            relic.hb.move(relic.currentX, relic.currentY);
            newRelics.add(relic);
        }
        AbstractDungeon.player.relics = newRelics;

        EnergyPanel.totalCount = AbstractDungeon.player.energy.energy;

        //TODO dont hardcode string! I only allow picking 1 card but you could easily allow picking more
        addToTop(new FutureSelectCardsAction(1, "Borrow", true, true, c -> true, cards -> {
            //Restore all the original stuff
            AbstractDungeon.scene = this.scene;
            AbstractDungeon.getCurrMapNode().room = this.room;
            AbstractDungeon.player.hand = this.hand;
            AbstractDungeon.player.drawPile = this.draw;
            AbstractDungeon.player.discardPile = this.discard;
            AbstractDungeon.player.exhaustPile = this.exhaust;

            AbstractDungeon.player.currentHealth = this.health;
            //TODO this animates the hp bar as if you actually took damage, probably want to instead set some of the stuff directly,
            // might need some reflectionhacks for that
            AbstractDungeon.player.healthBarUpdatedEvent();
            AbstractDungeon.player.relics = this.relics;
            EnergyPanel.totalCount = this.energy;

            //Actually add the selected card(s)
            //TODO actually see if there is room in the hand
            AbstractDungeon.player.hand.group.addAll(cards);
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();

            //Stop the emitter, if you didnt delete that
            EMITTER.isDone = true;
            EMITTER = null;
        }));
        this.isDone = true;
    }

    private ArrayList<AbstractCard> generateCardChoices(int cards, Predicate<AbstractCard> filter) {
        ArrayList<AbstractCard> validCards = new ArrayList<>();
        validCards.addAll(AbstractDungeon.srcCommonCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        validCards.addAll(AbstractDungeon.srcUncommonCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        validCards.addAll(AbstractDungeon.srcRareCardPool.group.stream().filter(filter).collect(Collectors.toList()));
        if (validCards.isEmpty()) {
            validCards.addAll(CardLibrary.getAllCards().stream().filter(c -> filter.test(c) && (c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)).collect(Collectors.toList()));
        }
        ArrayList<AbstractCard> ret = new ArrayList<>();
        for (int i = 0 ; (i < cards && !validCards.isEmpty()) ; i++) {
            //TODO removes the card to ensure every card is unique,
            // you can change this if you want to be able to have duplicate cards (weaker effect but more realistic)
            AbstractCard choice = validCards.remove(AbstractDungeon.cardRandomRng.random(validCards.size() - 1)).makeCopy();
            if(AbstractDungeon.cardRandomRng.random(3) == 1){
                choice.upgrade();
            }
            ret.add(choice);
        }
        return ret;
    }

    private static void makeRoom(){
        ArrayList<String> possibleChoices = new ArrayList<>();

        if(AbstractDungeon.actNum == EXORDIUM){
            possibleChoices.add("2 Thieves");
            possibleChoices.add("3 Byrds");
            possibleChoices.add("Shell Parasite");
            possibleChoices.add("Spheric Guardian");
            possibleChoices.add("Cultist and Chosen");
            possibleChoices.add("3 Cultists");
            possibleChoices.add("Chosen and Byrds");
            possibleChoices.add("Sentry and Sphere");
            possibleChoices.add("Snake Plant");
            possibleChoices.add("Snecko");
            possibleChoices.add("Book of Stabbing");
            possibleChoices.add("Gremlin Leader");
            possibleChoices.add("Slavers");
            possibleChoices.add("Masked Bandits");
            possibleChoices.add("Automaton");
            possibleChoices.add("Champ");
            possibleChoices.add("Collector");
        }else if(AbstractDungeon.actNum == THE_CITY){
            possibleChoices.add("Transient");
            possibleChoices.add("3 Darklings");
            possibleChoices.add("3 Shapes");
            possibleChoices.add("Jaw Worm Horde");
            possibleChoices.add("Orb Walker");
            possibleChoices.add("Spire Growth");
            possibleChoices.add("Maw");
            possibleChoices.add("4 Shapes");
            possibleChoices.add("Sphere and 2 Shapes");
            possibleChoices.add("Reptomancer");
            possibleChoices.add("Nemesis");
            possibleChoices.add("Giant Head");
            possibleChoices.add("Time Eater");
            possibleChoices.add("Awakened One");
            possibleChoices.add("Donu and Deca");
        }else if(AbstractDungeon.actNum == THE_BEYOND){
            possibleChoices.add("Shield and Spear");
        }else if(AbstractDungeon.actNum == THE_ENDING){
            possibleChoices.add("The Heart");
        }

        AbstractRoom newRoom = new MonsterRoom();
        newRoom.monsters = MonsterHelper.getEncounter(possibleChoices.get(MathUtils.random(0, possibleChoices.size() - 1)));

        for(AbstractMonster mo : newRoom.monsters.monsters){
            mo.init();
            //TODO this doesnt render the hp bar becasue it doesnt have time to animate it fading in,
            // probably need to reflectionhacks the healthBarAnimTimer and/or set hbAlpha and call updateHbAlpha
            //TODO monsters dont spawn with powers since those are technically added via actions,
            // if you dont make a large list of possible monsters this can probably be hardcoded easily enough
            //TODO also throw some random debuffs on the monsters for fun
            mo.currentHealth = (int) (mo.maxHealth * MathUtils.random(0.35f, 0.85f));
        }


        AbstractDungeon.getCurrMapNode().room = newRoom;

    }

    private static void makeScene(){
        AbstractScene newScene;

        switch (AbstractDungeon.actNum){
            case EXORDIUM:
                newScene = new TheCityScene();
                break;
            case THE_CITY:
                newScene = new TheBeyondScene();
                break;
            case THE_BEYOND:
            default:
                newScene = new TheEndingScene();
                break;
        }

        newScene.randomizeScene();

        AbstractDungeon.scene = newScene;
    }
}
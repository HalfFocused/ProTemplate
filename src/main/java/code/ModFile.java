package code;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import code.cards.tokens.Vision;
import code.util.charUtil.CardUtil;
import code.util.charUtil.ForgetCard;
import code.util.charUtil.mods.FlashbackModifier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import code.cards.AbstractEasyCard;
import code.cards.cardvars.AbstractEasyDynamicVariable;
import code.potions.AbstractEasyPotion;
import code.relics.AbstractEasyRelic;
import code.util.ProAudio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ModFile implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber,
        OnPlayerTurnStartSubscriber,
        OnStartBattleSubscriber,
        PostExhaustSubscriber,
        PostDeathSubscriber,
        OnCardUseSubscriber{

    public static final String modID = "displacedmod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color displacedColor = new Color(0.8f, 0.54f, 0.28f, 1);
    public static Color offeringColor = new Color(0.8f, 0.28f, 0.28f, 1);


    public static final String SHOULDER1 = makeCharacterPath("mainChar/shoulder.png");
    public static final String SHOULDER2 = makeCharacterPath("mainChar/shoulder2.png");
    public static final String CORPSE = makeCharacterPath("mainChar/corpse.png");
    private static final String ATTACK_S_ART = makeImagePath("512/attack.png");
    private static final String SKILL_S_ART = makeImagePath("512/skill.png");
    private static final String POWER_S_ART = makeImagePath("512/power.png");
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String ATTACK_L_ART = makeImagePath("1024/attack.png");
    private static final String SKILL_L_ART = makeImagePath("1024/skill.png");
    private static final String POWER_L_ART = makeImagePath("1024/power.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public ModFile() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheDisplaced.Enums.DISPLACED_COLOR, displacedColor, displacedColor, displacedColor,
                displacedColor, displacedColor, displacedColor, displacedColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath)
    {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeShaderPath(String resourcePath)
    {
        return modID + "Resources/shaders/" + resourcePath;
    }

    public static String makeUIPath(String resourcePath)
    {
        return modID + "Resources/images/ui/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        ModFile thismod = new ModFile();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheDisplaced(TheDisplaced.characterStrings.NAMES[1], TheDisplaced.Enums.THE_DISPLACED),
            CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheDisplaced.Enums.THE_DISPLACED);
        
        new AutoAdd(modID)
            .packageFilter(AbstractEasyPotion.class)
            .any(AbstractEasyPotion.class, (info, potion) -> {
                if (potion.pool == null)
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID);
                else
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.ID, potion.pool);
            });
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
            .packageFilter(AbstractEasyDynamicVariable.class)
            .any(DynamicVariable.class, (info, var) -> 
                BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        CardUtil.cardExhaustedThisTurn = false;
        if(CardUtil.queuedWarps == 0) {
            CardUtil.theSecondDreamActivatedLastTurn = CardUtil.theSecondDreamActivatedThisTurn;
            CardUtil.theSecondDreamActivatedThisTurn = false;
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        CardUtil.cardStreaks.clear();
        CardUtil.cardsPlayedLastTurn.clear(); //Even though Recurring Dream working across combats was cool
        CardUtil.queuedWarps = 0; //In case the previous combat ends with warps queued (cursed)
        CardUtil.warpsThisCombat = 0;
    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {
        CardUtil.cardExhaustedThisTurn = true;
        if(abstractCard instanceof ForgetCard){
            CardUtil.forgetCard((ForgetCard) abstractCard);
        }
    }
    public static final String RECORD_FILE = modID + "/saveable/displacedSavable.txt";

    @Override
    public void receivePostDeath() {
        if(AbstractDungeon.player instanceof TheDisplaced && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            if(AbstractDungeon.getCurrRoom().monsters.monsters.stream().anyMatch(mon -> mon instanceof TimeEater)){
                SpireConfig record = getTimeEaterRecord();
                record.setInt("losses",record.getInt("losses") + 1);
                record.setBool("lastRunDied", true);
                try {
                    record.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static SpireConfig getTimeEaterRecord() {
        Properties defaults = new Properties();
        defaults.setProperty("wins", "0");
        defaults.setProperty("losses", "0");
        defaults.setProperty("lastRunDied", "false");
        SpireConfig record;
        try {
            record = new SpireConfig(modID, "time-eater", defaults);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if(abstractCard instanceof Vision){
            FlashbackModifier.flashback(FlashbackModifier.THE_STARS_ALIGNED);
        }
    }
}

package code;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import code.cards.AbstractEasyCard;
import code.cardvars.SecondBlock;
import code.cardvars.SecondDamage;
import code.cardvars.SecondMagicNumber;
import code.character.KosmoCharacter;
import code.potions.AbstractEasyPotion;
import code.relics.AbstractEasyRelic;
import code.util.ProAudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class KosmoMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber {

    public static final String modID = "kosmomod24";

    public static Color characterColor = new Color(219f/255f, 105f/255f, 250f/255f, 1f);

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

    public KosmoMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(KosmoCharacter.Enums.VIOLET, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static String makePath(String resourcePath) {
        return "KosmoModResources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return "KosmoModResources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return "KosmoModResources/images/relics/" + resourcePath;
    }

    public static String makeLargeRelicPath(String resourcePath) {
        return "KosmoModResources/images/largeRelics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return "KosmoModResources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return "KosmoModResources/images/powers/" + resourcePath;
    }

    public static String makeCharacterPath(String resourcePath)
    {
        return "KosmoModResources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return "KosmoModResources/images/cards/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return "KosmoModResources/images/events/" + resourcePath;
    }

    public static void initialize() {
        KosmoMod thismod = new KosmoMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new KosmoCharacter(KosmoCharacter.characterStrings.NAMES[1], KosmoCharacter.Enums.KOSMO),
            CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, KosmoCharacter.Enums.KOSMO);
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
    public void receivePostInitialize() {
        return;
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        BaseMod.addDynamicVariable(new SecondBlock());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "KosmoModResources/localization/" + getLangString() + "/CardStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "KosmoModResources/localization/" + getLangString() + "/RelicStrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "KosmoModResources/localization/" + getLangString() + "/CharacterStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "KosmoModResources/localization/" + getLangString() + "/PowerStrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "KosmoModResources/localization/" + getLangString() + "/PotionStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class,  "KosmoModResources/localization/" + getLangString() + "/EventStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("KosmoModResources/localization/" + getLangString() + "/KeywordsStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID + "", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    
    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        ArrayList<AbstractCard> cards = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(KosmoModTags.GRAVE)) {
                cards.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(KosmoModTags.GRAVE)) {
                cards.add(c);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(KosmoModTags.GRAVE)) {
                cards.add(c);
            }
        }

        for (AbstractCard card : cards) {
            AbstractDungeon.player.drawPile.removeCard(card);
            AbstractDungeon.player.discardPile.removeCard(card);
            AbstractDungeon.player.hand.removeCard(card);
            AbstractDungeon.player.exhaustPile.addToTop(card);
        }
    }
}

package kosmomod.relics;

import basemod.abstracts.CustomRelic;
import kosmomod.KosmoMod;
import kosmomod.util.TexLoader;

import static kosmomod.KosmoMod.makeLargeRelicPath;
import static kosmomod.KosmoMod.makeRelicOutlinePath;
import static kosmomod.KosmoMod.makeRelicPath;
import static kosmomod.KosmoMod.modID;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractEasyRelic extends CustomRelic {
    public AbstractCard.CardColor color;

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractEasyRelic(String setId, String img, String img_otl, RelicTier tier, LandingSound sfx) {
		super(setId, new Texture(KosmoMod.makeRelicPath(img)), new Texture(KosmoMod.makeRelicOutlinePath(img_otl)), tier, sfx);
	}

    public AbstractEasyRelic(String setId, String large_img, RelicTier tier, LandingSound sfx) {
		super(setId, new Texture(KosmoMod.makeLargeRelicPath(large_img)), tier, sfx);
	}

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicOutlinePath(setId.replace(modID + ":", "") + "Outline.png"));
        largeImg = TexLoader.getTexture(makeLargeRelicPath(setId.replace(modID + ":", "")  + ".png"));
        this.color = color;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
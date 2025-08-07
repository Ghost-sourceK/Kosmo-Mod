package code.relics;

import basemod.abstracts.CustomRelic;
import code.util.TexLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static code.KosmoMod.makeRelicPath;
import static code.KosmoMod.makeRelicOutlinePath;
import static code.KosmoMod.makeLargeRelicPath;
import static code.KosmoMod.modID;
import code.KosmoMod;

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
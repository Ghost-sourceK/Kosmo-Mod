package kosmomod.relics;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import kosmomod.character.KosmoCharacter;

public class Blackhole extends AbstractEasyRelic {
    public static final String ID = makeID("Blackhole");
    private int turnCounter = 0;

    public Blackhole() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, KosmoCharacter.Enums.VIOLET);
    }

    @Override
    public void atBattleStart() {
        turnCounter = 1;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (turnCounter % 2 == 1) {
            flash();
            addToTop(new DrawCardAction(2));
            addToBot(new ExhaustAction(1, true));
        }
        turnCounter++;
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(RedGiant.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(RedGiant.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(RedGiant.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        }
        else {
            super.obtain();
        }
    }
}
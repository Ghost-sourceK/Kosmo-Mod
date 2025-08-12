package kosmomod.relics;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;

import kosmomod.character.KosmoCharacter;

public class RedGiant extends AbstractEasyRelic {
    public static final String ID = makeID("RedGiant");
    private boolean firstTurn = false;

    public RedGiant() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, KosmoCharacter.Enums.VIOLET);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToTop(new DrawCardAction(1));
        firstTurn = true;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (firstTurn) {
            addToBot(new ExhaustAction(1, false));
            firstTurn = false;
        }
    }

    @Override
    public void onVictory() {
        firstTurn = false;
    }
}
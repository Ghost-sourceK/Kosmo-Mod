package code.relics;

import code.character.KosmoCharacter;
import static code.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;

public class Blackhole extends AbstractEasyRelic {
    public static final String ID = makeID("Blackhole");
    private boolean firstTurn = false;

    public Blackhole() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, KosmoCharacter.Enums.VIOLET);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToTop(new DrawCardAction(1));
        firstTurn = true;
    }

    @Override
    public void atTurnStart() {
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
package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import code.cards.AbstractEasyCard;
import static com.megacrit.cardcrawl.actions.common.DrawCardAction.drawnCards;

public class CheckKosmoTagAction extends AbstractGameAction {

    private final ArrayList<AbstractCard> toExhaust = new ArrayList<>();
    private boolean firstUpdate = true;

    public CheckKosmoTagAction() {
        this.duration = this.startDuration = 0.5f; // Delay 0.5s
    }

    @Override
    public void update() {
        if (firstUpdate) {
            ArrayList<AbstractCard> lastDrawn = new ArrayList<>(drawnCards);

            for (AbstractCard card : lastDrawn) {
                if (card.hasTag(code.KosmoModTags.KOSMO)) {
                    AbstractEasyCard kosmoCard = (AbstractEasyCard) card;
                    if (!kosmoCard.hasGivenEnergyThisTurn) {
                        kosmoCard.hasGivenEnergyThisTurn = true;
                        addToTop(new GainEnergyAction(1));
                    }
                } else {
                    toExhaust.add(card);
                }
            }
            firstUpdate = false;
        }

        tickDuration();

        if (this.isDone) {
            for (AbstractCard card : toExhaust) {
                AbstractDungeon.player.hand.moveToExhaustPile(card);
                AbstractDungeon.player.exhaustPile.removeCard(card);
            }

            drawnCards.clear();
        }
    }
}
package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

public class DrawTaggedCardAction extends AbstractGameAction {
    private AbstractCard.CardTags tag;

    public DrawTaggedCardAction(AbstractCard.CardTags tag) {
        this.tag = tag;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> validCards = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(tag)) {
                validCards.add(c);
            }
        }

        if (!validCards.isEmpty()) {
            Collections.shuffle(validCards, AbstractDungeon.cardRandomRng.random);

            AbstractCard cardToDraw = validCards.get(0);
            AbstractDungeon.player.drawPile.removeCard(cardToDraw);
            AbstractDungeon.player.hand.addToHand(cardToDraw);
            cardToDraw.triggerWhenDrawn();
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
        }

        this.isDone = true;
    }
}
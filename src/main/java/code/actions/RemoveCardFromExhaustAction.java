package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveCardFromExhaustAction extends AbstractGameAction {
    private AbstractCard card;

    public RemoveCardFromExhaustAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.exhaustPile.contains(card)) {
            AbstractDungeon.player.exhaustPile.removeCard(card);
        }
        isDone = true;
    }
}
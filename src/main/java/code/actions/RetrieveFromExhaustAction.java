package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

public class RetrieveFromExhaustAction extends AbstractGameAction {
    private ArrayList<AbstractCard> tempExhumes = new ArrayList<>();
    private AbstractPlayer p;

    public RetrieveFromExhaustAction(int amount) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 10) {
                this.p.createHandIsFullDialog();
                this.isDone = true;
                return;
            }

            CardGroup exhaustPile = this.p.exhaustPile;
            if (exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.amount >= exhaustPile.size()) {
                AbstractCard c = this.p.exhaustPile.getTopCard();
                c.unfadeOut();
                this.p.hand.addToHand(c);
                this.p.exhaustPile.removeCard(c);
                c.unhover();
                c.fadingOut = false;
                this.isDone = true;
                return;
            }
            for (AbstractCard abstractCard : this.p.exhaustPile.group) {
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }

            if (exhaustPile.isEmpty()) {
                exhaustPile.group.addAll(tempExhumes);
                tempExhumes.clear();
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(exhaustPile, amount, "Choose cards to retrieve from Exhaust pile", false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                this.p.hand.addToHand(c);
                this.p.exhaustPile.removeCard(c);
                c.unhover();
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
            this.p.exhaustPile.group.addAll(this.tempExhumes);
            tempExhumes.clear();

            for (AbstractCard c : this.p.exhaustPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
        }

        tickDuration();
    }
}

package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayFromExhaustAction extends AbstractGameAction {
    private AbstractPlayer player;
    private int amount;

    public PlayFromExhaustAction(int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.player.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.player.exhaustPile.size() <= this.amount) {
                for (AbstractCard c : this.player.exhaustPile.group) {
                    AbstractCard tmp = c.makeStatEquivalentCopy();
                    tmp.purgeOnUse = true;
                    addToBot(new NewQueueCardAction(tmp, true, false, true));
                }
                this.player.exhaustPile.group.clear();
                this.isDone = true;
                return;
            }

            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.player.exhaustPile.group) {
                temp.addToTop(c);
            }
            temp.sortAlphabetically(true);
            temp.sortByRarityPlusStatusCardType(false);

            AbstractDungeon.gridSelectScreen.open(temp, this.amount, "Choose cards to play from the Exhaust pile", false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.exhaustPile.group.remove(c);

                AbstractCard tmp = c.makeStatEquivalentCopy();
                tmp.purgeOnUse = true;
                addToBot(new NewQueueCardAction(tmp, true, false, true));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }

        tickDuration();
    }
}
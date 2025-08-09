package code.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.Settings;

public class PurgeCardExhaustPileAction extends AbstractGameAction {
    private boolean random;
    private AbstractPlayer p;

    public PurgeCardExhaustPileAction(int amount, boolean random) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.random = random;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    public PurgeCardExhaustPileAction(int amount) {
        this(amount, false);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup exhaustPile = this.p.exhaustPile;

            if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (random) {
                for (int i = 0; i < amount && !this.p.exhaustPile.isEmpty(); i++) {
                    AbstractCard c = this.p.exhaustPile.getRandomCard(true);
                    purgeCard(c);
                }
                this.isDone = true;
                return;
            }

            if (exhaustPile.size() <= amount) {
                ArrayList<AbstractCard> cardsToPurge = new ArrayList<>(exhaustPile.group);
                
                for (AbstractCard c : cardsToPurge) {
                    purgeCard(c);
                }
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(exhaustPile, amount, "Choose cards to Purge in the Exhaust pile", false);
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                purgeCard(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void purgeCard(AbstractCard c) {
        this.p.exhaustPile.removeCard(c);

        c.exhaustOnUseOnce = false;
        c.freeToPlayOnce = false;

        CardCrawlGame.dungeon.checkForPactAchievement();
    }
}

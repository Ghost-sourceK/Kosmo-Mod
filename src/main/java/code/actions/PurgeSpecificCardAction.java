package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PurgeSpecificCardAction extends AbstractGameAction {
   private AbstractCard targetCard;
   private CardGroup group;
   private float startingDuration;

   public PurgeSpecificCardAction(AbstractCard targetCard, CardGroup group) {
        this.targetCard = targetCard;
        this.group = group;
        this.actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        if (this.duration == this.startingDuration && group.contains(targetCard)) {
            group.group.remove(targetCard);

            AbstractDungeon.player.hand.refreshHandLayout();
            CardCrawlGame.dungeon.checkForPactAchievement();

            targetCard.exhaustOnUseOnce = false;
            targetCard.freeToPlayOnce = false;
        }

        this.tickDuration();
    }
}

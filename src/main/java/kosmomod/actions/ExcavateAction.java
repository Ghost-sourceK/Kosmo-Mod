package kosmomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ExcavateAction extends AbstractGameAction {
    private final int lookAmount;
    private final int drawAmount;
    private final float startingDuration;

    public ExcavateAction(int lookAmount, int drawAmount) {
        this.lookAmount = lookAmount;
        this.drawAmount = drawAmount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
            return;
        }

        if (this.duration == this.startingDuration) {
            CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (int i = 0; i < Math.min(lookAmount, AbstractDungeon.player.drawPile.size()); i++) {
                AbstractCard c = AbstractDungeon.player.drawPile.getNCardFromTop(i);
                tmpGroup.addToTop(c);
            }

            AbstractDungeon.gridSelectScreen.open(tmpGroup, drawAmount, true, "Choose " + this.drawAmount + " cards to draw");

            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ArrayList<AbstractCard> toDraw = new ArrayList<>(AbstractDungeon.gridSelectScreen.selectedCards);

            for (AbstractCard c : toDraw) {
                AbstractDungeon.player.drawPile.moveToHand(c, AbstractDungeon.player.drawPile);
                c.triggerWhenDrawn();
            }

            for (AbstractCard c : AbstractDungeon.gridSelectScreen.targetGroup.group) {
                if (!toDraw.contains(c)) {
                    AbstractDungeon.player.drawPile.moveToExhaustPile(c);
                }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        } else {
            ArrayList<AbstractCard> toDraw = new ArrayList<>(AbstractDungeon.gridSelectScreen.selectedCards);

            for (AbstractCard c : AbstractDungeon.gridSelectScreen.targetGroup.group) {
                if (!toDraw.contains(c)) {
                    AbstractDungeon.player.drawPile.moveToExhaustPile(c);
                }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }

        tickDuration();
    }
}

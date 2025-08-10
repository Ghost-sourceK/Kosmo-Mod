package code.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayFromExhaustAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int amount;
    private boolean random;

    public PlayFromExhaustAction(int amount, boolean random) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.random = random;
    }

    public PlayFromExhaustAction(int amount) {
        this(amount, false);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (random) {
                int count = Math.min(amount, p.exhaustPile.size());
                ArrayList<AbstractCard> chosen = new ArrayList<>();
                AbstractCard c = null;

                for (int i = 0; i < count; i++) {
                    c = p.exhaustPile.getRandomCard(false);
                    p.exhaustPile.group.remove(c);
                    chosen.add(c);
                }

                for (AbstractCard card : chosen) {
                    AbstractCard tmp = card.makeStatEquivalentCopy();
                    tmp.purgeOnUse = true;
                    addToBot(new NewQueueCardAction(tmp, true, false, true));
                }
                purgeNebula(c);

                this.isDone = true;
                return;
            }

            if (this.p.exhaustPile.size() <= this.amount) {
                for (AbstractCard c : this.p.exhaustPile.group) {
                    AbstractCard tmp = c.makeStatEquivalentCopy();
                    tmp.purgeOnUse = true;
                    addToBot(new NewQueueCardAction(tmp, true, false, true));
                }
                this.p.exhaustPile.group.clear();
                this.isDone = true;
                return;
            }

            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.exhaustPile.group) {
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
                p.exhaustPile.group.remove(c);

                AbstractCard tmp = c.makeStatEquivalentCopy();
                tmp.purgeOnUse = true;
                addToBot(new NewQueueCardAction(tmp, true, false, true));

                purgeNebula(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            p.hand.refreshHandLayout();
        }

        tickDuration();
    }

    private void purgeNebula(AbstractCard c) {
        if (c instanceof code.cards.rareattacks.Nebula) {
            code.cards.rareattacks.Nebula copy = (code.cards.rareattacks.Nebula) c.makeStatEquivalentCopy();
            copy.baseDamage = (int)(copy.baseDamage * 1.5);
            copy.damage = copy.baseDamage;
            copy.applyPowers();

            this.p.hand.addToHand(copy);
            this.p.hand.refreshHandLayout();
        }
    }
}
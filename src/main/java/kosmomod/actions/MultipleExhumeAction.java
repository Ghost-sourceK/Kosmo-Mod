package kosmomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import kosmomod.KosmoModTags;
import kosmomod.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;


public class MultipleExhumeAction extends AbstractGameAction {
    private ArrayList<AbstractCard> tempExhumes = new ArrayList<>();
    private AbstractPlayer p;
    private boolean random;

    public MultipleExhumeAction(int amount, boolean random) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.random = random;
    }

    public MultipleExhumeAction(int amount) {
        this(amount, false);
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
            ArrayList<AbstractCard> validCards = new ArrayList<>();

            for (AbstractCard card : exhaustPile.group) {
                if (card.hasTag(KosmoModTags.EXIMPOSSIBLE)) {
                    continue;
                }

                if (card instanceof AbstractEasyCard) {
                    if (!((AbstractEasyCard) card).isPurging) {
                        validCards.add(card);
                    }
                } else {
                    validCards.add(card);
                }
            }

            if (validCards.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (this.amount >= validCards.size()) {
                for (AbstractCard c : validCards) {
                    moveToHandFromExhaust(c, false);
                }
                this.isDone = true;
                return;
            }

            if (random) {
                for (int i = 0; i < this.amount && !validCards.isEmpty(); i++) {
                    AbstractCard c = validCards.get(AbstractDungeon.cardRandomRng.random(validCards.size() - 1));
                    moveToHandFromExhaust(c, true);
                    validCards.remove(c);
                }
                this.isDone = true;
                return;
            }
            
            for (AbstractCard abstractCard : this.p.exhaustPile.group) {
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }

            CardGroup selectableGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            selectableGroup.group.addAll(validCards);

            AbstractDungeon.gridSelectScreen.open(selectableGroup, amount, "Choose cards to retrieve from Exhaust pile", false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (c.type == AbstractCard.CardType.STATUS) {
                    this.p.exhaustPile.removeCard(c);
                    AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c));
                    continue;
                }

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

    private void moveToHandFromExhaust(AbstractCard c, boolean showEffect) {
        c.unfadeOut();
        if (c.type == AbstractCard.CardType.STATUS) {
            this.p.exhaustPile.removeCard(c);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c));
            return;
        }
        if (showEffect) {
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
        } else {
            this.p.hand.addToHand(c);
        }
        this.p.exhaustPile.removeCard(c);
        c.unhover();
        c.fadingOut = false;
    }
}

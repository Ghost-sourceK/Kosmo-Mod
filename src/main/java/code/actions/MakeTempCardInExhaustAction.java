package code.actions;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class MakeTempCardInExhaustAction extends AbstractGameAction {
    private AbstractCard c;
    private int numCards;
    private float waitTimer;
    private boolean sameUUID;

    public MakeTempCardInExhaustAction(AbstractCard card, int amount, float waitTimer) {
        UnlockTracker.markCardAsSeen(card.cardID);
        this.numCards = amount;
        this.waitTimer = waitTimer;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.c = card;
        this.sameUUID = false;
    }

    public MakeTempCardInExhaustAction(AbstractCard card, boolean sameUUID) {
        this(card, 1, 1.45F);
        this.sameUUID = sameUUID;
        if (!sameUUID && this.c.type != CardType.CURSE && this.c.type != CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
            this.c.upgrade();
        }
    }

    public MakeTempCardInExhaustAction(AbstractCard card, int amount) {
        this(card, amount, 1.45F);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            ArrayList<AbstractCard> newCards = new ArrayList<>();

            for (int i = 0; i < this.numCards; i++) {
                AbstractCard newCard = makeNewCard();
                newCards.add(newCard);
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(newCard));
            }

            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    waitTimer -= Gdx.graphics.getDeltaTime();
                    if (waitTimer <= 0F) {
                        for (AbstractCard card : newCards) {
                            AbstractDungeon.player.discardPile.removeCard(card);
                            AbstractDungeon.player.exhaustPile.addToTop(card);
                        }
                        isDone = true;
                    }
                }
            });
        }
        this.tickDuration();
    }

    private AbstractCard makeNewCard() {
        return this.sameUUID ? this.c.makeSameInstanceOf() : this.c.makeStatEquivalentCopy();
    }
}

package code.actions;


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
    private boolean sameUUID;

    public MakeTempCardInExhaustAction(AbstractCard card, int amount) {
        UnlockTracker.markCardAsSeen(card.cardID);
        this.numCards = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.c = card;
        this.sameUUID = false;
    }

    public MakeTempCardInExhaustAction(AbstractCard card, boolean sameUUID) {
        this(card, 1);
        this.sameUUID = sameUUID;
        if (!sameUUID && this.c.type != CardType.CURSE && this.c.type != CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
            this.c.upgrade();
        }
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            for (int i = 0; i < this.numCards; i++) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard()));
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        this.tickDuration();
    }

    private AbstractCard makeNewCard() {
        return this.sameUUID ? this.c.makeSameInstanceOf() : this.c.makeStatEquivalentCopy();
    }
}

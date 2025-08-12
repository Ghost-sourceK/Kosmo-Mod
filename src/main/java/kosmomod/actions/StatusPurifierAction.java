package kosmomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.ArrayList;

public class StatusPurifierAction extends AbstractGameAction {
    private AbstractPlayer p;

    public StatusPurifierAction(int amount) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> statusCards = new ArrayList<>();
        for (AbstractCard c : p.exhaustPile.group) {
            if (c.type == AbstractCard.CardType.STATUS) {
                statusCards.add(c);
            }
        }

        for (int i = 0; i < amount && !statusCards.isEmpty(); i++) {
            AbstractCard c = statusCards.get(0);

            c.unfadeOut();
            statusCards.remove(0);
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c));
            p.exhaustPile.removeCard(c);
            c.unhover();
            c.fadingOut = false;
        }

        isDone = true;
    }
}


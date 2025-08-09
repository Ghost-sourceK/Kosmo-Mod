package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.Settings;

public class PurgeAction extends AbstractGameAction {
    private boolean random;
    private AbstractPlayer p;
    private int purgeCount;
    private int healPerCard;

    public PurgeAction(int amount, boolean random, int healPerCard) {
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.random = random;
        this.healPerCard = healPerCard;
        this.purgeCount = 0;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }

    public PurgeAction(int amount, boolean random) {
        this(amount, random,0);
    }

    public PurgeAction(int amount) {
        this(amount, false,0);
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (random) {
                for (int i = 0; i < amount && !this.p.hand.isEmpty(); i++) {
                    AbstractCard c = this.p.hand.getRandomCard(true);
                    purgeCard(c);
                }
                purgeHeal();
                this.isDone = true;
                return;
            }

            if (this.p.hand.size() == 1) {
                AbstractCard c = this.p.hand.getTopCard();
                purgeCard(c);
                purgeHeal();
                this.isDone = true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("Purge", amount, true, false);
            this.tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                purgeCard(c);
            }
            purgeHeal();
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            this.p.hand.refreshHandLayout();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void purgeCard(AbstractCard c) {
        this.p.hand.moveToExhaustPile(c);

        this.p.exhaustPile.group.remove(c);

        c.exhaustOnUseOnce = false;
        c.freeToPlayOnce = false;

        if (c instanceof code.cards.rareattacks.Nebula) {
            code.cards.rareattacks.Nebula copy = (code.cards.rareattacks.Nebula) c.makeStatEquivalentCopy();
            copy.baseDamage = (int)(copy.baseDamage * 1.5);
            copy.damage = copy.baseDamage;
            copy.applyPowers();

            this.p.hand.addToHand(copy);
            this.p.hand.refreshHandLayout();
            AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect(copy.makeStatEquivalentCopy()));
        }

        purgeCount++;

        CardCrawlGame.dungeon.checkForPactAchievement();
    }

    private void purgeHeal() {
        if (healPerCard > 0 && purgeCount > 0) {
            int healAmount = purgeCount * healPerCard;
            addToBot(new HealAction(this.p, this.p, healAmount));
        }
    }
}

package code.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import code.cards.AbstractEasyCard;
import static com.megacrit.cardcrawl.actions.common.DrawCardAction.drawnCards;

public class CheckKosmoTagAction extends AbstractGameAction {

    private final AbstractMonster target;
    private final int amount;
    private final boolean canVuln;
    private final boolean canWeak;
    private final ArrayList<AbstractCard> toExhaust = new ArrayList<>();
    private boolean firstUpdate = true;

    public CheckKosmoTagAction(AbstractMonster target, int amount, boolean vuln, boolean weak) {
        this.target = target;
        this.amount = amount;
        this.canVuln = vuln;
        this.canWeak = weak;
        this.duration = this.startDuration = 0.5f; // Delay 0.5s
    }

    public CheckKosmoTagAction(AbstractMonster target, int amount, boolean vuln) {
        this(target, amount, vuln, false);
    }

    public CheckKosmoTagAction() {
        this(null, 0, false, false);
    }

    @Override
    public void update() {
        if (firstUpdate) {
            ArrayList<AbstractCard> lastDrawn = new ArrayList<>(drawnCards);

            for (AbstractCard card : lastDrawn) {
                if (card.hasTag(code.KosmoModTags.KOSMO)) {
                    AbstractEasyCard kosmoCard = (AbstractEasyCard) card;
                    if (!kosmoCard.hasGivenEnergyThisTurn) {
                        kosmoCard.hasGivenEnergyThisTurn = true;
                        addToTop(new GainEnergyAction(1));

                        if (canVuln && !target.isDeadOrEscaped()) {
                            addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new VulnerablePower(target, amount, false), amount));
                        }
                        if (canWeak && !target.isDeadOrEscaped()) {
                            addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(target, amount, false), amount));
                        }
                    }
                } else {
                    toExhaust.add(card);
                }
            }
            firstUpdate = false;
        }

        tickDuration();

        if (this.isDone) {
            for (AbstractCard card : toExhaust) {
                AbstractDungeon.player.hand.moveToExhaustPile(card);
            }

            drawnCards.clear();
        }
    }
}
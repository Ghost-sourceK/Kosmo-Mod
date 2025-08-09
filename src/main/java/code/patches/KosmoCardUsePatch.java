package code.patches;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;

import java.util.ArrayList;

import code.cards.rareattacks.Nebula;
import code.KosmoModTags;

@SpirePatch(
    clz = AbstractPlayer.class,
    method = "useCard"
)
public class KosmoCardUsePatch {

    @SpirePostfixPatch
    public static void afterUseCard(AbstractPlayer __instance, AbstractCard c) {
        if (c.hasTag(KosmoModTags.KOSMO)) {
            if (AbstractDungeon.player.hand.size() >= 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                return;
            }

            ArrayList<AbstractCard> nebulasToMove = new ArrayList<>();

            for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
                if (card instanceof Nebula) {
                    nebulasToMove.add(card);
                }
            }

            for (AbstractCard card : nebulasToMove) {
                if (AbstractDungeon.player.hand.size() >= 10) {
                    AbstractDungeon.player.createHandIsFullDialog();
                    break;
                }

                AbstractDungeon.player.exhaustPile.removeCard(card);
                AbstractDungeon.player.hand.addToHand(card);
                card.unhover();
                card.unfadeOut();
                card.fadingOut = false;
                card.applyPowers();
            }
        }
    }
}
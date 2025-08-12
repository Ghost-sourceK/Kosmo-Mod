package kosmomod.cards.power;

import static kosmomod.KosmoMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import kosmomod.KosmoModTags;
import kosmomod.cards.AbstractEasyCard;
import kosmomod.cards.special.DormantStar;
import kosmomod.character.KosmoCharacter;
import kosmomod.powers.KosmoStarArchitectPower;

public class KosmoStarArchitect extends AbstractEasyCard {
  public static final String ID = makeID("KosmoStarArchitect");

  private static final CardRarity RARITY = CardRarity.UNCOMMON;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.POWER;
  public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

  private DormantStar dormantStar = new DormantStar();

  public KosmoStarArchitect() {
    super(ID, 1, TYPE, RARITY, TARGET, COLOR);

    baseMagicNumber = 1;
    magicNumber = baseMagicNumber;
    this.cardsToPreview = dormantStar;
    tags.add(KosmoModTags.KOSMO);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower(KosmoStarArchitectPower.POWER_ID)) {
        KosmoStarArchitectPower power = (KosmoStarArchitectPower)p.getPower(KosmoStarArchitectPower.POWER_ID);
        power.addStacks(this.magicNumber, this.upgraded);
    } else {
        addToBot(new ApplyPowerAction(p, p, new KosmoStarArchitectPower(p, this.magicNumber, this.upgraded)));
    }
  }

  public void upp() {
    dormantStar.upgrade();
    this.cardsToPreview = dormantStar;
    this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
    initializeDescription();
  }
}

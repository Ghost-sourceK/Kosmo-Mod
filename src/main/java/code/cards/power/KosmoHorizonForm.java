package code.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import code.KosmoModTags;
import code.cards.AbstractEasyCard;

import code.character.KosmoCharacter;
import code.powers.KosmoHorizonFormPower;

import static code.KosmoMod.makeID;

public class KosmoHorizonForm extends AbstractEasyCard {
  public static final String ID = makeID("KosmoHorizonForm");

  private static final CardRarity RARITY = CardRarity.RARE;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = KosmoCharacter.Enums.VIOLET;

  public KosmoHorizonForm() {
    super(ID, 3, TYPE, RARITY, TARGET, COLOR);

    baseMagicNumber = 1;
    magicNumber = baseMagicNumber;
    this.isEthereal = true;
    tags.add(KosmoModTags.KOSMO);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m) {
    addToBot(new ApplyPowerAction(p, p, new KosmoHorizonFormPower(p, this.magicNumber)));
  }

  public void upp() {
    this.isEthereal = false;
    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    initializeDescription();
  }
}

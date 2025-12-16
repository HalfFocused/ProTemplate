package code.util.charUtil;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface AtDamageGiveToPower {
    float atDamageGiveTo(AbstractCreature target, float damage, DamageInfo.DamageType type, AbstractCard sourceCard);
}

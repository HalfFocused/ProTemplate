package code.util.charUtil;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface CardBlessedHook {
    abstract void onCardBlessed(AbstractCard card);
}

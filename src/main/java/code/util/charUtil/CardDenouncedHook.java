package code.util.charUtil;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface CardDenouncedHook {
    abstract void onCardDenounced(AbstractCard card);
}

package code.util.charUtil.mods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class EphemeralModifier extends AbstractCardModifier {

    public EphemeralModifier(){
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + "thestargazer:Ephemeral.";
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new EphemeralModifier();
    }

    @Override
    public String identifier(AbstractCard card) {
        return "ephemeral";
    }

    public boolean isInherent(AbstractCard card) {
        return true;
    }
}

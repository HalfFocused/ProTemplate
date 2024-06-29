package code.actions;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created by Jedi#3970:
 * https://github.com/Jedi515/sts-jedi/blob/6fe1abcf796567034324b55d276bc8554842cc47/src/mod/jedi/actions/ScryCallbackAction.java#L9
 */
public class ScryCallbackAction extends ScryAction
{
    public Consumer<ArrayList<AbstractCard>> callback;

    public ScryCallbackAction(int numCards, Consumer<ArrayList<AbstractCard>> callback)
    {
        super(numCards);
        this.callback = callback;
    }
}

package code.effects;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class InternalBleedingSlashEffect extends FlashAtkImgEffect {
    public InternalBleedingSlashEffect(float x, float y, AbstractGameAction.AttackEffect effect, boolean mute) {
        super(x, y, effect, mute);
        scale = Settings.scale * 0.75f;
        color = Color.SCARLET.cpy();
    }
}

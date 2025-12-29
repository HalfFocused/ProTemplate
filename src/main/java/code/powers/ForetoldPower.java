package code.powers;

import code.ModFile;
import code.TheDisplaced;
import code.effects.EyeGlintEffect;
import code.relics.Everything;
import code.util.TexLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ForetoldPower extends AbstractEasyPower {
    public AbstractCreature source;

    boolean hitDuringAttackUse = false;
    public boolean usedThisTurn = false;

    public static final String POWER_ID = ModFile.makeID("ForetoldPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ForetoldPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }
    @Override
    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))

    public void updateDescription() {
        description = AbstractDungeon.player.hasRelic(Everything.ID) ? DESCRIPTIONS[1] : DESCRIPTIONS[0];
    }

    public void atEndOfRound() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    public void reset(){
        usedThisTurn = false;
        setTexture(false);
    }

    /*
    Double the incoming damage if Foretold hasn't been used this turn.
    Triple it instead if the player has Everything.
     */
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && !usedThisTurn) {
            if(AbstractDungeon.player.hasRelic(Everything.ID)){
                return damage * 3.0f;
            }
            return damage * 2.0f;
        }
        return damage;
    }

    /*
    Some attacks calculate their damage more than once, which results in more than one onAttacked() call.
    So, we don't disable Foretold in this step, just mark it to be disabled later (after the card is done)
     */
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.type == DamageInfo.DamageType.NORMAL && !usedThisTurn && !hitDuringAttackUse){
            flash();
            AbstractPlayer p = AbstractDungeon.player;
            if(p instanceof TheDisplaced){
                AbstractGameEffect eyeGlint = new EyeGlintEffect(((TheDisplaced) p).getEyeX(), ((TheDisplaced) p).getEyeY());
                AbstractDungeon.topLevelEffectsQueue.add(eyeGlint);
            }
            AbstractRelic everything = p.getRelic(Everything.ID);
            if(everything != null){
                everything.flash();
            }
            hitDuringAttackUse = true;
        }
        return damageAmount;
    }

    /*
    After a card is finished resolving, if we've marked Foretold to be disabled, disable it!
     */
    public void onAfterUseCard(AbstractCard card, UseCardAction action){
        if(hitDuringAttackUse){
            hitDuringAttackUse = false;
            usedThisTurn = true;
            setTexture(true);
        }
    }

    /*
    Sometimes one attack queues up another card play (cough cough The Stars Aligned).
    In this case, The Stars Aligned will use Foretold, but the vision it plays will use it
    as well, since The Stars Aligned doesn't finish resolving. So, when we queue up a card, if we haven't
    finished resolving an attack that used foretold, spend the Foretold immediately.

    Hypothetically this would not work on some horribly designed attack that is some twisted fusion of
    Reckless Abandon and The Stars Aligned that plays an attack between random hits... but nobody would ever
    make such a thing, right? :P
     */

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(hitDuringAttackUse){
            hitDuringAttackUse = false;
            usedThisTurn = true;
            setTexture(true);
        }
    }


    private void setTexture(boolean used){
        Texture normalTexture;
        Texture hiDefImage;

        if(used){
            normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/Spent" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
            hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/Spent" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        }else{
            normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
            hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        }
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }
    }
}

package code.cards.collectible.common.attack;

import code.cards.AbstractEasyCard;

import static code.ModFile.makeID;

import code.util.charUtil.CardUtil;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlashDash extends AbstractEasyCard {
    public final static String ID = makeID("SlashDash");
    // intellij stuff attack, all_enemy, common, 7, 3, , , , 

    public SlashDash() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 7;
        isMultiDamage = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++){
            AbstractMonster mon = AbstractDungeon.getMonsters().monsters.get(i);
            if(!mon.isDeadOrEscaped()){
                addToBot(new DamageAction(mon, new DamageInfo(p, multiDamage[i], DamageInfo.DamageType.NORMAL), CardUtil.randomSlash()));
            }
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}
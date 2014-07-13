package character.jobs;

import character.Enemy;
import character.Obj;
import character.Player;


public class Acolyte extends Job{
    public Acolyte() {
        name = "Acolyte";

        s1 =  "Holy Light";
        s1Type = "attack";
        s1Info = "Casts a holy attack on the enemy";
        s1Cost = 10;

        s2 = "Heal";
        s2Type = "buff";
        s2Info = "Recovers 20 HP";
        s2Cost = 15;
    }

    public String skill1(Obj c, Player p) {
        if (p.getMp() < s1Cost) {
            return "Player does not have enough MP. ";
        }

        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();

        int damage = (int) (Math.ceil(((100 / (100 + enemyDef))) * p.getInt()) * 1.5);
        enemy.changeHp(damage);
        p.changeMp(s1Cost);

        return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. ";
    }

    public String skill2(Obj c, Player p) {
        return "";
    }
    public String skill1(Player p) { return ""; }

    public String skill2(Player p) {
        if (p.getHp() == p.getMaxHp()) {
            return "Player is at full health. ";
        }

        if (p.getMp() < s2Cost) {
            return "Player does not have enough MP. ";
        }

        int healAmt = (int)(p.getInt() * 1.5);
        p.addHp(healAmt);
        p.changeMp(s2Cost);
        return "Player used " + s2 + " and recovered " + healAmt + " HP. ";
    }
}

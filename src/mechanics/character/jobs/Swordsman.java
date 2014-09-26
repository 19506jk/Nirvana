package mechanics.character.jobs;

import mechanics.character.Enemy;
import mechanics.character.Obj;
import mechanics.character.Player;

public class Swordsman extends Job{
    public Swordsman() {
        name = "Swordsman";

        s1 =  "Bash";
        s1Type = "attack";
        s1Info = "A heavy attack against the enemy, stuns it for 2 rounds";
        s1Cost = 15;

        s2 = "Concentrate";
        s2Type = "buff";
        s2Info = "Focus in the battle, increases attack by 10 for 5 rounds";
        s2Cost = 15;
    }

    public String skill1(Obj c, Player p) {
        if (p.getMp() < s1Cost) {
            return "Player does not have enough MP. ";
        }

        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        p.subMp(s1Cost);
        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * playerAtk);
        enemy.changeHp(damage);
        int stunTimer = enemy.checkBuffTimer("stun");
        if (stunTimer == 0) {
            enemy.setDeBuff("stun", 2);
            return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. " + enemy.getName() + " is now stunned for 2 rounds. ";
        }
        else if (enemy.getHP() <= 0) {
            return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. ";
        }
        else {
            return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. " + enemy.getName() + " still stuns for " + stunTimer + " rounds. ";
        }
    }

    public String skill1(Player p) { return ""; }

    public String skill2(Obj c, Player p) {
        return "";
    }

    public String skill2(Player p) {
        if (p.checkBuffTimer("atk") > 0) {
            return "Concentrate is still in effect. ";
        }

        if (p.getMp() < s2Cost) {
            return "Player does not have enough MP. ";
        }

        p.subMp(s2Cost);
        p.changeBuff("atk", 5, 10);
        return "Player used " + s2 + ", attack is increased by 10 for 5 rounds. ";
    }
}

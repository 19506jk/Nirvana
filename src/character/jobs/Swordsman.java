package character.jobs;

import character.Enemy;
import character.Obj;
import character.Player;

public class Swordsman extends Job{
    public Swordsman(int choice, String first, String second, String type1, String type2, String info1, String info2, String passive) {
        super(choice, first, second, type1, type2, info1, info2, passive);
    }

    public String skill1(Obj c, Player p) {
        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * playerAtk);
        enemy.changeHp(damage);
        int stunTimer = enemy.checkBuffTimer("stun");
        if (stunTimer == 0) {
            enemy.setDeBuff("stun", 2);
            return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. " + enemy.getName() + " is now stunned for 2 rounds. ";
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
        p.changeBuff("atk", 5, 10);
        return "Player used " + s2 + ", attack is increased by 10 for 5 rounds. ";
    }
}

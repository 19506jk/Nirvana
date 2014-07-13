package character.jobs;

import character.Enemy;
import character.Obj;
import character.Player;

public class Rogue extends Job {

    public Rogue() {
        name = "Rogue";

        s1 = "Double Stab";
        s1Type = "attack";
        s1Info = "Stabs the enemy twice";
        s1Cost = 10;

        s2 = "Envenom";
        s2Type = "attack";
        s2Info = "Adds 15 attack to normal attack. Casts poison on the enemy, which decreases its health by 3% for 3 rounds";
        s2Cost = 10;
    }

    public String skill1(Obj c, Player p) {
        if (p.getMp() < s1Cost) {
            return "Player does not have enough MP. ";
        }

        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * playerAtk);
        enemy.changeHp(damage * 2);
        p.changeMp(s1Cost);

        return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage * 2 + " damage. ";
    }

    public String skill2(Obj c, Player p) {
        if (p.getMp() < s2Cost) {
            return "Player does not have enough MP. ";
        }

        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * (playerAtk + 15));
        enemy.changeHp(damage);
        enemy.setDeBuff("poison", 3);
        p.changeMp(s2Cost);

        return "Player used " + s2 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. ";

    }
    public String skill1(Player p) { return ""; }
    public String skill2(Player p) { return ""; }
}

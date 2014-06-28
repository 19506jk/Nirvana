package character.jobs;

import character.Enemy;
import character.Obj;
import character.Player;

public class Rogue extends Job {

    public Rogue(int choice, String first, String second, String type1, String type2, String info1, String info2, String passive) {
        super(choice, first, second, type1, type2, info1, info2, passive);
    }

    public String skill1(Obj c, Player p) {
        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * playerAtk);
        enemy.changeHp(damage * 2);

        return "Player used " + s1 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage * 2 + " damage. ";
    }

    public String skill2(Obj c, Player p) {
        Enemy enemy = (Enemy) c;
        float enemyDef = enemy.getDef();
        float playerAtk = p.getAtk();

        int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * (playerAtk + 15));
        enemy.changeHp(damage);
        enemy.setDeBuff("poison", 3);

        return "Player used " + s2 + " on " + enemy.getName() + "(" + enemy.getHP() + ")" + " and dealt " + damage + " damage. ";

    }
    public String skill1(Player p) { return ""; }
    public String skill2(Player p) { return ""; }
}

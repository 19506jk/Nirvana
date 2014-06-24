package character.jobs;

import character.Enemy;
import character.Obj;
import character.Player;

public class Knight extends Job{
    public Knight(int choice, String first, String second, String type1, String type2) {
        super(choice, first, second, type1, type2);
    }

    public String skill1(Obj c, Player p) {
        Enemy enemy = (Enemy) c;
        float enemydef = enemy.getDef();
        float playeratk = p.getatk();

        int damage = (int) Math.ceil(((100 / (100 + enemydef))) * playeratk);
        enemy.changehp(damage);
        enemy.setStunned(2);
        return "Player used Stun on " + enemy.getName() + " and dealt " + damage + " damage. " + enemy.getName() + " is now stunned for two rounds. ";
    }

    public String skill2(Obj c, Player p) {
        return "";
    }
}

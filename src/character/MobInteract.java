package character;
import enums.*;

public class MobInteract extends Interaction {

	public MobInteract() {
		super();
	}
	
	public void move(Obj o) {
		int count = 0;
		while (true) {
			if ( count > 100)
			{
				return;
			}

			int dir = (int) (Math.random() * 8);
			
			if (dir == Direction.NO.ordinal())
			{
				setDirection("no");
			}

			if (dir == Direction.SO.ordinal())
			{
				setDirection("so");
			}
		
			if (dir == Direction.EA.ordinal())
			{
				setDirection("ea");
			}

			if (dir == Direction.WE.ordinal())
			{
				setDirection("we");
			}

			if (dir == Direction.NE.ordinal())
			{
				setDirection("ne");
			}

			if (dir == Direction.NW.ordinal())
			{
				setDirection("nw");
			}

			if (dir == Direction.SE.ordinal())
			{
				setDirection("se");
			}

			if (dir == Direction.SW.ordinal())
			{
				setDirection("sw");
			}

			if (floor.getFloorType(tr, tc) == Square.values()[5])
			{
				if (floor.getObj(tr, tc) == null)
				{
					floor.setObj(tr, tc, o);
					floor.setObj(pr, pc, null);
					pr = tr;
					pc = tc;
					break;
				}
			}

			else
			{
				count++;
				continue;
			}	
		}
	}

	public void combat(Obj o) {
		Enemy enemy = (Enemy) o;
		Player player = (Player) (floor.scanPlayer(pr, pc));
		if ((int) (Math.random() * 2) == 1)
		{
			float enemyatk = enemy.getAtk();
			float playerdef = player.getdef();
			int damage = (int) (Math.ceil(((100 / (100 + playerdef))) * enemyatk));
			player.changehp(damage);
			String msg = "";
			msg += enemy.getRep();	
			msg += " deals " + damage + " to PC. ";
			floor.addmsg(msg);
		}
		else
		{
			floor.addmsg(enemy.getRep() + " missed attack on PC. ");
		}
	}


	Obj scan() {
		return floor.scanPlayer(pr, pc);
	}

}

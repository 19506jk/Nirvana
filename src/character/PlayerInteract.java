package character;

import enums.PotionType;
import enums.Square;

class PlayerInteract extends Interaction {

	public PlayerInteract() {
		super();
	}

    /*
        Setters
     */

    public int getRow() { return pr; }

    public int getCol() { return pc; }

    /*
        Methods
     */

	// type:
	// 0 = player
	// 1 = monster
	// 2 = gold
	// 3 = potion
	public void move(Obj o) {
		Player player = (Player)o;
		if (target != null)
		{
			int targettype = target.getType();
			if (targettype == 2)
			{
				Gold gold = (Gold)target;
				if (gold.getGoldType() == 3)
				{
					Dhorde dgold = (Dhorde)target;
					if (dgold.canopen())
					{
						player.changeGold(gold.giveGold());
						floor.clearCell(tr, tc);
						floor.setObj(tr, tc, o);
						floor.setObj(pr, pc, null);
						pr = tr;
						pc = tc;
						String msg =  "PC picked up 6 gold. ";
						floor.changemsg(msg);
					}
					else
					{
						String msg =  "Big Dragon is WATCHING YOU! ";
						floor.changemsg(msg);
					}
				}
				else
				{
					int amt = gold.giveGold();
					player.changeGold(gold.giveGold());
					floor.clearCell(tr, tc);
					floor.setObj(tr, tc, o);
					floor.setObj(pr, pc, null);
					pr = tr;
					pc = tc;
					String msg =  "PC picked up " + amt + " gold.";
					floor.changemsg(msg);
				}
			}
		}
		else
		{
			Square ftype = floor.getFloorType(tr, tc);
			if (ftype == Square.TILE || ftype == Square.DOOR || ftype == Square.PASSAGE)
			{
				String plist = "";
				Obj[] surround = floor.scanPotion(tr, tc);
				for (int i = 0; i < 8; i++) {
					if (surround[i] != null && surround[i].getType() == 3)
					{
						Potion p = (Potion) surround[i];
						if (player.checkPotion(p.getEffect().ordinal()) == 1)
						{
							plist += p.getName() + " ";
						}	

						else
						{
							plist += "an unknown ";
						}
					}
				}

				floor.setObj(tr,tc, o);
				floor.setObj(pr, pc, null);
				pr = tr;
				pc = tc;

				if (plist.equals(""))
				{
					String msg;
					msg = "PC moves " + direction + ". "; 
					floor.changemsg(msg);
				}
			
				else
				{
					String msg;
					plist += "potion. ";
					msg = "PC moves " + direction + " and sees " + plist;
					floor.changemsg(msg);
				}	

				for (int i = 0; i < 6; i++) {
					surround[i] = null;
				}
				surround = null;
					
			}
			
			else if (ftype == Square.HORIW || ftype == Square.VERTIW)
			{
				String msg = "PC tried to walk into a wall, but wall pushed PC back. ";
				floor.changemsg(msg);
			}

			if (ftype == Square.VOID)
			{
				String msg = "Darkness....";
				floor.changemsg(msg);
			}
		}
	}

	public void combat(Obj o) {
		Player player = (Player) o;
		if (target == null)
		{
			String msg = "PC just hit the air, but it was not very effective. ";
			floor.changemsg(msg);
			return;
		}

		int targettype = target.getType();
		if (targettype == 1)
		{

			Enemy enemy = (Enemy) target;

			if (enemy.getRep() == 'M')
			{
				Merchant merchant = (Merchant) target;
				merchant.changestatus();
			}

			float enemydef = enemy.getDef();
			float playeratk = player.getatk();
			int damage = (int) Math.ceil(((100 / (100 + enemydef))) * playeratk);
			enemy.changehp(damage);
			String msg = "PC deals " + damage + " to " + enemy.getRep() + ". (" + enemy.getHP() + " HP) ";
			floor.changemsg(msg);

			if (enemy.getHP() <= 0)
			{
				int money = enemy.giveGold();
				player.changeGold(enemy.giveGold());
				if (enemy.getRep() == 'M')
				{
					floor.clearCell(tr, tc);
					floor.spawnGold(tr, tc);
				}
				else if (enemy.getRep() == 'D')
				{
					Dragon dragon =(Dragon) enemy;
					dragon.notify();
					floor.clearCell(tr, tc);
				}
				else
				{
					floor.clearCell(tr, tc);
				}

				msg += "PC gets " + money + " Gold.";
				floor.changemsg(msg);
			}

		}
	}

	public void usePotion(Player p) {
		if (target == null)
		{
			return;
		}
		int targettype = target.getType();
		if (targettype == 3)
		{
			Potion potion = (Potion) target;
			PotionType type = potion.getEffect();
			if (p.checkPotion(type.ordinal()) == 0)
			{
				p.trackPotion(type.ordinal());
			}
			String name = potion.getName();
			p.changebuff(potion.getEffect(), potion.getVal());
			floor.clearCell(tr, tc);
			String msg = "PC uses " + name + ". ";
			floor.changemsg(msg);
		}
	}

}

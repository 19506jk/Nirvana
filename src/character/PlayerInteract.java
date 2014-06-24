package character;

import character.jobs.*;
import enums.JobClass;
import enums.PotionType;
import enums.Square;

class PlayerInteract extends Interaction {

    Job job = null;

	public PlayerInteract() {
		super();
	}

    /*
        Setters
     */

    public void setJob(int choice) {
        if (choice == JobClass.KNIGHT.ordinal())
        {
            job = new Knight(choice, "Stun", "WestSide", "attack", "buff");
        }
        else if (choice == JobClass.ROGUE.ordinal())
        {
            job = new Rogue(choice, "Double Stab", "WestSide", "attack", "buff");
        }
        else if (choice == JobClass.CRUSADER.ordinal())
        {
            job = new Rogue(choice, "Young Money", "WestSide", "combat", "buff");
        }
    }

    /*
        Getter
     */

    public int getRow() { return pr; }

    public int getCol() { return pc; }

    public String getJobName() { return job.getname(); }

    public String getS1() { return job.gets1(); }

    public String getS1Type() { return job.gets1Type(); }

    public String getS2() { return job.gets2(); }

    public String getS2Type() { return job.gets2Type(); }

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
			floor.changemsg("PC just hit the air, but it was not very effective. ");
			return;
		}

		int targettype = target.getType();
		if (targettype == 1)
		{

			Enemy enemy = (Enemy) target;

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
				if (enemy.getRep() == 'D')
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

    public void castSkill1(Player p) {
        if (target == null)
        {
            floor.changemsg("PC just hit the air, but it was not very effective. ");
            return;
        }
        if ("attack".equals(job.gets1Type())) {
            int targettype = target.getType();
            if (targettype == 1)
            {
                floor.changemsg(job.skill1(target, p));
            }
        }
        else if ("buff".equals(job.gets1Type())) {
            floor.changemsg(job.skill1(null, p));
        }

    }

}

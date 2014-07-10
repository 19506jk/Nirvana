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

        if (choice == JobClass.SWORDSMAN.ordinal())
        {
            job = new Swordsman();
        }
        else if (choice == JobClass.ROGUE.ordinal())
        {
            job = new Rogue();
        }
        else if (choice == JobClass.ACOLYTE.ordinal())
        {
            job = new Acolyte();
        }
    }

    /*
        Getter
     */

    public int getRow() { return pr; }

    public int getCol() { return pc; }

    public String getJobName() { return job.getName(); }

    public String getS1() { return job.gets1(); }

    public String getS1Type() { return job.gets1Type(); }

    public String getS2() { return job.gets2(); }

    public String getS2Type() { return job.gets2Type(); }

    public String getS1Info() { return job.getS1Info(); }

    public String getS2Info() { return job.getS2Info(); }

    public String getPassive() { return job.getPassive(); }

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
			int targetType = target.getType();
			if (targetType == 2)
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
					String msg =  "PC picked up " + amt + " gold. ";
					floor.changemsg(msg);
				}
			}
		}
		else
		{
			Square ftype = floor.getFloorType(tr, tc);
			if (ftype == Square.TILE || ftype == Square.DOOR || ftype == Square.PASSAGE)
			{
				floor.setObj(tr,tc, o);
				floor.setObj(pr, pc, null);
				pr = tr;
				pc = tc;

                String msg;
                msg = "PC moves " + direction + ". ";
                floor.changemsg(msg);
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

    private String endBattle(Enemy enemy, Player player) {
        if (enemy.getHP() > 0) {
            return "";
        }

        int money = enemy.giveGold();
        if ("ROGUE".equals(player.getJob())) {
            money = (int) (money * 1.5);
        }

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
        return "PC gets " + money + " Gold.";
    }

	public void combat(Obj o) {
		Player player = (Player) o;
		if (target == null)
		{
			floor.changemsg("PC just hit the air, but it was not very effective. ");
			return;
		}

		int targetType = target.getType();
		if (targetType == 1)
		{

			Enemy enemy = (Enemy) target;

			float enemyDef = enemy.getDef();
			float playerAtk = player.getAtk();
			int damage = (int) Math.ceil(((100 / (100 + enemyDef))) * playerAtk);
			enemy.changeHp(damage);
			String msg = "PC deals " + damage + " to " + enemy.getRep() + ". (" + enemy.getHP() + " HP) ";
			floor.changemsg(msg);

			if (enemy.getHP() <= 0)
			{
                msg += endBattle(enemy, player);
                floor.changemsg(msg);
			}

		}
	}

	public void usePotion(Player p) {} // Todo

    public void castSkill(Player p, int choice) {
        String skillType;

        if (choice == 1) {
            skillType = getS1Type();
        }
        else {
            skillType = getS2Type();
        }

        if ("attack".equals(skillType)) {
            if (target == null)
            {
                floor.changemsg("PC just hit the air, but it was not very effective. ");
                return;
            }

            int targetType = target.getType();
            if (targetType == 1)
            {
                String msg;
                if (choice == 1) {
                    msg = job.skill1(target, p);
                }
                else {
                    msg = job.skill2(target, p);
                }
                msg +=  endBattle((Enemy)target, p);
                floor.changemsg(msg);
            }
        }
        else if ("buff".equals(skillType)) {
            if (choice == 1) {
                floor.changemsg(job.skill1(p));
            }
            else {
                floor.changemsg(job.skill2(p));
            }
        }

    }

}

package character.jobs;

import character.Obj;
import character.Player;
import enums.JobClass;

public abstract class Job {
    String name, s1, s2, s1Type, s2Type, s1Info, s2Info;
    int s1Cost, s2Cost;

    abstract public String skill1(Obj c, Player p);
    abstract public String skill2(Obj c, Player p);
    abstract public String skill1(Player p);
    abstract public String skill2(Player p);
    public String getName() { return name; }
    public String gets1() { return s1; }
    public String gets2() { return s2; }
    public String gets1Type() { return s1Type; }
    public String gets2Type() { return s2Type; }
    public String getS1Info() { return s1Info; }
    public String getS2Info() { return s2Info; }
    public int getS1Cost() { return s1Cost; }
    public int getS2Cost() { return s2Cost; }


}

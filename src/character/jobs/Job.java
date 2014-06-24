package character.jobs;

import character.Obj;
import character.Player;
import enums.JobClass;

public abstract class Job {
    private String name, s1, s2, s1Type, s2Type;

    public Job(int choice, String first, String second, String sType1, String sType2) {
        name = JobClass.values()[choice].toString();
        s1 = first;
        s2 = second;
        s1Type = sType1;
        s2Type = sType2;
    }

    abstract public String skill1(Obj c, Player p);
    abstract public String skill2(Obj c, Player p);
    public String getname() { return name; }
    public String gets1() { return s1; }
    public String gets2() { return s2; }
    public String gets1Type() { return s1Type; }
    public String gets2Type() { return s2Type; }

}

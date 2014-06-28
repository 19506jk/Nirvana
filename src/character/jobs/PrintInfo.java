package character.jobs;

import character.Player;

public class PrintInfo {

    public void listSkills() {
        Player player = Player.getPlayer();
        System.out.println("Skills:");
        System.out.println("-------------------------------------");
        System.out.println("Passive: " + player.getPassive());
        System.out.println();
        System.out.println("1. " + player.getS1());
        System.out.println("Type: " + player.getS1Type());
        System.out.println(player.getS1Info());
        System.out.println();
        System.out.println("2. " + player.getS2());
        System.out.println("Type: " + player.getS2Type());
        System.out.println(player.getS2Info());
        System.out.println("-------------------------------------");

    }
}

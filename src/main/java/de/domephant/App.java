package de.domephant;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.util.Scanner;

public class App extends JFrame {
    public final Panel[][] grid = new Panel[8][8];
    private static String player1;
    private static String player2;

    public App() {
        this.setLayout(new GridLayout(8, 8));
        this.setSize(900, 900);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Panel();
                if ((i*8 + j + 1) % 2 == 0) {
                    if(i % 2 == 0) {
                        grid[i][j].setBackground(Color.BLACK);
                    } else {
                        grid[i][j].setBackground(Color.WHITE);
                    }
                } else {
                    if(i % 2 != 0) {
                        grid[i][j].setBackground(Color.BLACK);
                    } else {
                        grid[i][j].setBackground(Color.WHITE);
                    }
                }
                this.add(grid[i][j]);
            }
        }
        this.setVisible(true);
    }

    public static void main( String[] args )
    {
        System.out.println("Enter start parameters: ");
        checkParameters(new Scanner(System.in).nextLine());
        new App();
    }

    public static void checkParameters(String a) {
        String input = a;
        boolean parametersCorrect = false;
        while(!parametersCorrect) {
            if(input == null) {
                System.out.println("Wrong Parameters!");
                System.out.println("Enter start parameters: ");
                input = new Scanner(System.in).nextLine();
            } else {
                int spaceCounter = 0;
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == ' ') {
                        spaceCounter++;
                    }
                }
                if(spaceCounter > 1) {
                    String[] params = input.split(" ");
                    switch (params[0]) {
                        case "start":
                            if(setPlayer(params[1], "Player1") && setPlayer(params[2], "Player2")) {
                                parametersCorrect = true;
                            } else {
                                System.out.println("Wrong Parameters!");
                                System.out.println("Enter start parameters: ");
                                input = new Scanner(System.in).nextLine();
                                break;
                            }
                            break;
                        case "exit":
                            System.exit(0);
                            break;
                    }
                } else {
                    System.out.println("Wrong Parameters!");
                    System.out.println("Enter start parameters: ");
                    input = new Scanner(System.in).nextLine();
                }
            }
        }
    }
    public static boolean setPlayer(String input, String player) {
        switch (input) {
            case "user":
                if(player.equals("Player1")) {
                    player1 = "user";
                } else {
                    player2 = "user";
                }
                break;
            case "bot":
                if(player.equals("Player1")) {
                    player1 = "bot";
                } else {
                    player2 = "bot";
                }
                break;
            default:
                return false;
        }
        return true;
    }
}

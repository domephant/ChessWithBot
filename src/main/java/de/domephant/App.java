package de.domephant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class App extends JFrame {
    public final Panel[][] grid = new Panel[8][8];
    private static String player1;
    private static String player2;

    public App() throws IOException {
        this.setLayout(new GridLayout(8, 8));
        this.setSize(900, 900);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Panel();
                if ((i*8 + j + 1) % 2 == 0) {
                    if(i % 2 == 0) {
                        grid[i][j].setBackground(Color.GRAY);
                    } else {
                        grid[i][j].setBackground(Color.WHITE);
                    }
                } else {
                    if(i % 2 != 0) {
                        grid[i][j].setBackground(Color.GRAY);
                    } else {
                        grid[i][j].setBackground(Color.WHITE);
                    }
                }
                this.add(grid[i][j]);
            }
        }
        //Pawn Spawning
        BufferedImage img_black = ImageIO.read(new File("src/main/resources/assets/black/png/Chess_pdt60.png"));
        BufferedImage img_white = ImageIO.read(new File("src/main/resources/assets/white/png/Chess_plt60.png"));
        for(int h = 0; h < 8; h++) {
            grid[1][h].add(new JLabel(new ImageIcon(img_black)));
            grid[6][h].add(new JLabel(new ImageIcon(img_white)));
        }

        //Rook Spawning
        //Black
        grid[0][0].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_rdt60.png")).getScaledInstance(100, 100, 1))));
        grid[0][7].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_rdt60.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][0].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_rlt60.png")).getScaledInstance(100, 100, 1))));
        grid[7][7].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_rlt60.png")).getScaledInstance(100, 100, 1))));

        //Knight Spawning
        //Black
        grid[0][1].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_ndt60.png")).getScaledInstance(100, 100, 1))));
        grid[0][6].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_ndt60.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][1].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_nlt60.png")).getScaledInstance(100, 100, 1))));
        grid[7][6].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_nlt60.png")).getScaledInstance(100, 100, 1))));

        //Bishop Spawning
        //Black
        grid[0][2].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_bdt60.png")).getScaledInstance(100, 100, 1))));
        grid[0][5].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_bdt60.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][2].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_blt60.png")).getScaledInstance(100, 100, 1))));
        grid[7][5].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_blt60.png")).getScaledInstance(100, 100, 1))));

        //Queen Spawning
        //Black
        grid[0][3].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_qdt60.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][3].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_qlt60.png")).getScaledInstance(100, 100, 1))));

        //King Spawning
        //Black
        grid[0][4].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/Chess_kdt60.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][4].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/Chess_klt60.png")).getScaledInstance(100, 100, 1))));

        this.setVisible(true);
    }

    public static void main( String[] args ) throws IOException {
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

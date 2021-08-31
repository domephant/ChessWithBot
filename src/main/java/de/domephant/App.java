package de.domephant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class App extends JFrame {
    public final Panel[][] grid = new Panel[8][8];
    private static String player1;
    private static String player2;

    public App() throws IOException {
        //Basic Layout
        this.setLayout(new GridLayout(8, 8));
        this.setSize(900, 900);
        this.setResizable(false);
        this.setTitle("Chess");
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // Add MenuBarItems
        JMenu menuFile = new JMenu("File");
        JMenu menuGame = new JMenu("Game");
        JMenuItem enterPosition = new JMenuItem("Enter Position");

        JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem startGame = new JMenuItem("Start Game");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start game");
            }
        });

        JMenuItem swapColors = new JMenuItem("Swap Colors");
        menuFile.add(enterPosition);
        menuFile.add(exitGame);
        menuGame.add(swapColors);
        menuGame.add(startGame);
        menuBar.add(menuFile);
        menuBar.add(menuGame);
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
        Image img_black = ImageIO.read(new File("src/main/resources/assets/black/png/pawn_black.png")).getScaledInstance(100, 100, 1);
        Image img_white = ImageIO.read(new File("src/main/resources/assets/white/png/pawn_white.png")).getScaledInstance(100, 100, 1);
        for(int h = 0; h < 8; h++) {
            grid[1][h].add(new JLabel(new ImageIcon(img_black)));
            grid[6][h].add(new JLabel(new ImageIcon(img_white)));
        }

        //Rook Spawning
        //Black
        grid[0][0].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/rook_black.png")).getScaledInstance(100, 100, 1))));
        grid[0][7].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/rook_black.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][0].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/rook_white.png")).getScaledInstance(100, 100, 1))));
        grid[7][7].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/rook_white.png")).getScaledInstance(100, 100, 1))));

        //Knight Spawning
        //Black
        grid[0][1].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/knight_black.png")).getScaledInstance(100, 100, 1))));
        grid[0][6].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/knight_black.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][1].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/knight_white.png")).getScaledInstance(100, 100, 1))));
        grid[7][6].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/knight_white.png")).getScaledInstance(100, 100, 1))));

        //Bishop Spawning
        //Black
        grid[0][2].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/bishop_black.png")).getScaledInstance(100, 100, 1))));
        grid[0][5].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/bishop_black.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][2].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/bishop_white.png")).getScaledInstance(100, 100, 1))));
        grid[7][5].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/bishop_white.png")).getScaledInstance(100, 100, 1))));

        //Queen Spawning
        //Black
        grid[0][3].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/queen_black.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][3].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/queen_white.png")).getScaledInstance(100, 100, 1))));

        //King Spawning
        //Black
        grid[0][4].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/black/png/king_black.png")).getScaledInstance(100, 100, 1))));
        //White
        grid[7][4].add(new JLabel(new ImageIcon(ImageIO.read(new File("src/main/resources/assets/white/png/king_white.png")).getScaledInstance(100, 100, 1))));

        this.setVisible(true);
    }

    public static void main( String[] args ) throws IOException {
        new App();
    }
}

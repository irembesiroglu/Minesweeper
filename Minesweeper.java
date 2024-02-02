import minesweeper.Box;
import minesweeper.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JFrame {
    private Timer timer;
    private TimerTask timerTask;
    private static Color c = Color.white;
    private Game game;
    private final int IMAGE_SIZE = 50;
    public JPanel panel;
    private JLabel label;
    private boolean isGameFinished = false; // Yeni değişken oyunun bitip bitmediğini kontrol etmek için.

    public Minesweeper(int COLS, int ROWS, int BOMBS) {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            int seconds = 0;

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    label.setText(getMessage() + " | Time: " + seconds + "s");
                    seconds++;
                    if (isGameFinished) {
                        cancel();
                        label.setText(getMessage() + " | Time: " + (seconds - 1) + "s");
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    private void initLabel() {
        label = new JLabel(getMessage());
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);
        startTimer();
    }
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }


    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(getClass().getResource("baaaaaack.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x* IMAGE_SIZE + 5,
                            coord.y* IMAGE_SIZE + 5, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;

                Coord coord = new Coord(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        game.pressLeftButton(coord);
                        label.setText(getMessage());
                        panel.repaint();
                        panel.setOpaque(false);
                        if (game.getState() != GameState.PLAYED) {
                            isGameFinished = true; // Oyun bittiğinde isGameFinished değerini true yap.
                        }
                        break;
                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coord);
                        label.setText(getMessage());
                        panel.repaint();
                        panel.setOpaque(false);
                        if (game.getState() != GameState.PLAYED) {
                            isGameFinished = true;
                            stopTimer();
                        }
                        break;
                    case MouseEvent.BUTTON2:
                        dispose();
                        ChooseMenu chooseMenu = new ChooseMenu();
                        chooseMenu.createChooseMenu();
                        break;
                }
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE + 10,
                Ranges.getSize().y * IMAGE_SIZE + 10));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Minesweeper/Game");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(c);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
        setIconImage(getImage("icon"));
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private String getMessage() {
        GameState gm = game.getState();
        if (gm == GameState.BOMBED) {
            c = Color.red;
            return "You lose. For create a new game press Roller";
        } else if (gm == GameState.WINNER) {
            c = Color.green;
            return "Congratulations, all bombs have been marked.";
        } else if (gm == GameState.PLAYED) {
            c = Color.white;
            return "Think twice. Flagged " + game.getTotalFlagged() + " of " + game.getTotalBombs() + " bombs";
        } else {
            return "Welcome!";
        }
    }

}

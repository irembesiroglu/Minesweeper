import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JFrame {
    private JButton startButton;
    private JButton htpButton;
    private JButton exitButton;


    public void createMenu() {
        JFrame frame = new JFrame("Menu");
        frame.setSize(720, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(getClass().getResource("backk.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        BufferedImage startImage = loadImage("startb.png");
        BufferedImage exitImage = loadImage("exitb.png");
        BufferedImage htpImage = loadImage("htpb.png");

        startButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(startImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        startButton.setPreferredSize(new Dimension(314, 97));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ChooseMenu chooseMenu = new ChooseMenu();
                chooseMenu.createChooseMenu();

            }
        });

        htpButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(htpImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        htpButton.setPreferredSize(new Dimension(314, 97));
        htpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HowToPlay howToPlay = new HowToPlay();
                howToPlay.createHPTMenu();
                System.out.println("how to play screen was opened");
            }
        });

        exitButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(exitImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        exitButton.setPreferredSize(new Dimension(314, 97));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(Box.createGlue(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createVerticalStrut(100), gbc); // Add empty space before the buttons

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(startButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(htpButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(exitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(Box.createGlue(), gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

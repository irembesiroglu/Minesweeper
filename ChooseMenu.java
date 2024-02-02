import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChooseMenu extends JFrame {
    private JButton nineButton;
    private JButton twelveButton;
    private JButton fifteenButton;
    public void createChooseMenu() {
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

        BufferedImage nineImage = loadImage("9x9.png");
        BufferedImage twelveImage = loadImage("12x12.png");
        BufferedImage fifteenImage = loadImage("16x16.png");

        nineButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(nineImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        nineButton.setPreferredSize(new Dimension(314, 97));
        nineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Minesweeper(9,9,12);

            }
        });

        twelveButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(twelveImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        twelveButton.setPreferredSize(new Dimension(314, 97));
        twelveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new Minesweeper(12,12,20);
            }
        });

        fifteenButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fifteenImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fifteenButton.setPreferredSize(new Dimension(314, 97));
        fifteenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Minesweeper(15,15,30);
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
        panel.add(nineButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(twelveButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(fifteenButton, gbc);

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

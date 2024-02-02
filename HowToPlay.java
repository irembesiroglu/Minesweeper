import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HowToPlay extends JFrame {
    private JButton backButton;

    public void createHPTMenu() {
        JFrame frame = new JFrame("HTP");
        frame.setSize(720, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(getClass().getResource("howToPlay.png"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        BufferedImage backImage = loadImage("exitb.png");
        backButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        System.out.println("screens layout setted");
        backButton.setPreferredSize(new Dimension(314, 97));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu Menu = new Menu();
                Menu.createMenu();

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(Box.createGlue(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createVerticalStrut(400), gbc); // Add empty space before the buttons

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(backButton, gbc);

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

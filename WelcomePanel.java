import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    
    public WelcomePanel() {
        setLayout(new BorderLayout());

        // Notice the use of HTML tags for line breaks and alignment
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>Welcome to the<br/>Vending Machine Factory Simulator</div></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.decode("#CEA0AA"));
        setBackground(Color.decode("#FEE1DD"));
        add(welcomeLabel, BorderLayout.CENTER);

        // North panel with "Haz Factory" label
        JPanel northPanel = new JPanel();
        JLabel hazFactoryLabel = new JLabel("Haz Factory");
        hazFactoryLabel.setFont(new Font("Consolas", Font.BOLD, 24));
        hazFactoryLabel.setForeground(Color.decode("#CEA0AA"));
        northPanel.setBackground(Color.decode("#E9C2C5"));
        northPanel.add(hazFactoryLabel);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.decode("#E9C2C5"));

        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
    }
}

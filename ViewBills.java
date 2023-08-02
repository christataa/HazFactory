import javax.swing.*;
import java.awt.*;

public class ViewBills extends JPanel {

    private JButton[][] buttons = new JButton[4][3];

    public ViewBills(){
        setLayout(new BorderLayout());
        JPanel moneyPanel = new JPanel(new GridLayout(4, 3));

        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                buttons[i][j] = new JButton();
                ImageIcon icon = new ImageIcon("Money" + ((i * 3) + j + 1) + ".png");
                buttons[i][j].setIcon(icon);
                moneyPanel.add(buttons[i][j]);
            }
        }

        add(moneyPanel, BorderLayout.CENTER);
    }
}

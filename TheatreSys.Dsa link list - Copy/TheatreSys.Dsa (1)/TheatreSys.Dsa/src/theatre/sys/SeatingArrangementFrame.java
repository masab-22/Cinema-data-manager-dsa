
package theatre.sys;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SeatingArrangementFrame extends JFrame {

    public SeatingArrangementFrame(JFrame previousFrame) {
        setTitle("Seating Arrangement");
        setSize(600, 500); // Adjusted the size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x697086)); // Set the background color of the frame

        // Added title label
        JLabel titleLabel = new JLabel("Seating Arrangement");
        titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setBounds(50, 10, 300, 300);

        JPanel seatingPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        seatingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add smaller JLabels with images to the seating arrangement
        for (int i = 0; i < 12; i++) {
            JLabel seatLabel = new JLabel();
            seatLabel.setPreferredSize(new Dimension(100, 100)); // Adjusted the size
            seatLabel.setHorizontalAlignment(JLabel.CENTER);
            seatLabel.setVerticalAlignment(JLabel.CENTER);
            seatLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Load and set image for the seatLabel
            try {
                Image img;
                if (i == 0 || i == 1 || i == 2) { // Set 2nd seat of 1st row, 2nd seat of 2nd row, and 3rd seat of 3rd row as red
                    img = ImageIO.read(new File("E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\seat_selected.png"));
                } else {
                    img = ImageIO.read(new File("E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\seat_sale.png"));
                }
                seatLabel.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            seatingPanel.add(seatLabel);
        }

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> {
            dispose();  // Close the current frame
            previousFrame.setVisible(true);  // Show the previous frame (homepage)
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(seatingPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0xDED864));
        button.setForeground(new Color(0x0F0F11));
        button.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Testing Seating Arrangement Frame");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(400, 400);
            testFrame.setLocationRelativeTo(null);

            JButton totalBtn = new JButton("Total Seats");
            totalBtn.addActionListener(e -> {
                testFrame.setVisible(false); // Hide the test frame
                SeatingArrangementFrame seatingFrame = new SeatingArrangementFrame(testFrame);
                seatingFrame.setVisible(true); // Show the seating arrangement frame
            });

            JPanel testPanel = new JPanel();
            testPanel.add(totalBtn);

            testFrame.setContentPane(testPanel);
            testFrame.setVisible(true);
            JLabel label = new JLabel();
            ImageIcon image = new ImageIcon("E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\cinema.png");
            testFrame.setIconImage(image.getImage());
        });
    }
}



package theatre.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SeatsLeftFrame extends JFrame {

    private LinkedList<Integer> bookedSeats;
    private JLabel[] seatLabels;

    public SeatsLeftFrame(JFrame previousFrame) {
        setTitle("Seats Left");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel seatsLeftPanel = new JPanel(new GridLayout(4, 6, 10, 10)); // Increased to 12 seats
        seatsLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        seatsLeftPanel.setBackground(new Color(0x697086));

        seatLabels = new JLabel[12];  // Increased to 12 seats

        // Add JLabels to the seats left arrangement
        for (int i = 0; i < 12; i++) {
            seatLabels[i] = new JLabel();
            seatLabels[i].setHorizontalAlignment(JLabel.CENTER);
            seatLabels[i].setVerticalAlignment(JLabel.CENTER);
            seatLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            seatLabels[i].setName(String.valueOf(i + 1)); // Set seat number as the name

            seatLabels[i].addMouseListener(new SeatClickListener());

            seatsLeftPanel.add(seatLabels[i]);
        }

        // Set initially booked seats
        bookedSeats = new LinkedList<>();
        bookedSeats.add(1);
        bookedSeats.add(2);
        bookedSeats.add(3);

        updateSeatImages();

        JButton backButton = createStyledButton("Back", new Color(0xDED864), new Color(0x0F0F11), 18);
        backButton.addActionListener(e -> {
            dispose();  // Close the current frame
            previousFrame.setVisible(true);  // Show the previous frame (homepage)
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(seatsLeftPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.EAST);

        setContentPane(mainPanel);
    }

    private JButton createStyledButton(String text, Color backgroundColor, Color foregroundColor, int fontSize) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Gill Sans MT", Font.PLAIN, fontSize));
        return button;
    }

    private void updateSeatImages() {
        for (JLabel seatLabel : seatLabels) {
            int seatNumber = Integer.parseInt(seatLabel.getName());

            try {
                if (bookedSeats.contains(seatNumber)) {
                    // Set image for booked seat
                    seatLabel.setIcon(new ImageIcon("E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\seat_sold.png"));
                } else {
                    // Set image for available seat
                    seatLabel.setIcon(new ImageIcon("E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\seat_sale.png"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    private class SeatClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            int seatNumber = Integer.parseInt(clickedLabel.getName());

            if (bookedSeats.contains(seatNumber)) {
                if (isVipSeat(seatNumber)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "This seat is reserved for VIP guests.",
                            "VIP Seat Booking Alert",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Seat " + seatNumber + " is already booked.",
                            "Seat Booking Alert",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                if (isVipSeat(seatNumber)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "This seat is reserved for VIP guests.",
                            "VIP Seat Booking Alert",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    bookedSeats.add(seatNumber);
                    updateSeatImages();
                    JOptionPane.showMessageDialog(
                            null,
                            "Seat " + seatNumber + " booked successfully.",
                            "Seat Booking",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }

        private boolean isVipSeat(int seatNumber) {
            // Define the VIP seats (2nd seat of 1st row, 2nd seat of 2nd row, and 3rd seat of 3rd row)
            return (seatNumber == 1 || seatNumber == 2 || seatNumber == 3);
        }
    } }
package theatre.sys;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatBookingFrame extends JFrame {
    private JTextField customerNameField;
    private JTextField movieNameField;
    private JTextField rowField;
    private JTextField seatsToBookField;

    public SeatBookingFrame(JFrame previousFrame) {
        setTitle("Seat Booking Form");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(null);
        bookingPanel.setBackground(new Color(0x697086));

        JLabel mainTitleLabel = new JLabel("Seat Booking");
        mainTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 48));
        mainTitleLabel.setForeground(Color.WHITE);
        mainTitleLabel.setBounds(200, 20, 400, 60);
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        customerNameField = createStyledTextField();
        customerNameField.setBounds(300, 100, 200, 30);

        movieNameField = createStyledTextField();
        movieNameField.setBounds(300, 150, 200, 30);

        rowField = createStyledTextField();
        rowField.setBounds(300, 200, 200, 30);

        seatsToBookField = createStyledTextField();
        seatsToBookField.setBounds(350, 250, 150, 30);

        JButton submitButton = createStyledButton("Submit", 300, 320, 150, 30, e -> {
            if (areFieldsEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    int numSeats = Integer.parseInt(seatsToBookField.getText());
                    if (numSeats < 0) {
                        throw new NumberFormatException();
                    }

                    int totalPrice = numSeats * 2400; // Fixed seat price

                    // Display an alert for successful booking
                    JOptionPane.showMessageDialog(this, "Booking Approved!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Pass user information to the homepage for later retrieval
                    String customerName = customerNameField.getText();
                    String movieName = movieNameField.getText();
                    String row = rowField.getText();
                    String seatsToBook = seatsToBookField.getText();

                    // Call a method in ViewReceiptFrame to handle the receipt
                    ViewReceiptFrame.handleReceipt(customerName, movieName, row, seatsToBook, totalPrice);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Number of seats must be a valid positive integer", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        bookingPanel.add(mainTitleLabel);
        bookingPanel.add(createStyledLabel("Customer Name:", 100, 100, 150, 30));
        bookingPanel.add(customerNameField);
        bookingPanel.add(createStyledLabel("Movie Name:", 100, 150, 150, 30));
        bookingPanel.add(movieNameField);
        bookingPanel.add(createStyledLabel("Specify a Row:", 100, 200, 150, 30));
        bookingPanel.add(rowField);
        bookingPanel.add(createStyledLabel("Number of Seats to be Booked:", 100, 250, 250, 30));
        bookingPanel.add(seatsToBookField);
        bookingPanel.add(submitButton);
        bookingPanel.add(createStyledSeparator(250, 290, 300, 10));
        bookingPanel.add(createStyledButton("Back", 300, 350, 150, 30, e -> {
            dispose();  // Close the current frame
            previousFrame.setVisible(true);  // Show the previous frame (homepage)
        }));

        setContentPane(bookingPanel);
    }

    private boolean areFieldsEmpty() {
        return customerNameField.getText().isEmpty() ||
                movieNameField.getText().isEmpty() ||
                rowField.getText().isEmpty() ||
                seatsToBookField.getText().isEmpty();
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        textField.setBackground(new Color(0xFFD2D2D9, true));
        textField.setForeground(new Color(0x0F0F11));
        return textField;
    }

    private JLabel createStyledLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JSeparator createStyledSeparator(int x, int y, int width, int height) {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setBounds(x, y, width, height);
        return separator;
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0xDED864));
        button.setForeground(new Color(0x0F0F11));
        button.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Testing Seat Booking Frame");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(400, 400);
            testFrame.setLocationRelativeTo(null);

            JButton bookingBtn = new JButton("Seat Booking");
            bookingBtn.addActionListener(e -> {
                testFrame.setVisible(false);
                SeatBookingFrame bookingFrame = new SeatBookingFrame(testFrame);
                bookingFrame.setVisible(true);
            });

            JPanel testPanel = new JPanel();
            testPanel.add(bookingBtn);

            testFrame.setContentPane(testPanel);
            testFrame.setVisible(true);
        });
    }
}

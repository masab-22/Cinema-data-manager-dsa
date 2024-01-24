package theatre.sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Login {
    private JPanel login;
    private JTextField user_input;
    private JPasswordField user_pass;
    private JButton login_btn;
    private JLabel show;
    private final String pass = "welcometodsu";
    private final String input = "DsuTheatre";
    private JFrame signInFrame;
    private JFrame homepage;

    public Login(JFrame signInFrame) {
        this.signInFrame = signInFrame;

        login_btn.addActionListener(e -> performLogin());

        user_input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    user_pass.requestFocus();
                }
            }
        });

        user_pass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    login_btn.requestFocus();
                }
            }
        });

        login_btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }

    private void performLogin() {
        if (user_input.getText().isEmpty() && user_pass.getPassword().length == 0) {
            show.setText("Fields are empty");
        } else if (!user_input.getText().isEmpty()) {
            if (user_pass.getPassword().length == 0) {
                show.setText("Password field is empty");
            } else if (user_input.getText().equalsIgnoreCase(input) && new String(user_pass.getPassword()).equalsIgnoreCase(pass)) {
                navigateToHomePage();
            } else {
                show.setText("Incorrect");
            }
        } else {
            show.setText("Username field is empty");
        }
    }

    private void navigateToHomePage() {
        signInFrame.setVisible(false); // Hide the sign-in frame

        // Create components for the home page within the same frame
        homepage = new JFrame("Theatre Home Page");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setSize(800, 500);
        homepage.setLocationRelativeTo(null);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(0x697086)); // Set the background color of the frame

        JLabel mainTitleLabel = new JLabel("Home");
        mainTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 48));
        mainTitleLabel.setForeground(Color.WHITE);
        mainTitleLabel.setBounds(200, 20, 400, 60);
        mainTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel subtitleLabel = new JLabel("Data Manager");
        subtitleLabel.setFont(new Font("Droid Sans Mono Dotted", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(0x0F0F11));
        subtitleLabel.setBounds(200, 80, 400, 30);
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton totalBtn = createStyledButton("Total Seats");
        totalBtn.setBounds(100, 150, 150, 30);
        totalBtn.addActionListener(e -> {
            homepage.setVisible(false); // Hide the home page frame
            theatre.sys.SeatingArrangementFrame seatingFrame = new theatre.sys.SeatingArrangementFrame(homepage);
            seatingFrame.setVisible(true); // Show the seating arrangement frame
        });

        JButton bookSeatBtn = createStyledButton("Book a Seat");
        bookSeatBtn.setBounds(300, 150, 150, 30);
        bookSeatBtn.addActionListener(e -> {
            homepage.setVisible(false); // Hide the home page frame
            theatre.sys.SeatBookingFrame BookingFrame = new theatre.sys.SeatBookingFrame(homepage);
            BookingFrame.setVisible(true); // Show the seating arrangement frame
        });

        JButton seatsLeftBtn = createStyledButton("Seats Left");
        seatsLeftBtn.setBounds(500, 150, 150, 30);
        seatsLeftBtn.addActionListener(e -> {
            homepage.setVisible(false); // Hide the home page frame
            theatre.sys.SeatsLeftFrame BookingFrame = new theatre.sys.SeatsLeftFrame(homepage);
            BookingFrame.setVisible(true); // Show the seating arrangement frame
        });

        JButton receiptBtn = createStyledButton("View Receipt");
        receiptBtn.setBounds(300, 250, 150, 30);
        receiptBtn.addActionListener(e -> {
            homepage.setVisible(false); // Hide the home page frame
            theatre.sys.ViewReceiptFrame receiptFrame = new theatre.sys.ViewReceiptFrame(homepage);
            receiptFrame.setVisible(true); // Show the receipt frame
        });


        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setBounds(250, 320, 300, 10);

        JButton signOutBtn = createStyledButton("Sign Out");
        signOutBtn.setBounds(300, 350, 150, 30);
        signOutBtn.addActionListener(e -> {
            homepage.dispose();  // Close the current home frame
            signInFrame.setVisible(true);  // Show the sign-in frame
        });

        homePanel.add(mainTitleLabel);
        homePanel.add(subtitleLabel);
        homePanel.add(totalBtn);
        homePanel.add(bookSeatBtn);
        homePanel.add(seatsLeftBtn);
        homePanel.add(receiptBtn);
        homePanel.add(separator);
        homePanel.add(signOutBtn);

        homepage.setContentPane(homePanel);
        homepage.revalidate(); // Ensure that the changes are applied
        homepage.repaint();    // Repaint the frame to reflect the changes
        homepage.setVisible(true); // Make the modified frame visible
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0xDED864));
        button.setForeground(new Color(0x0F0F11));
        button.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame signInFrame = new JFrame("Cinema Data Manager");
            signInFrame.setContentPane(new Login(signInFrame).login);
            signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signInFrame.setSize(800, 500);
            signInFrame.setLocationRelativeTo(null);
            signInFrame.setVisible(true);
            JLabel label = new JLabel();
            ImageIcon image = new ImageIcon( "E:\\TheatreSys.Dsa (upd)\\TheatreSys.Dsa link list - Copy\\TheatreSys.Dsa (1)\\TheatreSys.Dsa\\picture\\cinema.png" );
            signInFrame.setIconImage( image.getImage() );

        });
    }
}

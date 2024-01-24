package theatre.sys;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class ViewReceiptFrame extends JFrame {


    ViewReceiptFrame(JFrame previousFrame) {
        // Modify the constructor to accept a JFrame argument
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("View Receipt");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);

            // ... (existing code)

            frame.setVisible(true);
        });
    }

    // calling this to seat booking frame

    public static void handleReceipt(String name, String movie, String seat, String seatsToBook, int totalAmount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        StringBuilder receipt = new StringBuilder();
        receipt.append("*** Theatre Receipt ***\n");
        receipt.append("Customer Name: ").append(name).append("\n");
        receipt.append("Movie Name: ").append(movie).append("\n");
        receipt.append("Seat Number: ").append(seat).append("\n");
        receipt.append("Seats to Book: ").append(seatsToBook).append("\n");
        receipt.append("Total Amount: $").append(decimalFormat.format(totalAmount)).append("\n");
        receipt.append("*************");

        JOptionPane.showMessageDialog(null, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);

        // condition for user if they want to save the receipt
        int saveOption = JOptionPane.showConfirmDialog(null, "Do you want to save this receipt?", "Save Receipt", JOptionPane.YES_NO_OPTION);
        if (saveOption == JOptionPane.YES_OPTION) {
            saveReceiptToFile(receipt.toString());
        }
    }

    private static void saveReceiptToFile(String receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
            writer.write(receipt);
            JOptionPane.showMessageDialog(null, "Receipt saved to 'receipt.txt'", "Receipt Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving receipt to file", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // Handle the exception according to your application's needs
        }
    }

    // main

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Testing View Receipt Frame");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setSize(400, 400);
            testFrame.setLocationRelativeTo(null);

            JButton receiptBtn = new JButton("View Receipt");
            receiptBtn.addActionListener(e -> {
                testFrame.setVisible(false);
                ViewReceiptFrame receiptFrame = new ViewReceiptFrame(testFrame);
                receiptFrame.setVisible(true);
            });

            JPanel testPanel = new JPanel();
            testPanel.add(receiptBtn);

            testFrame.setContentPane(testPanel);
            testFrame.setVisible(true);
        });
    }
}

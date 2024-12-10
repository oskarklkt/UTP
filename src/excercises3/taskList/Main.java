package excercises3.taskList;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        AppPanel panel = new AppPanel();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(panel.getWidth(),panel.getHeight());

        frame.setTitle("Task Status Checker");

        frame.add(panel);
        frame.setResizable(false);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(panel);
    }
}

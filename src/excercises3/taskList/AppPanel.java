package excercises3.taskList;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AppPanel extends JPanel implements Runnable {

    private final int panelWidth = 800;
    private final int panelHeight = 400;
    private final int maxTasks = 5;
    private Task selectedTask;
    private String selectedTaskName;
    JButton cancelButton, statusButton, showResultButton;
    JLabel cancelStatusLabel, taskStatusLabel, resultStatusLabel;
    private final HashMap<String, Task> tasksMap = new HashMap<>();
    private final DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private final JList<String> taskList = new JList<>(taskListModel);
    public ExecutorService taskExecutor = Executors.newFixedThreadPool(maxTasks);

    public int getPanelWidth() {
        return panelWidth;
    }

    public int getPanelHeight() {
        return panelHeight;
    }

    public AppPanel() {
        this.setLayout(null);
        this.setSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLUE);

        initializeDummyTasks();
        setupUIComponents();
    }

    public void setupUIComponents() {

        Font font = new Font("Arial", Font.PLAIN, 20);

        taskList.setPreferredSize(new Dimension(100, 240));
        taskList.setFont(font);
        taskList.setBackground(Color.GRAY);
        JScrollPane taskScrollPane = new JScrollPane(taskList);
        taskScrollPane.setBounds(50, 50, 150, 250);

        this.add(taskScrollPane);

        cancelButton = new JButton("CANCEL");
        statusButton = new JButton("STATUS");
        showResultButton = new JButton("SHOW RESULT");

        cancelButton.setVisible(true);
        statusButton.setVisible(true);
        showResultButton.setVisible(true);

        cancelButton.setBounds(225, 50, 150, 50);
        statusButton.setBounds(225, 150, 150, 50);
        showResultButton.setBounds(225, 250, 150, 50);

        this.add(cancelButton);
        this.add(statusButton);
        this.add(showResultButton);

        cancelStatusLabel = new JLabel();
        taskStatusLabel = new JLabel();
        resultStatusLabel = new JLabel();

        cancelStatusLabel.setFont(font);
        cancelStatusLabel.setForeground(Color.RED);
        taskStatusLabel.setFont(font);
        taskStatusLabel.setForeground(Color.WHITE);
        resultStatusLabel.setFont(font);
        resultStatusLabel.setForeground(Color.WHITE);

        cancelStatusLabel.setBounds(400, 50, 350, 50);
        taskStatusLabel.setBounds(400, 150, 350, 50);
        resultStatusLabel.setBounds(400, 250, 350, 50);

        this.add(cancelStatusLabel);
        this.add(taskStatusLabel);
        this.add(resultStatusLabel);
    }

    public void initializeDummyTasks() {
        for (int i = 0; i < maxTasks; i++) {
            Task newTask = new Task(taskExecutor);
            String taskKey = "Task " + i;
            newTask.runTask();
            tasksMap.put(taskKey, newTask);
        }
        tasksMap.keySet().forEach(taskListModel::addElement);
    }

    public void setupTaskListListener() {
        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedTaskName = taskList.getSelectedValue();
                selectedTask = tasksMap.get(selectedTaskName);
            }
        });
    }

    public void setupButtonListeners() {
        cancelButton.addActionListener(e -> {
            if (selectedTask != null) {
                if (!selectedTask.getFutureTask().isCancelled() && !selectedTask.getFutureTask().isDone()) {
                    cancelStatusLabel.setText("CANCELLED " + selectedTaskName);
                    selectedTask.getFutureTask().cancel(true);
                } else if (selectedTask.getFutureTask().isCancelled()) {
                    cancelStatusLabel.setText("CANCELLED " + selectedTaskName + " ALREADY");
                } else {
                    cancelStatusLabel.setText(selectedTaskName + " IS DONE!");
                }
            } else {
                cancelStatusLabel.setText("SELECT A TASK!");
            }
        });

        statusButton.addActionListener(e -> {
            if (selectedTask != null) {
                String statusMessage = "STATUS " + selectedTaskName + ": ";

                if (selectedTask.getFutureTask().isDone() && !selectedTask.getFutureTask().isCancelled()) {
                    taskStatusLabel.setText(statusMessage + "DONE!");
                } else if (selectedTask.getFutureTask().isCancelled()) {
                    taskStatusLabel.setText(statusMessage + "CANCELLED!");
                } else {
                    taskStatusLabel.setText(statusMessage + "RUNNING!");
                }
            } else {
                taskStatusLabel.setText("SELECT A TASK!");
            }
        });

        showResultButton.addActionListener(e -> {
            if (selectedTask != null) {
                if (selectedTask.getFutureTask().isDone() && !selectedTask.getFutureTask().isCancelled()) {
                    try {
                        resultStatusLabel.setText("RESULT " + selectedTaskName + ": " + selectedTask.getFutureTask().get());
                    } catch (InterruptedException | ExecutionException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (selectedTask.getFutureTask().isCancelled()) {
                    resultStatusLabel.setText("RESULT " + selectedTaskName + ": TASK CANCELLED");
                } else {
                    resultStatusLabel.setText("RESULT " + selectedTaskName + ": STILL RUNNING!");
                }
            } else {
                resultStatusLabel.setText("SELECT A TASK!");
            }
        });
    }

    @Override
    public void run() {
        setupTaskListListener();
        setupButtonListeners();
    }
}



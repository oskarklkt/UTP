package excercises4.interdb;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Database {

    private final String url;
    private final TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS offers (" +
                "id SERIAL PRIMARY KEY, " +
                "locale TEXT, " +
                "country TEXT, " +
                "dateFrom DATE, " +
                "dateTo DATE, " +
                "destination TEXT, " +
                "price NUMERIC, " +
                "currency TEXT)";
        String countQuery = "SELECT COUNT(*) FROM offers";
        String insertSQL = "INSERT INTO offers (locale, country, dateFrom, dateTo, destination, price, currency) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(url, "postgres", "postgres");
                Statement statement = connection.createStatement()
        ) {

            Class.forName("org.postgresql.Driver");

            statement.execute(createTableSQL);

            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count < travelData.getOffersDescriptionsList().size()) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                        for (Offer offer : travelData.getOffersDescriptionsList()
                                .subList(count, travelData.getOffersDescriptionsList().size())) {
                            preparedStatement.setString(1, String.valueOf(offer.getLocale()));
                            preparedStatement.setString(2, offer.getCountry());
                            preparedStatement.setDate(3,
                                    java.sql.Date.valueOf(String.valueOf(offer.getDateFrom())));
                            preparedStatement.setDate(4,
                                    java.sql.Date.valueOf(String.valueOf(offer.getDateTo())));
                            preparedStatement.setString(5, offer.getDestination());
                            preparedStatement.setBigDecimal(6,
                                    new BigDecimal(String.valueOf(offer.getPrice())));
                            preparedStatement.setString(7, String.valueOf(offer.getCurrency()));
                            preparedStatement.executeUpdate();
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showGui() {

        JFrame localeFrame = new JFrame("Select Locale");
        localeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        localeFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Select your locale:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        String[] locales = {"pl_PL", "en_GB", "de_DE"};
        JComboBox<String> localeSelector = new JComboBox<>(locales);
        JButton proceedButton = new JButton("Proceed");

        proceedButton.addActionListener(e -> {
            String selectedLocale = (String) localeSelector.getSelectedItem();
            String[] localeParts = Objects.requireNonNull(selectedLocale).split("_");
            Locale locale = new Locale(localeParts[0], localeParts[1]);
            localeFrame.dispose();
            showOffersTable(locale);
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(localeSelector);
        panel.add(proceedButton);

        localeFrame.add(label, BorderLayout.NORTH);
        localeFrame.add(panel, BorderLayout.CENTER);

        localeFrame.setSize(400, 200);
        localeFrame.setVisible(true);
    }

    private void showOffersTable(Locale locale) {
        JFrame tableFrame = new JFrame("Travel Offers");
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setLayout(new BorderLayout());

        ResourceBundle bundle = ResourceBundle.getBundle("excercises4.interdb.words", locale);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                bundle.getString("COUNTRY"),
                bundle.getString("DATE_FROM"),
                bundle.getString("DATE_TO"),
                bundle.getString("DESTINATION"),
                bundle.getString("PRICE"),
                bundle.getString("CURRENCY")
        });
        table.setModel(tableModel);

        loadTableData(tableModel, locale, bundle);

        tableFrame.add(new JScrollPane(table), BorderLayout.CENTER);

        tableFrame.setSize(800, 600);
        tableFrame.setVisible(true);
    }

    private void loadTableData(DefaultTableModel tableModel, Locale locale, ResourceBundle bundle) {
        tableModel.setRowCount(0);

        List<String> offers = travelData.getOffersDescriptionsList(
                locale.toString(),
                "yyyy-MM-dd"
        );

        for (String offer : offers) {
            String[] parts = offer.split("\t");
            tableModel.addRow(parts);
        }
    }
}
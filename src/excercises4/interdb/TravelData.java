package excercises4.interdb;

import java.io.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TravelData {

    private final List<Offer> offersDescriptionsList;
    private final File dataDirectory;
    private final List<File> dataFiles;

    public TravelData(File dataDirectory) {
        this.dataDirectory = dataDirectory;
        this.offersDescriptionsList = new ArrayList<>();
        this.dataFiles = getFiles();
        populateOffers();
    }

    private List<File> getFiles() {
        return Arrays.asList(Objects.requireNonNull(dataDirectory.listFiles()));
    }

    private void populateOffers() {
        dataFiles.stream()
                .map(extractOffersFromFile())
                .forEach(offersDescriptionsList::addAll);
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat) {
        return offersDescriptionsList.stream()
                .map(parseOffer(loc, dateFormat))
                .map(OfferDto::toString)
                .collect(Collectors.toList()); // Replace .toList() with Collectors.toList()
    }

    private Function<File, List<Offer>> extractOffersFromFile() {
        return file -> {
            List<Offer> result = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\t");
                    Offer offer = new Offer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                    result.add(offer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return result;
        };
    }

    private Function<Offer, OfferDto> parseOffer(String loc, String dateFormat) {
        return offer -> {
            Locale locale = parseLocale(loc);
            ResourceBundle bundle = ResourceBundle.getBundle("excercises4.interdb.words", locale);
            ResourceBundle currentLanguageBundle = ResourceBundle.getBundle("excercises4.interdb.words", offer.getLocale());

            String country = getTranslation(currentLanguageBundle, bundle, offer.getCountry());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat, locale);
            String dateFrom = dtf.format(offer.getDateFrom().toLocalDate());
            String dateTo = dtf.format(offer.getDateTo().toLocalDate());

            String destination = getTranslation(currentLanguageBundle, bundle, offer.getDestination());

            NumberFormat nf = NumberFormat.getInstance(locale);
            nf.setCurrency(offer.getCurrency());

            String price = nf.format(offer.getPrice());

            return new OfferDto(country, dateFrom, dateTo, destination, price, offer.getCurrency());
        };
    }

    private Locale parseLocale(String loc) {
        String[] parts = loc.split("_");
        if (parts.length == 2) {
            return new Locale(parts[0], parts[1]);
        } else if (parts.length == 1) {
            return new Locale(parts[0]);
        } else {
            throw new IllegalArgumentException("Invalid locale format: " + loc);
        }
    }

    private String getTranslation(ResourceBundle currentLanguageBundle, ResourceBundle bundle, String word) {
        String key = "";
        Enumeration<String> keys = currentLanguageBundle.getKeys();
        while (keys.hasMoreElements()) {
            String currentKey = keys.nextElement();
            if (currentLanguageBundle.getString(currentKey).equals(word)) {
                key = currentKey;
                break;
            }
        }
        if (key.isEmpty()) {
            throw new RuntimeException("No translation for word: " + word + " in language: " + bundle.getLocale());
        }
        return bundle.getString(key);
    }

    public List<Offer> getOffersDescriptionsList() {
        return offersDescriptionsList;
    }
}

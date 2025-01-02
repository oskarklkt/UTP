package excercises4.interdb;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class Offer {
    private final Locale locale;
    private final String country;
    private final Date dateFrom;
    private final Date dateTo;
    private final String destination;
    private final BigDecimal price;
    private final Currency currency;

    public Offer(String locale, String country, String dateFrom, String dateTo, String destination,
                 String price, String currency) {
        this.locale = parseLocale(locale);
        this.country = country;
        this.dateFrom = Date.valueOf(dateFrom);
        this.dateTo = Date.valueOf(dateTo);
        this.destination = destination;
        this.price = parsePrice(price, this.locale);
        this.currency = Currency.getInstance(currency);
    }

    private Locale parseLocale(String localeStr) {
        String[] parts = localeStr.split("_");
        if (parts.length == 2) {
            return new Locale(parts[0], parts[1]);
        } else if (parts.length == 1) {
            return new Locale(parts[0]);
        } else {
            throw new IllegalArgumentException("Invalid locale format: " + localeStr);
        }
    }

    private BigDecimal parsePrice(String priceStr, Locale locale) {
        try {
            NumberFormat format = NumberFormat.getInstance(locale);
            return new BigDecimal(format.parse(priceStr).toString());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing price: " + priceStr + " for locale: " + locale, e);
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public String getCountry() {
        return country;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }
}

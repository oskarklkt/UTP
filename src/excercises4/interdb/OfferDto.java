package excercises4.interdb;

import java.util.Currency;

public class OfferDto {
    private final String country;
    private final String dateFrom;
    private final String dateTo;
    private final String destination;
    private final String price;
    private final Currency currency;

    public OfferDto(String country, String dateFrom, String dateTo, String destination, String price,
                    Currency currency) {
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.destination = destination;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return country + "\t" + dateFrom + "\t" + dateTo + "\t" + destination + "\t" + price + "\t" + currency;
    }
}

/**
 * Represents a stock with symbol, price, volume, and market capitalization.
 */
public class Stock {
    private String symbol;
    private double price;
    private long volume;
    private long marketCap;

    /**
     * Constructs a new Stock instance.
     * 
     * @param symbol    The stock's trading symbol.
     * @param price     The current trading price of the stock.
     * @param volume    The total volume of stock currently traded.
     * @param marketCap The total market capitalization of the stock.
     */
    public Stock(String symbol, double price, long volume, long marketCap) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    /**
     * Returns the stock symbol.
     * 
     * @return The symbol of the stock.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the stock symbol.
     * 
     * @param symbol The new symbol of the stock.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the current price of the stock.
     * 
     * @return The price of the stock.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the stock.
     * 
     * @param price The new price of the stock.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the total volume of the stock traded.
     * 
     * @return The volume of the stock.
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Sets the volume of stock traded.
     * 
     * @param volume The new trading volume of the stock.
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Returns the market capitalization of the stock.
     * 
     * @return The market capitalization.
     */
    public long getMarketCap() {
        return marketCap;
    }

    /**
     * Sets the market capitalization of the stock.
     * 
     * @param marketCap The new market capitalization value.
     */
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * Returns a string representation of the stock, including its symbol, price,
     * volume, and market cap.
     * 
     * @return A string describing the stock.
     */
    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", marketCap=" + marketCap +
                '}';
    }
}

package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsItemCurrentPrice {

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("lowest_price")
    protected Float lowestPrice;

    @JsonProperty("highest_price")
    protected Float highestPrice;

    @JsonProperty("cumulative_price")
    protected Float cumulativePrice;


    @JsonProperty("total_items")
    protected int totalItems;

    public BitSkinsItemCurrentPrice() {
    }

    public String getMarketHashName() {
        return marketHashName;
    }

    public void setMarketHashName(String marketHashName) {
        this.marketHashName = marketHashName;
    }

    public Float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public Float getCumulativePrice() {
        return cumulativePrice;
    }

    public void setCumulativePrice(Float cumulativePrice) {
        this.cumulativePrice = cumulativePrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsItemCurrentPrice that = (BitSkinsItemCurrentPrice) o;

        if (totalItems != that.totalItems) return false;
        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (lowestPrice != null ? !lowestPrice.equals(that.lowestPrice) : that.lowestPrice != null) return false;
        if (highestPrice != null ? !highestPrice.equals(that.highestPrice) : that.highestPrice != null) return false;
        return cumulativePrice != null ? cumulativePrice.equals(that.cumulativePrice) : that.cumulativePrice == null;

    }

    @Override
    public int hashCode() {
        int result = marketHashName != null ? marketHashName.hashCode() : 0;
        result = 31 * result + (lowestPrice != null ? lowestPrice.hashCode() : 0);
        result = 31 * result + (highestPrice != null ? highestPrice.hashCode() : 0);
        result = 31 * result + (cumulativePrice != null ? cumulativePrice.hashCode() : 0);
        result = 31 * result + totalItems;
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsItemCurrentPrice{" +
                "marketHashName='" + marketHashName + '\'' +
                ", lowestPrice=" + lowestPrice +
                ", highestPrice=" + highestPrice +
                ", cumulativePrice=" + cumulativePrice +
                ", totalItems=" + totalItems +
                '}';
    }
}

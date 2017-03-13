package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsItemSellEvent {

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("price")
    protected Float price;

    @JsonProperty("sold_at")
    protected Long soldAt;

    @JsonProperty("wear_value")
    protected Float wearWalue;

    public BitSkinsItemSellEvent() {
    }

    public Long getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(Long soldAt) {
        this.soldAt = soldAt;
    }

    public Float getWearWalue() {
        return wearWalue;
    }

    public void setWearWalue(Float wearWalue) {
        this.wearWalue = wearWalue;
    }

    public String getMarketHashName() {
        return marketHashName;
    }

    public void setMarketHashName(String marketHashName) {
        this.marketHashName = marketHashName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsItemSellEvent that = (BitSkinsItemSellEvent) o;

        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (soldAt != null ? !soldAt.equals(that.soldAt) : that.soldAt != null) return false;
        return wearWalue != null ? wearWalue.equals(that.wearWalue) : that.wearWalue == null;

    }

    @Override
    public int hashCode() {
        int result = marketHashName != null ? marketHashName.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (soldAt != null ? soldAt.hashCode() : 0);
        result = 31 * result + (wearWalue != null ? wearWalue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsItemSellEvent{" +
                "marketHashName='" + marketHashName + '\'' +
                ", price=" + price +
                ", soldAt=" + soldAt +
                ", wearWalue=" + wearWalue +
                '}';
    }
}

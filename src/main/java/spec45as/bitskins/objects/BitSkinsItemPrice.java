package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class BitSkinsItemPrice {

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("price")
    protected Float price;

    @JsonProperty("created_at")
    protected Long createdAt;

    public BitSkinsItemPrice() {
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

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsItemPrice that = (BitSkinsItemPrice) o;

        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;

    }

    @Override
    public int hashCode() {
        int result = marketHashName != null ? marketHashName.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsItemPrice{" +
                "marketHashName='" + marketHashName + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}

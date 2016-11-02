package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsBuyOrderInfo {
    final public static String ORDER_STATE_LISTED = "LISTED";
    final public static String ORDER_STATE_SETTLED = "SETTLED";
    final public static String ORDER_STATE_CANCELLED_BY_USER = "CANCELLED_BY_USER";
    final public static String ORDER_STATE_CANCELLED_BY_SYSTEM = "CANCELLED_BY_SYSTEM";

    @JsonProperty("buy_order_id")
    protected Integer buyOrderId;
    //"settled_with_item" : null

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("price")
    protected Float price;

    @JsonProperty("suggested_price")
    protected Float suggestedPrice;

    @JsonProperty("state")
    protected String state;

    @JsonProperty("created_at")
    protected Long createdAt;

    @JsonProperty("updated_at")
    protected Long updatedAt;

    public BitSkinsBuyOrderInfo() {
    }

    public Integer getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(Integer buyOrderId) {
        this.buyOrderId = buyOrderId;
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

    public Float getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Float suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BitSkinsBuyOrderInfo{" +
                "buyOrderId=" + buyOrderId +
                ", marketHashName='" + marketHashName + '\'' +
                ", price=" + price +
                ", suggestedPrice=" + suggestedPrice +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

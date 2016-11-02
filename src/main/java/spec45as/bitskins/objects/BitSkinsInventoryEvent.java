package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by spec45as on 9/29/2016.
 */
public class BitSkinsInventoryEvent {

    @JsonProperty("item_id")
    protected Long itemId;

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("price")
    protected Float price;

    @JsonProperty("last_update_at")
    protected Long lastUpdateAt;

    @JsonProperty("listed_at")
    protected Long listedAt;

    @JsonProperty("withdrawn_at")
    protected Long withdrawnAt;

    @JsonProperty("sold_at")
    protected Long soldAt;

    @JsonProperty("listed_by_me")
    protected Boolean listedByMe;

    @JsonProperty("on_hold")
    protected Boolean onHold;

    @JsonProperty("on_sale")
    protected Boolean onSale;

    @JsonProperty("image")
    protected String imageUrl;

    public BitSkinsInventoryEvent() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Long getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Long lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public Long getListedAt() {
        return listedAt;
    }

    public void setListedAt(Long listedAt) {
        this.listedAt = listedAt;
    }

    public Long getWithdrawnAt() {
        return withdrawnAt;
    }

    public void setWithdrawnAt(Long withdrawnAt) {
        this.withdrawnAt = withdrawnAt;
    }

    public Long getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(Long soldAt) {
        this.soldAt = soldAt;
    }

    public Boolean getListedByMe() {
        return listedByMe;
    }

    public void setListedByMe(Boolean listedByMe) {
        this.listedByMe = listedByMe;
    }

    public Boolean getOnHold() {
        return onHold;
    }

    public void setOnHold(Boolean onHold) {
        this.onHold = onHold;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsInventoryEvent that = (BitSkinsInventoryEvent) o;

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (lastUpdateAt != null ? !lastUpdateAt.equals(that.lastUpdateAt) : that.lastUpdateAt != null) return false;
        if (listedAt != null ? !listedAt.equals(that.listedAt) : that.listedAt != null) return false;
        if (withdrawnAt != null ? !withdrawnAt.equals(that.withdrawnAt) : that.withdrawnAt != null) return false;
        if (soldAt != null ? !soldAt.equals(that.soldAt) : that.soldAt != null) return false;
        if (listedByMe != null ? !listedByMe.equals(that.listedByMe) : that.listedByMe != null) return false;
        if (onHold != null ? !onHold.equals(that.onHold) : that.onHold != null) return false;
        if (onSale != null ? !onSale.equals(that.onSale) : that.onSale != null) return false;
        return imageUrl != null ? imageUrl.equals(that.imageUrl) : that.imageUrl == null;

    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (marketHashName != null ? marketHashName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (lastUpdateAt != null ? lastUpdateAt.hashCode() : 0);
        result = 31 * result + (listedAt != null ? listedAt.hashCode() : 0);
        result = 31 * result + (withdrawnAt != null ? withdrawnAt.hashCode() : 0);
        result = 31 * result + (soldAt != null ? soldAt.hashCode() : 0);
        result = 31 * result + (listedByMe != null ? listedByMe.hashCode() : 0);
        result = 31 * result + (onHold != null ? onHold.hashCode() : 0);
        result = 31 * result + (onSale != null ? onSale.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsInventoryEvent{" +
                "itemId=" + itemId +
                ", marketHashName='" + marketHashName + '\'' +
                ", price=" + price +
                ", lastUpdateAt=" + lastUpdateAt +
                ", listedAt=" + listedAt +
                ", withdrawnAt=" + withdrawnAt +
                ", soldAt=" + soldAt +
                ", listedByMe=" + listedByMe +
                ", onHold=" + onHold +
                ", onSale=" + onSale +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

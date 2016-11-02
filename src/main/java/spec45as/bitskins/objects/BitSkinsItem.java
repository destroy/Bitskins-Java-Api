package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BitSkinsItem {

    @JsonProperty("item_id")
    protected Long itemId;

    @JsonProperty("class_id")
    protected Long classId;

    @JsonProperty("instance_id")
    protected Long instanceId;

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    @JsonProperty("item_type")
    protected String itemType;

    @JsonProperty("image")
    protected String image;

    @JsonProperty("inspectable")
    protected Boolean inspectable;

    @JsonProperty("inspect_link")
    protected String inspectLink;

    @JsonProperty("price")
    protected Float price;

    @JsonProperty("suggested_price")
    protected Float suggestedPrice;

    @JsonProperty("is_featured")
    protected Boolean featured;

    @JsonProperty("wear_value")
    protected Float wearValue;

    @JsonProperty("float_value")
    protected Float floatValue;

    @JsonProperty("attempting_to_send_offer")
    protected Boolean tradeOfferIsActive;

    @JsonProperty("is_mine")
    protected Boolean itemIsMine;

    @JsonProperty("trade_token")
    protected String tradeToken;

    List<BitSkinsSticker> stickers = new ArrayList<>();

    public BitSkinsItem() {
    }

    public Boolean getTradeOfferIsActive() {
        return tradeOfferIsActive;
    }

    public void setTradeOfferIsActive(Boolean tradeOfferIsActive) {
        this.tradeOfferIsActive = tradeOfferIsActive;
    }

    public String getTradeToken() {
        return tradeToken;
    }

    public void setTradeToken(String tradeToken) {
        this.tradeToken = tradeToken;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public String getMarketHashName() {
        return marketHashName;
    }

    public void setMarketHashName(String marketHashName) {
        this.marketHashName = marketHashName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getInspectable() {
        return inspectable;
    }

    public void setInspectable(Boolean inspectable) {
        this.inspectable = inspectable;
    }

    public String getInspectLink() {
        return inspectLink;
    }

    public void setInspectLink(String inspectLink) {
        this.inspectLink = inspectLink;
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

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Float getWearValue() {
        return wearValue;
    }

    public void setWearValue(Float wearValue) {
        this.wearValue = wearValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public Boolean getItemIsMine() {
        return itemIsMine;
    }

    public void setItemIsMine(Boolean itemIsMine) {
        this.itemIsMine = itemIsMine;
    }

    public List<BitSkinsSticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<BitSkinsSticker> stickers) {
        this.stickers = stickers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsItem that = (BitSkinsItem) o;

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (itemType != null ? !itemType.equals(that.itemType) : that.itemType != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (inspectable != null ? !inspectable.equals(that.inspectable) : that.inspectable != null) return false;
        if (inspectLink != null ? !inspectLink.equals(that.inspectLink) : that.inspectLink != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (suggestedPrice != null ? !suggestedPrice.equals(that.suggestedPrice) : that.suggestedPrice != null)
            return false;
        if (featured != null ? !featured.equals(that.featured) : that.featured != null) return false;
        if (wearValue != null ? !wearValue.equals(that.wearValue) : that.wearValue != null) return false;
        if (floatValue != null ? !floatValue.equals(that.floatValue) : that.floatValue != null) return false;
        if (tradeOfferIsActive != null ? !tradeOfferIsActive.equals(that.tradeOfferIsActive) : that.tradeOfferIsActive != null)
            return false;
        if (itemIsMine != null ? !itemIsMine.equals(that.itemIsMine) : that.itemIsMine != null) return false;
        if (tradeToken != null ? !tradeToken.equals(that.tradeToken) : that.tradeToken != null) return false;
        return stickers != null ? stickers.equals(that.stickers) : that.stickers == null;

    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (instanceId != null ? instanceId.hashCode() : 0);
        result = 31 * result + (marketHashName != null ? marketHashName.hashCode() : 0);
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (inspectable != null ? inspectable.hashCode() : 0);
        result = 31 * result + (inspectLink != null ? inspectLink.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (suggestedPrice != null ? suggestedPrice.hashCode() : 0);
        result = 31 * result + (featured != null ? featured.hashCode() : 0);
        result = 31 * result + (wearValue != null ? wearValue.hashCode() : 0);
        result = 31 * result + (floatValue != null ? floatValue.hashCode() : 0);
        result = 31 * result + (tradeOfferIsActive != null ? tradeOfferIsActive.hashCode() : 0);
        result = 31 * result + (itemIsMine != null ? itemIsMine.hashCode() : 0);
        result = 31 * result + (tradeToken != null ? tradeToken.hashCode() : 0);
        result = 31 * result + (stickers != null ? stickers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsItem{" +
                "itemId=" + itemId +
                ", classId=" + classId +
                ", instanceId=" + instanceId +
                ", marketHashName='" + marketHashName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", image='" + image + '\'' +
                ", inspectable=" + inspectable +
                ", inspectLink='" + inspectLink + '\'' +
                ", price=" + price +
                ", suggestedPrice=" + suggestedPrice +
                ", featured=" + featured +
                ", wearValue=" + wearValue +
                ", floatValue=" + floatValue +
                ", tradeOfferIsActive=" + tradeOfferIsActive +
                ", itemIsMine=" + itemIsMine +
                ", tradeToken='" + tradeToken + '\'' +
                ", stickers=" + stickers +
                '}';
    }
}

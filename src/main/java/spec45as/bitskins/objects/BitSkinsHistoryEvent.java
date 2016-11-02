package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by spec45as on 9/29/2016.
 */
public class BitSkinsHistoryEvent {

    public enum OPERATION_TYPE {
        BUY,
        SELL
    }

    @JsonProperty("item_id")
    protected Long itemId;

    @JsonProperty("class_id")
    protected Long classId;

    @JsonProperty("instance_id")
    protected Long instanceId;

    @JsonProperty("market_hash_name")
    protected String marketHashName;

    protected Float price;

    protected OPERATION_TYPE operationType;

    @JsonProperty("time")
    protected Long time;

    @JsonProperty("withdrawn")
    protected Boolean withdrawn;

    public BitSkinsHistoryEvent() {
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


    @JsonProperty("sale_price")
    public void setSellPrice(Float price) {
        operationType = OPERATION_TYPE.SELL;
        this.price = price;
    }

    @JsonProperty("buy_price")
    public void setBuyPrice(Float price) {
        operationType = OPERATION_TYPE.BUY;
        this.price = price;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OPERATION_TYPE getOperationType() {
        return operationType;
    }

    public void setOperationType(OPERATION_TYPE operationType) {
        this.operationType = operationType;
    }

    public Boolean getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(Boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsHistoryEvent that = (BitSkinsHistoryEvent) o;

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (instanceId != null ? !instanceId.equals(that.instanceId) : that.instanceId != null) return false;
        if (marketHashName != null ? !marketHashName.equals(that.marketHashName) : that.marketHashName != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (operationType != that.operationType) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return withdrawn != null ? withdrawn.equals(that.withdrawn) : that.withdrawn == null;

    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (instanceId != null ? instanceId.hashCode() : 0);
        result = 31 * result + (marketHashName != null ? marketHashName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (withdrawn != null ? withdrawn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsHistoryEvent{" +
                "itemId=" + itemId +
                ", classId=" + classId +
                ", instanceId=" + instanceId +
                ", marketHashName='" + marketHashName + '\'' +
                ", price=" + price +
                ", operationType=" + operationType +
                ", time=" + time +
                ", withdrawn=" + withdrawn +
                '}';
    }
}

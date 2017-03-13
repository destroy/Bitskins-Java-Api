package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class BitSkinsTradeDetail {

    protected String tradeToken;
    protected String tradeId;

    @JsonProperty("items_sent")
    protected ArrayList<BitSkinsItem> sentByBsItems;

    @JsonProperty("items_retrieved")
    protected ArrayList<BitSkinsItem> retrievedByBsItems;

    @JsonProperty("created_at")
    protected Long createdAt;

    @JsonProperty("bot_uid")
    protected String botUid;

    public BitSkinsTradeDetail() {
    }

    public String getTradeToken() {
        return tradeToken;
    }

    public void setTradeToken(String tradeToken) {
        this.tradeToken = tradeToken;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public ArrayList<BitSkinsItem> getSentByBsItems() {
        return sentByBsItems;
    }

    public void setSentByBsItems(ArrayList<BitSkinsItem> sentByBsItems) {
        this.sentByBsItems = sentByBsItems;
    }

    public ArrayList<BitSkinsItem> getRetrievedByBsItems() {
        return retrievedByBsItems;
    }

    public void setRetrievedByBsItems(ArrayList<BitSkinsItem> retrievedByBsItems) {
        this.retrievedByBsItems = retrievedByBsItems;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getBotUid() {
        return botUid;
    }

    public void setBotUid(String botUid) {
        this.botUid = botUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsTradeDetail that = (BitSkinsTradeDetail) o;

        if (tradeToken != null ? !tradeToken.equals(that.tradeToken) : that.tradeToken != null) return false;
        if (tradeId != null ? !tradeId.equals(that.tradeId) : that.tradeId != null) return false;
        if (sentByBsItems != null ? !sentByBsItems.equals(that.sentByBsItems) : that.sentByBsItems != null)
            return false;
        if (retrievedByBsItems != null ? !retrievedByBsItems.equals(that.retrievedByBsItems) : that.retrievedByBsItems != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return botUid != null ? botUid.equals(that.botUid) : that.botUid == null;

    }

    @Override
    public int hashCode() {
        int result = tradeToken != null ? tradeToken.hashCode() : 0;
        result = 31 * result + (tradeId != null ? tradeId.hashCode() : 0);
        result = 31 * result + (sentByBsItems != null ? sentByBsItems.hashCode() : 0);
        result = 31 * result + (retrievedByBsItems != null ? retrievedByBsItems.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (botUid != null ? botUid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsTradeDetail{" +
                "tradeToken='" + tradeToken + '\'' +
                ", tradeId='" + tradeId + '\'' +
                ", sentByBsItems=" + sentByBsItems +
                ", retrievedByBsItems=" + retrievedByBsItems +
                ", createdAt=" + createdAt +
                ", botUid='" + botUid + '\'' +
                '}';
    }
}

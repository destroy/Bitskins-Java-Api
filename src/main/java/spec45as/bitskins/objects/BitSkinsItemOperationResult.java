package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by spec45as on 9/29/2016.
 */
public class BitSkinsItemOperationResult {

    @JsonProperty("items")
    private List<BitSkinsItem> bitSkinsItemList;

    @JsonProperty("trade_tokens")
    private List<String> tradeTokens;

    public BitSkinsItemOperationResult() {
    }

    public List<BitSkinsItem> getBitSkinsItemList() {
        return bitSkinsItemList;
    }

    public void setBitSkinsItemList(List<BitSkinsItem> bitSkinsItemList) {
        this.bitSkinsItemList = bitSkinsItemList;
    }

    public List<String> getTradeTokens() {
        return tradeTokens;
    }

    public void setTradeTokens(List<String> tradeTokens) {
        this.tradeTokens = tradeTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsItemOperationResult that = (BitSkinsItemOperationResult) o;

        if (bitSkinsItemList != null ? !bitSkinsItemList.equals(that.bitSkinsItemList) : that.bitSkinsItemList != null)
            return false;
        return tradeTokens != null ? tradeTokens.equals(that.tradeTokens) : that.tradeTokens == null;

    }

    @Override
    public int hashCode() {
        int result = bitSkinsItemList != null ? bitSkinsItemList.hashCode() : 0;
        result = 31 * result + (tradeTokens != null ? tradeTokens.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsItemOperationResult{" +
                "bitSkinsItemList=" + bitSkinsItemList +
                ", tradeTokens=" + tradeTokens +
                '}';
    }
}

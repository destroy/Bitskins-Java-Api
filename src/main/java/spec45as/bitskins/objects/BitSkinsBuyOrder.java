package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsBuyOrder {

    protected String itemName;

    @JsonProperty("number_of_buy_orders")
    protected Integer numberOfOrders;

    @JsonProperty("max_price")
    protected Float maxPrice;

    @JsonProperty("min_price")
    protected Float minPrice;

    @JsonProperty("my_buy_orders")
    protected Integer myOrders;

    public BitSkinsBuyOrder() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(Integer myOrders) {
        this.myOrders = myOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsBuyOrder that = (BitSkinsBuyOrder) o;

        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
        if (numberOfOrders != null ? !numberOfOrders.equals(that.numberOfOrders) : that.numberOfOrders != null)
            return false;
        if (maxPrice != null ? !maxPrice.equals(that.maxPrice) : that.maxPrice != null) return false;
        if (minPrice != null ? !minPrice.equals(that.minPrice) : that.minPrice != null) return false;
        return myOrders != null ? myOrders.equals(that.myOrders) : that.myOrders == null;

    }

    @Override
    public int hashCode() {
        int result = itemName != null ? itemName.hashCode() : 0;
        result = 31 * result + (numberOfOrders != null ? numberOfOrders.hashCode() : 0);
        result = 31 * result + (maxPrice != null ? maxPrice.hashCode() : 0);
        result = 31 * result + (minPrice != null ? minPrice.hashCode() : 0);
        result = 31 * result + (myOrders != null ? myOrders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsBuyOrder{" +
                "itemName='" + itemName + '\'' +
                ", numberOfOrders=" + numberOfOrders +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", myOrders=" + myOrders +
                '}';
    }
}

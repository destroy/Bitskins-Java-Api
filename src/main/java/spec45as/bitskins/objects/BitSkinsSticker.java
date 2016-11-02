package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsSticker {

    @JsonProperty("name")
    protected String name;

    @JsonProperty("url")
    protected String url;

    @JsonProperty("wear_value")
    protected Float wearValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getWearValue() {
        return wearValue;
    }

    public void setWearValue(Float wearValue) {
        this.wearValue = wearValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsSticker that = (BitSkinsSticker) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return wearValue != null ? wearValue.equals(that.wearValue) : that.wearValue == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (wearValue != null ? wearValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsSticker{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", wearValue=" + wearValue +
                '}';
    }
}

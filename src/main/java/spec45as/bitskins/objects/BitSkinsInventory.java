package spec45as.bitskins.objects;

import java.util.HashMap;

/**
 * Created by spec45as on 9/29/2016.
 */
public class BitSkinsInventory {
    private HashMap<Long, BitSkinsItem> bitSkinsInventory = new HashMap<>();
    private HashMap<Long, BitSkinsItem> steamInventory = new HashMap<>();
    private HashMap<Long, BitSkinsItem> pendingWithdrawalFromBitskins = new HashMap<>();

    public HashMap<Long, BitSkinsItem> getBitSkinsInventory() {
        return bitSkinsInventory;
    }

    public void setBitSkinsInventory(HashMap<Long, BitSkinsItem> bitSkinsInventory) {
        this.bitSkinsInventory = bitSkinsInventory;
    }

    public HashMap<Long, BitSkinsItem> getSteamInventory() {
        return steamInventory;
    }

    public void setSteamInventory(HashMap<Long, BitSkinsItem> steamInventory) {
        this.steamInventory = steamInventory;
    }

    public HashMap<Long, BitSkinsItem> getPendingWithdrawalFromBitskins() {
        return pendingWithdrawalFromBitskins;
    }

    public void setPendingWithdrawalFromBitskins(HashMap<Long, BitSkinsItem> pendingWithdrawalFromBitskins) {
        this.pendingWithdrawalFromBitskins = pendingWithdrawalFromBitskins;
    }

    @Override
    public String toString() {
        return "BitSkinsInventory{" +
                "bitSkinsInventory=" + bitSkinsInventory +
                ", steamInventory=" + steamInventory +
                ", pendingWithdrawalFromBitskins=" + pendingWithdrawalFromBitskins +
                '}';
    }
}

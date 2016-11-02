package spec45as.bitskins.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitSkinsAccountBalance {
    @JsonProperty("available_balance")
    protected float availableBalance;

    @JsonProperty("pending_withdrawals")
    protected float pendingWithdrawals;

    @JsonProperty("withdrawable_balance")
    protected float withdrawableBalance;

    public BitSkinsAccountBalance() {
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    public float getPendingWithdrawals() {
        return pendingWithdrawals;
    }

    public void setPendingWithdrawals(float pendingWithdrawals) {
        this.pendingWithdrawals = pendingWithdrawals;
    }

    public float getWithdrawableBalance() {
        return withdrawableBalance;
    }

    public void setWithdrawableBalance(float withdrawableBalance) {
        this.withdrawableBalance = withdrawableBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitSkinsAccountBalance that = (BitSkinsAccountBalance) o;

        if (Float.compare(that.availableBalance, availableBalance) != 0) return false;
        if (Float.compare(that.pendingWithdrawals, pendingWithdrawals) != 0) return false;
        return Float.compare(that.withdrawableBalance, withdrawableBalance) == 0;

    }

    @Override
    public int hashCode() {
        int result = (availableBalance != +0.0f ? Float.floatToIntBits(availableBalance) : 0);
        result = 31 * result + (pendingWithdrawals != +0.0f ? Float.floatToIntBits(pendingWithdrawals) : 0);
        result = 31 * result + (withdrawableBalance != +0.0f ? Float.floatToIntBits(withdrawableBalance) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BitSkinsAccountBalance{" +
                "availableBalance=" + availableBalance +
                ", pendingWithdrawals=" + pendingWithdrawals +
                ", withdrawableBalance=" + withdrawableBalance +
                '}';
    }
}

package es.rachelcarmena.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    protected final BigDecimal value;

    public Amount(long amount) {
        BigDecimal value = new BigDecimal(amount);
        this.value = value.setScale(SCALE);
    }

    public Amount(BigDecimal amount) {
        this.value = amount;
    }

    public Amount(String amount) {
        this.value = new BigDecimal(amount);
    }

    public Amount subtract(Amount amount) {
        return new Amount(value.subtract(amount.value));
    }

    public Amount min(Amount amount) {
        return new Amount(value.min(amount.value));
    }

    public Amount perMonth() {
        BigDecimal perMonth = value.divide(BigDecimal.valueOf(12), SCALE , ROUNDING_MODE);
        return new Amount(perMonth);
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.value));
    }

    public boolean greaterThanZero() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Amount calculatePercentage(int percentage) {
        BigDecimal amount = value.multiply(BigDecimal.valueOf(percentage));
        amount = amount.divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);
        return new Amount(amount);
    }

    public Amount divide(Amount amount) {
        BigDecimal result = value.divide(amount.value, SCALE , ROUNDING_MODE);
        return new Amount(result);
    }

    public static Amount valueOf(long amount) {
        BigDecimal result = BigDecimal.valueOf(amount).setScale(SCALE);
        return new Amount(result);
    }

    public String toPrintedAmount() {
        return value.setScale(SCALE).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Amount)
            return value.equals(((Amount) obj).value);
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
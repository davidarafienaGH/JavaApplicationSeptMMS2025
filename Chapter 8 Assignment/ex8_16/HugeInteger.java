import java.util.Arrays;

public class HugeInteger {
    private final int[] digits = new int[40];

    public HugeInteger() {}

    public HugeInteger(String val) {
        parse(val);
    }

    public void parse(String val) {
        Arrays.fill(digits, 0);
        int length = val.length();
        for (int i = 0; i < length; i++) {
            digits[40 - length + i] = Character.getNumericValue(val.charAt(i));
        }
    }

    public HugeInteger add(HugeInteger val) {
        HugeInteger result = new HugeInteger();
        int carry = 0;
        for (int i = 39; i >= 0; i--) {
            int sum = this.digits[i] + val.digits[i] + carry;
            result.digits[i] = sum % 10;
            carry = sum / 10;
        }
        return result;
    }

    public boolean isEqualTo(HugeInteger val) {
        return Arrays.equals(this.digits, val.digits);
    }

    public boolean isGreaterThan(HugeInteger val) {
        for (int i = 0; i < 40; i++) {
            if (this.digits[i] > val.digits[i]) return true;
            if (this.digits[i] < val.digits[i]) return false;
        }
        return false;
    }

    public boolean isZero() {
        for (int digit : digits) {
            if (digit != 0) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean leadingZeros = true;
        for (int digit : digits) {
            if (digit != 0) leadingZeros = false;
            if (!leadingZeros) sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

import java.math.BigInteger;

/**
 * @author xschick
 * @version 1.2
 */

public class Iban {
    int bankCode;
    int bankAccountCode;
    String countryCode;

    public Iban(String theCountryCode, String theBankCode, String theBankAccountCode) {
        this.countryCode = theCountryCode;
        this.bankCode = Integer.parseInt(theBankCode);
        this.bankAccountCode = Integer.parseInt(theBankAccountCode);
    }

    public static void main(String[] args) {
        Iban iban1 = new Iban(args[0], args[1],args[2]);
        iban1.createIban();
    }

    public void createIban() {
        System.out.println(((countryCode + createChecksum(mergeNumbers()) + mergeNumbers().toString()).replaceAll("(.{4})", "$1 ").trim()).substring(0, 27));
    }

    public String createChecksum(BigInteger theMergedNumber) {
        BigInteger b = new BigInteger("97");
        int checksum = (theMergedNumber.remainder(b)).intValue();
        checksum = 98 - checksum;
        if (checksum == 2) {
            return String.format("%02d", checksum);
        } else {
            return Integer.toString(checksum);
        }
    }

    public BigInteger mergeNumbers() {
        String stringBankCode = Integer.toString(bankCode);
        String stringBankAccountCode = String.format("%010d", bankAccountCode);
        String stringTheCountryCodeNumber = Integer.toString(convertCountryCode());
        String allNumbers = stringBankCode + stringBankAccountCode + stringTheCountryCodeNumber;
        return new BigInteger(allNumbers);
    }

    public int convertCountryCode() {
        int firstLetterCode = countryCode.charAt(0) - 55;
        int secondLetterCode = countryCode.charAt(1) - 55;
        return Integer.parseInt(Integer.toString(firstLetterCode) + Integer.toString(secondLetterCode) + "00");
    }
}

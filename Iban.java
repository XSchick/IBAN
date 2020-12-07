import com.sun.jdi.connect.Connector;

import java.math.BigInteger;
import java.util.Scanner;


public class Iban {
    public String countryCode;
    public double bankCode;
    public double bankAccountCode;
    public String stringChecksum;

    public Iban(String theCountryCode, String theBankCode, String theBankAccountCode) {
        this.countryCode = theCountryCode;
        this.bankCode = Double.parseDouble(theBankCode);
        this.bankAccountCode = Double.parseDouble(theBankAccountCode);
        //createIban();
    }

    public static void main(String[] args) {
        Iban iban1 = new Iban("DE", "12345678", "12345");
        iban1.createIban();
    }

    public void createIban() {
        System.out.println(createChecksum(mergeNumbers()));
    }

    public String createChecksum(BigInteger theMergedNumber) {
        BigInteger b = new BigInteger("97");
        int checksum = (theMergedNumber.remainder(b)).intValue();
        checksum = 98 - checksum;
        if (checksum < 10) {
            stringChecksum = String.format("%02d", checksum);
        }
        return stringChecksum;
    }

    public BigInteger mergeNumbers() {
        String stringBankCode = Double.toString(bankCode);
        String stringBankAccountCode = String.format("%010f", bankAccountCode);
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

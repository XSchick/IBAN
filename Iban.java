import java.math.BigInteger;
import java.util.Scanner;


public class Iban {
    public String countryCode;
    public BigInteger checksum;
    public BigInteger bankCode;
    public BigInteger bankAccountCode;
    public BigInteger mergedNumber;
    public BigInteger mergedAllNumbers;


    Scanner scanner = new Scanner(System.in);

    public Iban() {
        input();
    }

    public static void main(String[] args) {
        new Iban();
    }

    public void input() {
        System.out.println("Laenderkennung: ");
        countryCode = scanner.nextLine().toUpperCase();
        System.out.println("Bankleitzahl: ");
        bankCode = scanner.nextBigInteger();
        System.out.println("Kontonummer: ");
        bankAccountCode = scanner.nextBigInteger();
        outputIban();
    }

    public BigInteger createChecksum(BigInteger theBankCode, BigInteger theBankAccountCode) {
        this.mergedNumber = mergeNumbers(theBankAccountCode, theBankCode);
        int countryCodeNumber = convertCountryCode(countryCode);
        BigInteger b;
        BigInteger c = new BigInteger("98");
        checksum = mergeAllNumbers(mergedNumber, countryCodeNumber).remainder(b = new BigInteger("97"));
        checksum = c.subtract(checksum);
        String StringA = checksum.toString();
        BigInteger x;
        if (-1 == (checksum.compareTo(x = new BigInteger("10")))) {
            StringA = String.format("%02d", checksum.toString());
        }
        checksum = new BigInteger(StringA);
        return checksum;
    }

    public BigInteger mergeAllNumbers(BigInteger theMergedNumber, int theCountryCodeNumber) {
        String StringMergedNumber = theMergedNumber.toString();
        String StringTheCountryCodeNumber = Integer.toString(theCountryCodeNumber);
        String allNumbers = StringMergedNumber + StringTheCountryCodeNumber;
        return new BigInteger(allNumbers);
    }

    public int convertCountryCode(String theCountryCode) {
        int firstLetterCode = theCountryCode.charAt(0) - 55;
        int secondLetterCode = theCountryCode.charAt(1) - 55;
        return Integer.parseInt(Integer.toString(firstLetterCode) + Integer.toString(secondLetterCode) + "00");
    }

    public BigInteger mergeNumbers(BigInteger theBankAccountCode, BigInteger theBankCode) {
        String StringBankCode = theBankCode.toString();
        String StringBankAccountCode = String.format("%010d", theBankAccountCode);
        String StringMergedNumber = StringBankCode + StringBankAccountCode;
        BigInteger mergedNumber = new BigInteger(StringMergedNumber);
        return mergedNumber;
    }


    public void outputIban() {
        String StringMergedAllNumbers = createChecksum(bankCode, bankAccountCode).toString();
        String StringChecksum = checksum.toString();
        System.out.println("IBAN: " + countryCode + checksum + mergedNumber);
    }
}

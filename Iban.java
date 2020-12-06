import java.math.BigInteger;
import java.util.Scanner;


public class Iban {
    public String countryCode;
    public int checksum;
    public BigInteger bankCode;
    public BigInteger bankAccountCode;
    public BigInteger mergedNumber;
    Scanner scanner = new Scanner(System.in);

    public Iban() {
        input();
    }

    public static void main(String[] args) {
        new Iban();
    }

    /**
     * Input Scanner
     **/
    public void input() {
        System.out.println("Laenderkennung: ");
        countryCode = scanner.nextLine().toUpperCase();
        System.out.println("Bankleitzahl: ");
        bankCode = scanner.nextBigInteger();
        System.out.println("Kontonummer: ");
        bankAccountCode = scanner.nextBigInteger();
        createChecksum(bankCode, bankAccountCode);
    }

    /**
     *
     **/
    public int createChecksum(BigInteger theBankCode, BigInteger theBankAccountCode) {
        this.mergedNumber = mergeNumbers(theBankAccountCode, theBankCode);
        System.out.println(mergedNumber);
        int countryCodeNumber = convertCountryCode(countryCode);
        System.out.println(countryCodeNumber);
        System.out.println(mergeAllNumbers(mergedNumber, countryCodeNumber));

        return checksum;
    }

    public BigInteger mergeAllNumbers(BigInteger theMergedNumber, int theCountryCodeNumber) {
        String StringMergedNumber = theMergedNumber.toString();
        String StringTheCountryCodeNumber = Integer.toString(theCountryCodeNumber);
        String allNumbers = StringMergedNumber + StringTheCountryCodeNumber;

        return new BigInteger(allNumbers);
    }

    public int convertCountryCode(String theCountryCode) {
        int firstLetterCode = theCountryCode.charAt(0);
        firstLetterCode = firstLetterCode - 64;
        int firstletterNumber = firstLetterCode + 9;
        int secondLetterCode = theCountryCode.charAt(1);
        secondLetterCode = secondLetterCode - 64;
        int secondletterNumber = secondLetterCode + 9;
        return Integer.parseInt(Integer.toString(firstletterNumber) + Integer.toString(secondletterNumber) + "00");
    }

    /**
     * This method first converts the bank code from BigInteger to String
     **/
    public BigInteger mergeNumbers(BigInteger theBankAccountCode, BigInteger theBankCode) {
        String StringBankCode = theBankCode.toString();
        String StringBankAccountCode = String.format("%010d", theBankAccountCode);
        String StringMergedNumber = StringBankCode + StringBankAccountCode;
        System.out.println(StringMergedNumber);
        BigInteger mergedNumber = new BigInteger(StringMergedNumber);
        System.out.println(mergedNumber);
        return mergedNumber;
    }


    public void outputIban(String theCountryCode, int theChecksum, BigInteger theBankCode, BigInteger theBankAccountCode) {
        System.out.println("IBAN:");
    }
}

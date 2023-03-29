package creditcard;

import java.util.Calendar;
import java.util.Date;

public abstract class CCTemplate 
{
    protected String cardNum; 
    protected int expMM, expYY;

     //constructor
    protected CCTemplate(String cardNum, int expMonth, int expYear) { 
        this.cardNum = cardNum;
        this.expMM = expMonth;
        this.expYY = expYear;
    } 

    //check for valid date
    protected final boolean isExpDtValid() {      
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new Date()); 
        int mm = cal.get(Calendar.MONTH) + 1; 
        int yy = cal.get(Calendar.YEAR); 
        boolean result = 
            (yy > expYY) || ((yy == expYY) && (mm > expMM)); 
        return (!result); 
    }
    
    //check for valid chars
    protected final boolean hasValidChars() { 
        String validChars = "0123456789";
        boolean result = true; 
        for (int i = 0; i < cardNum.length(); i++) { 
            if (validChars.indexOf(cardNum.substring(i, i + 1)) <
                0) { 
            result = false; 
            break; 
            } 
        } 
        return result; 
    } 
      
    //calc checksum
    protected final boolean isValidCheckSum() { 
    boolean result = true; 
    int sum = 0; 
    int multiplier = 1; 
    int strLen = cardNum.length(); 
    for (int i = 0; i < strLen; i++) { 
        String digit = cardNum.substring(strLen - i - 1, 
                    strLen - i);
        int currProduct = Integer.parseInt(digit) * multiplier; 
        if (currProduct >= 10) 
        sum += (currProduct% 10) + 1; 
        else 
        sum += currProduct; 
        if (multiplier == 1) 
        multiplier++; 
        else 
        multiplier -- ; 
    } 
    if ((sum% 10) != 0) 
    result = false; 
    return result; 
    } 
      
    //check number of digits
    protected abstract boolean isNumOfDigitsValid();
      
    //check prefix
    protected abstract boolean isValidPrefix();

    protected abstract boolean isAccountInGoodStand();

    public final boolean isValid() { 
        if (!isExpDtValid()) { 
          System.out.println(" Invalid Exp Dt. ");
          return false; 
        } 
        if (!isNumOfDigitsValid()) { 
          System.out.println(" Invalid Number of Digits ");
          return false; 
        } 
        if (!isValidPrefix()) { 
          System.out.println(" Invalid Prefix ");
          return false; 
        }
        if (!hasValidChars()) { 
          System.out.println(" Invalid Characters ");
          return false; 
        } 
        if (!isValidCheckSum()) { 
          System.out.println(" Invalid Check Sum ");
          return false; 
        } 
        if (!isAccountInGoodStand()) { 
          System.out.println( 
            " Account is Inactive/Revoked/Over the Limit ");
          return false; 
        } 
        return true; 
      }
}

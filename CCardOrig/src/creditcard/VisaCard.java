/*
 * validate visa card
 * 
 */
package creditcard;

public class VisaCard extends CCTemplate{ 
    
  //constructor
  public VisaCard(CustVisa customer) { 
     super(customer.number, customer.month, customer.year);
  } 
  
  public boolean isNumOfDigitsValid() { 
    if ((cardNum.length() == 13) || 
        (cardNum.length() == 16)) { 
      return true; 
    } else { 
      return false; 
    } 
  } 
  public boolean isValidPrefix() { 
    String prefix = cardNum.substring(0, 1); 
    if (prefix.equals("4")) { 
      return true; 
    } else { 
      return false; 
    } 
  } 
  public boolean isAccountInGoodStand() { 
    /* 
      Make necessary VISA API calls to 
      perform other checks.
      (set return to true if card is to be valid

    */ 
    return true; 
  }  
} //end visa class

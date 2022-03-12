package DeailDetail.DealDetailsFX;
import java.sql.Time;
import org.apache.log4j.Logger;

public class DealDetails {

	private int dealUniqueID;
	private String orderingCurrency;
	private String toCurrencyCode;
	private long dealAmount;
	private Time timeStamp;
	
	public void setDealUniqueID(int dealUniqueID)
    { this.dealUniqueID=dealUniqueID; }
	public int getDealUniqueID() 
	{ 
		return dealUniqueID;
	}
	public void setDealAmount(long dealAmount)
    { this.dealAmount=dealAmount; }
	public long getDealAmount() 
	{ 
		return dealAmount;
	}
	public void setOrderingCurrency(String orderingCurrency)
    { this.orderingCurrency=orderingCurrency; }
	public String getOrderingCurrency() 
	{ 
		return orderingCurrency;
	}
	
	public void setToCurrencyCode(String toCurrencyCode)
    { this.toCurrencyCode=toCurrencyCode; }
	public String getToCurrencyCode() 
	{ 
		return toCurrencyCode;
	}
	
}

package trackCampusResearchFunds;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Amst
 *
 */
public class FundTransaction {
	private static final String NEWLINE = System.lineSeparator();
	
	private Long trID;
	private String fundNumber;
	private BigDecimal trAmount;
	private String trType;
	private Date trEffDate;
	private Date trPostDate;
	private Date trEntryDate;

	public FundTransaction(String num, BigDecimal amt, String type, Date effDt, Date postDt, Date enterDt) {
		// Constructor WITHOUT trans ID
		this.trID = null;
		this.fundNumber = num;
		this.trAmount = amt;
		this.trType = type;
		this.trEffDate = effDt;
		this.trPostDate = postDt;
		this.trEntryDate = enterDt;
	}
	
	public FundTransaction(Long ID, String num, BigDecimal amt, String type, Date effDt, Date postDt, Date enterDt) {
		// Constructor WITH trans ID
		this.trID = ID;
		this.fundNumber = num;
		this.trAmount = amt;
		this.trType = type;
		this.trEffDate = effDt;
		this.trPostDate = postDt;
		this.trEntryDate = enterDt;
	}

	public String htmlTableRow() {
		String tableRow =
				"<tr>" +
					"<td style=\"text-align:center;\">" + this.getId() + "</td>" +
					"<td style=\"text-align:left;\">" + this.getNumber() + "</td>" +
					"<td style=\"text-align:right;\">" + this.getAmount() + "</td>" +
					"<td style=\"text-align:center;\">" + this.getType() + "</td>" +
					"<td style=\"text-align:center;\">" + this.getEffDt() + "</td>" +
					"<td style=\"text-align:center;\">" + this.getPstDt() + "</td>" +
					"<td style=\"text-align:center;\">" + this.getEntDt() + "</td>" +
				"</tr>" + NEWLINE;
		
		return tableRow;
	}
	
	public Long getId() {
		return this.trID;
	}

	public String getNumber() {
		return this.fundNumber;
	}

	public BigDecimal getAmount() {
		return this.trAmount;
	}

	public String getType() {
		return this.trType;
	}

	public Date getEffDt() {
		return this.trEffDate;
	}
	
	public Date getPstDt() {
		return this.trPostDate;
	}
	
	public Date getEntDt() {
		return this.trEntryDate;
	}

	public String getDateText(Date iDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(iDate);
    }
	
	public String toString() {
		return ("ID:  " + this.trID +
				"  |  Fund Num:  " + this.fundNumber +
				"  |  Amount:  " + this.trAmount +
				"  |  Trans Type:  " + this.trType +
				"  |  Effective Date:  " + this.trEffDate +
				"  |  Post Date:  " + this.trPostDate +
				"  |  Entry Date:  " + this.trEntryDate);
	}

	public static void main(String[] args) {
		Calendar rightNow = Calendar.getInstance();
		System.out.println(rightNow.toString());
	}

/*
 * Method accepts an array of column names as strings and returns a string
 * with the column names as an HTML table header row.
 */
	public static String getHtmlTblHeader(String[] arrRsColNames) {
		
		StringBuilder retHtmlTableHeader = new StringBuilder("<thead>" + NEWLINE);
		retHtmlTableHeader.append("<tr>" + NEWLINE);
		
		int numColumns = arrRsColNames.length;
		
		for (int col = 0; col < numColumns; col++)
		{
			retHtmlTableHeader.append("<th style=\"text-align:center;\">" + arrRsColNames[col] + "</th>" + NEWLINE);
		}
		
		retHtmlTableHeader.append("</tr>" + NEWLINE);
		
		retHtmlTableHeader.append("</thead>" + NEWLINE);
		
		return retHtmlTableHeader.toString();
	}
}

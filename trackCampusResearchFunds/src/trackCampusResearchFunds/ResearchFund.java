package trackCampusResearchFunds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Map;

public class ResearchFund {
	public static final int MAX_DISP_FUNDNUM_LEN = 15;

	private String fundNumber;
	private String fundName;
	private String fundType;
//	private double balance;

	public ResearchFund(String number, String name, String type) {
		this.fundNumber = number;
		this.fundName = name;
		this.fundType = type;
//		this.balance = bal;
	}

	public String getNumber() {
		return this.fundNumber;
	}
	
	public String getName() {
		return this.fundName;
	}
	
	public String getType() {
		return this.fundType;
	}
	
//	public double getBal() {
//		return this.balance;
//	}
	
	public static ResultSet fundList()
	{
		ResultSet rs = null;
		
		Connection conn = null;
		
		try
		{
			conn = DriverManager.getConnection(
					"jdbc:ucanaccess://" + CampusResearchFundsApp.HOME_PATH + "/database/CampusResearchFunds.accdb");
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Statement s = null;

		String sql = "SELECT * FROM Funds";
		
		try
		{
			s = conn.createStatement();
			rs = s.executeQuery(sql);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
	        if (s != null)
	        {
	        	try
	        	{
	        		s.close();
	        	}
	        	catch (SQLException e)
	        	{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
	        }
	    }
		
		return rs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

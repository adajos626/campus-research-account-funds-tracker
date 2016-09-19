package trackCampusResearchFunds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
//import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
//import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;

public class CampusResearchFundsApp {
	public static final String FONT_FAMILY = "Lucida Console";
	public static final int FONT_SIZE_LABEL = 28;
	public static final int FONT_SIZE_FIELD = 24;
	
	public static final String HOME_PATH = System.getProperty("user.dir");
//	public static final String HOME_PATH = "C:/absolute/path/to/folder-with-jar-executable";
//	folder above would need the following subfolders: database, libsAndPlugins, and output
	
	private static final String NEWLINE = System.lineSeparator();
	
	private JFrame frmCampusResearchFunds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CampusResearchFundsApp window = new CampusResearchFundsApp();
					window.frmCampusResearchFunds.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String workingdirectory = System.getProperty("user.dir");
				System.out.println("Working Directory: " + workingdirectory);
				Charset csDefault = Charset.defaultCharset();
				System.out.println(csDefault.displayName());
				System.out.println(csDefault.name());
				System.out.println(csDefault.aliases().toString());
			}
		});
	}
	
	public static Map<Integer, String> getAllJdbcTypeNames() throws IllegalArgumentException, IllegalAccessException {

	    Map<Integer, String> result = new HashMap<Integer, String>();

	    for (Field field : Types.class.getFields()) {
	        result.put((int)field.get(null), field.getName());
	    }

	    return result;
	}
	
	public static String getFileEncoding(String filePath) {
		FileInputStream fis = null;
	    InputStreamReader isr =null;
	    String encodingString = null;
	      
	    try {
	    	// new input stream reader is created 
	        fis = new FileInputStream(filePath);
	        isr = new InputStreamReader(fis);
	         
	        // the name of the character encoding is determined
	        encodingString = isr.getEncoding();
	        System.out.println("Character Encoding: " + encodingString);
	    } catch (Exception e) {
	    	// print error
	        System.out.println("File could not be accessed in order to determine its character encoding.");
	    } finally {
	    	// closes the stream and releases resources associated
	        if (fis != null)
	        	try {
					fis.close();
				} catch (IOException e) {
			    	// print error
			        System.out.println("The file input stream is already closed.");
					e.printStackTrace();
				}
	        if (isr != null)
	        	try {
	        		isr.close();
				} catch (IOException e) {
			    	// print error
			        System.out.println("The input stream reader is already closed.");
					e.printStackTrace();
				}
	        }
	    return encodingString;
    }

	/**
	 * Create the application.
	 */
	public CampusResearchFundsApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCampusResearchFunds = new JFrame();
		frmCampusResearchFunds.setTitle("Campus Research Funds Tracker");
		frmCampusResearchFunds.setBounds(100, 100, 598, 499);
		frmCampusResearchFunds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCampusResearchFunds.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmCampusResearchFunds.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_3 = Box.createVerticalStrut(26);
		panel.add(verticalStrut_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Select an Option Below");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		Component verticalStrut = Box.createVerticalStrut(40);
		panel.add(verticalStrut, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		frmCampusResearchFunds.getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("Add New Fund");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnNewButton);
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FundForm frame = new FundForm();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_1);
		
		JButton btnNewButton_1 = new JButton("Add New Transaction");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 24));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_2);
		
		JButton btnNewButton_2 = new JButton("View Transactions");
		panel_4.add(btnNewButton_2);
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.setFont(new Font("Verdana", Font.PLAIN, 24));
		
		JPanel panel_1 = new JPanel();
		frmCampusResearchFunds.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblClickXAt = new JLabel("Click X at upper-right to exit.");
		lblClickXAt.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickXAt.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel_1.add(lblClickXAt, BorderLayout.CENTER);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_4, BorderLayout.SOUTH);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<Integer, String> jdbcMappings = null;
				try {
					jdbcMappings = getAllJdbcTypeNames();
				} catch (IllegalArgumentException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IllegalAccessException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				Connection conn = null;

				try {
					conn = DriverManager.getConnection(
							"jdbc:ucanaccess://" + CampusResearchFundsApp.HOME_PATH + "/database/CampusResearchFunds.accdb");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Statement s = null;

				String sql = "SELECT * FROM Transactions";
				
				ResultSet rs = null;
				
				try {
					s = conn.createStatement();
					rs = s.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int rsColCount = 0;
				
				int rsRowCount = 0;
				
//				ArrayList<FundTransaction> rsAl = new ArrayList<FundTransaction>();
				
				String htmlTopPath = CampusResearchFundsApp.HOME_PATH + "/libsAndPlugins/trans-report-html-top.txt";
				
				String encoding = getFileEncoding(htmlTopPath);
				
				Charset cs = Charset.forName(encoding);
				
				File htmlTopFile = new File(htmlTopPath);
				
				String htmlTableHeader = null;

				List<String> fileLines = null;
				
				try {
					fileLines = Files.readAllLines(htmlTopFile.toPath(), cs);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				StringBuilder tableSb = new StringBuilder("");
				
				for (String line : fileLines)
				{
					tableSb.append(line + NEWLINE);
//			        System.out.println(line);
			    }
				
				try {
					while (rs.next()) {
						rsRowCount ++;
						if (rsRowCount == 1)
						{
							rsColCount = rs.getMetaData().getColumnCount();
							String[] arrRsColNames = new String[rsColCount];
							int[] arrRsColTypesIntVal = new int[rsColCount];
							String[] arrRsColTypeString = new String[rsColCount];
							
							for (int col = 0; col < rsColCount; col++)
							{
								arrRsColNames[col] = rs.getMetaData().getColumnLabel(col + 1);
								arrRsColTypesIntVal[col] = rs.getMetaData().getColumnType(col + 1);
								arrRsColTypeString[col] = jdbcMappings.get(arrRsColTypesIntVal[col]);
								
								System.out.println(arrRsColNames[col] + "\t" + arrRsColTypesIntVal[col] + "\t" + arrRsColTypeString[col]);
							}
							htmlTableHeader = FundTransaction.getHtmlTblHeader(arrRsColNames);
							tableSb.append(htmlTableHeader);
							tableSb.append("<tbody>" + NEWLINE);
						}

						FundTransaction rsTrans = new FundTransaction(
								rs.getLong("ID"),
								rs.getString("FundNum"),
								rs.getBigDecimal("trAmount"),
								rs.getString("trType"),
								rs.getDate("effDate"),
								rs.getDate("postDate"),
								rs.getDate("entryDate"));

//					rsAl.add(rsTrans);

						tableSb.append(rsTrans.htmlTableRow());
						
//					System.out.println(rsTrans.toString());
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				tableSb.append("</tbody>" + NEWLINE);
				tableSb.append("</table>" + NEWLINE);
				tableSb.append("</div>" + NEWLINE);
				tableSb.append("</body>" + NEWLINE);
				tableSb.append("</html>" + NEWLINE);
				
//				System.out.println(tableSb);

				String outputPath = CampusResearchFundsApp.HOME_PATH + "/output/transaction-report.html";

				Charset csDefault = Charset.defaultCharset();
				
				File transactionFile = new File(outputPath);
				
				String[] reportLinesArr = tableSb.toString().split(NEWLINE);
				
				ArrayList<String> reportLines = new ArrayList<String>();
//				ArrayList<String> reportLlines = tableSb.toString().split(NEWLINE);
				
				Collections.addAll(reportLines, reportLinesArr);
				
				try {
					Files.write(transactionFile.toPath(), reportLines, csDefault);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				File htmlFile = new File(outputPath);

				try {
					System.out.println(htmlFile.toURI().toURL().toString());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				finally {
			        if (s != null)
			        {
			        	try {
							s.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }
			    }
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							TransactionForm frame = new TransactionForm();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}

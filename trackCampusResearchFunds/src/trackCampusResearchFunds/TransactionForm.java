package trackCampusResearchFunds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
//import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

public class TransactionForm extends JFrame {

	/**
	 * 
	 */
	private static final String FONT_NAME = CampusResearchFundsApp.FONT_FAMILY;
	private static final int LABEL_SIZE = CampusResearchFundsApp.FONT_SIZE_LABEL;
	private static final int FIELD_SIZE = CampusResearchFundsApp.FONT_SIZE_FIELD;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public TransactionForm() {
		setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		setTitle("Enter Transaction");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblFundNumber = new JLabel("Fund Number");
		lblFundNumber.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblFundNumber, "4, 4");
		
		JComboBox<String> fundNumber = new JComboBox<String>();
		fundNumber.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		ResultSet fundsRs = ResearchFund.fundList();
		int rsRowCount = 0;
	    try {
			while (fundsRs.next())
			{
				rsRowCount++;
				if (rsRowCount == 1)
				{
					char[] blankItem = new char[ResearchFund.MAX_DISP_FUNDNUM_LEN + 2];

					for (int i = 0; i < (ResearchFund.MAX_DISP_FUNDNUM_LEN + 1); i++)
					{
						blankItem[i] = ' ';
					}

					blankItem[ResearchFund.MAX_DISP_FUNDNUM_LEN + 1] = ':';
					String strBlankItem = new String(blankItem);
				    fundNumber.addItem(strBlankItem);
				}
				if (fundsRs.getString("FundName") != null && fundsRs.getString("FundNum") != null)
				{
					int fundNumLen = fundsRs.getString("FundNum").length();
					int itemPadding = ResearchFund.MAX_DISP_FUNDNUM_LEN - fundNumLen;
					char[] listItem = new char[ResearchFund.MAX_DISP_FUNDNUM_LEN];
					
					for (int i = 0; i < ResearchFund.MAX_DISP_FUNDNUM_LEN; i++)
					{
						if (i < itemPadding)
						{
							listItem[i] = ' ';
						}
						else
							listItem[i] = fundsRs.getString("FundNum").charAt(i - itemPadding);
					}
					
					String listItemName;
					
					if (fundsRs.getString("FundName").length() > 25)
						listItemName = fundsRs.getString("FundName").substring(0, 26);
					else
						listItemName = fundsRs.getString("FundName");

					String strListItem = new String(listItem) + " : " + listItemName;
					
				    fundNumber.addItem(strListItem);
				}
			}
			if (rsRowCount == 0)
			{
				char[] blankItem = new char[ResearchFund.MAX_DISP_FUNDNUM_LEN + 2];

				for (int i = 0; i < (ResearchFund.MAX_DISP_FUNDNUM_LEN + 1); i++)
				{
					blankItem[i] = ' ';
				}

				blankItem[ResearchFund.MAX_DISP_FUNDNUM_LEN + 1] = ':';
				String strBlankItem = new String(blankItem);
			    fundNumber.addItem(strBlankItem);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    try {
			fundsRs.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		contentPane.add(fundNumber, "8, 4, left, default");

		JLabel lblTransactionAmount = new JLabel("Transaction Amount");
		lblTransactionAmount.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblTransactionAmount, "4, 8");

		JFormattedTextField amount = new JFormattedTextField();
		amount.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(amount, "8, 8");
		
		NumberFormatter displayCurrencyFormatter = new NumberFormatter(new DecimalFormat("$ #,###.00"));
		NumberFormatter editCurrencyFormatter = new NumberFormatter(new DecimalFormat("#.##")); 
		NumberFormatter defaultCurrencyFormatter = new NumberFormatter(new DecimalFormat("#.##"));
		displayCurrencyFormatter.setValueClass(Double.class);
		editCurrencyFormatter.setValueClass(Double.class);
		defaultCurrencyFormatter.setValueClass(Double.class);

		DefaultFormatterFactory amountFactory = new DefaultFormatterFactory(defaultCurrencyFormatter, displayCurrencyFormatter, editCurrencyFormatter);
		amount.setFormatterFactory(amountFactory);
		
		JLabel lblTransactionType = new JLabel("Transaction Type");
		lblTransactionType.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblTransactionType, "4, 12");
		
		JComboBox<String> trType = new JComboBox<String>();
		trType.setModel(new DefaultComboBoxModel<String>(new String[] {"", "increase (revenue, donation, award)", "decrease (expense, payment, cut)"}));
		trType.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(trType, "8, 12, fill, default");
		
		JLabel lblEffectiveDate = new JLabel("Effective Date");
		lblEffectiveDate.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblEffectiveDate, "4, 16");
		
		JXDatePicker dp_effDt = new JXDatePicker();
		dp_effDt.getEditor().setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(dp_effDt, "8, 16");
		
		JLabel lblDatePosted = new JLabel("Date Posted in Acctg System");
		lblDatePosted.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblDatePosted, "4, 20");
		
		JXDatePicker dp_postDt = new JXDatePicker();
		dp_postDt.getEditor().setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(dp_postDt, "8, 20");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MathContext mcUsCurrency = new MathContext(16, RoundingMode.HALF_UP);

				try {
					amount.commitEdit();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Date trDateEntered = new Date();

				String amountString = amount.getValue().toString();
				BigDecimal bdAmt = new BigDecimal(amountString, mcUsCurrency).setScale(2, RoundingMode.HALF_UP);
				System.out.println("Amount String1 from Transaction Form: " + amountString);

				System.out.println(fundNumber.getSelectedItem().toString());
				String parsedNum = fundNumber.getSelectedItem().toString().substring(0, ResearchFund.MAX_DISP_FUNDNUM_LEN).trim();
				System.out.println(":" + parsedNum + ":");
				
				FundTransaction ft = new FundTransaction(fundNumber.getSelectedItem().toString().substring(0, ResearchFund.MAX_DISP_FUNDNUM_LEN).trim(), bdAmt, trType.getSelectedItem().toString(), dp_effDt.getDate(), dp_postDt.getDate(), trDateEntered);
				System.out.println(ft.toString());
				System.out.println("Number: " + ft.getNumber() + "  Amount: " + ft.getAmount() + "  Effective: " + ft.getDateText(ft.getEffDt()));
				
				Connection conn = null;
				try {
					conn = DriverManager.getConnection(
							"jdbc:ucanaccess://" + CampusResearchFundsApp.HOME_PATH + "/database/CampusResearchFunds.accdb");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				PreparedStatement s = null;

				String sql = "INSERT INTO Transactions (FundNum, trAmount, trType, effDate, postDate, entryDate) VALUES (?, ?, ?, ?, ?, ?)";

				try {
					s = conn.prepareStatement(sql);
					s.setString(1, ft.getNumber());
					s.setBigDecimal(2, ft.getAmount());
					s.setString(3, ft.getType());
					s.setObject(4, ft.getEffDt());
					s.setObject(5, ft.getPstDt());
					s.setObject(6, ft.getEntDt());
					 
					int rowsInserted = s.executeUpdate();
					if (rowsInserted > 0) {
					    System.out.println("A new transaction was inserted successfully!");
					    TransactionForm.this.dispose();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(btnSubmit, "8, 26, left, default");
	}
}

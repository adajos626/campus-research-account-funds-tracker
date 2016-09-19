package trackCampusResearchFunds;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
//import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
//import javax.swing.JFormattedTextField;
//import java.util.Locale;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.EventQueue;
//import java.sql.DriverManager;
import java.awt.Font;

public class FundForm extends JFrame {

	/**
	 * 
	 */
	private static final String FONT_NAME = CampusResearchFundsApp.FONT_FAMILY;
	private static final int LABEL_SIZE = CampusResearchFundsApp.FONT_SIZE_LABEL;
	private static final int FIELD_SIZE = CampusResearchFundsApp.FONT_SIZE_FIELD;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fundNumber;
	private JTextField fundName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public FundForm() {
		setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		setTitle("Create New Fund");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblFundNumber = new JLabel("Fund Number");
		lblFundNumber.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblFundNumber, "4, 4");
		
		fundNumber = new JTextField();
		fundNumber.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(fundNumber, "8, 4, left, default");
		fundNumber.setColumns(10);
		
		JLabel lblFundName = new JLabel("Fund Name");
		lblFundName.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblFundName, "4, 8");
		
		fundName = new JTextField();
		fundName.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		contentPane.add(fundName, "8, 8, left, default");
		fundName.setColumns(20);
		
		JLabel lblFundType = new JLabel("Fund Type");
		lblFundType.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_SIZE));
		contentPane.add(lblFundType, "4, 12");
		
		JComboBox<String> fundType = new JComboBox<>();
		fundType.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		fundType.setModel(new DefaultComboBoxModel<String>(new String[] {"", "endowment", "federal grant", "non-federal grant", "clinical trial", "R&D (e.g. trial residuals)", "F&A (money received to pay indirect costs)"}));
		contentPane.add(fundType, "8, 12, left, default");
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font(FONT_NAME, Font.PLAIN, FIELD_SIZE));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResearchFund rf = new ResearchFund(fundNumber.getText(), fundName.getText(), fundType.getSelectedItem().toString());
				System.out.println("Number: " + rf.getNumber() + "  Name: " + rf.getName() + "  Type: " + rf.getType());

				Connection conn = null;
				try {
					conn = DriverManager.getConnection(
							"jdbc:ucanaccess://" + CampusResearchFundsApp.HOME_PATH + "/database/CampusResearchFunds.accdb");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				PreparedStatement s = null;

				String sql = "INSERT INTO Funds (FundNum, FundName, FundType) VALUES (?, ?, ?)";

				try {
					s = conn.prepareStatement(sql);
					s.setString(1, rf.getNumber());
					s.setString(2, rf.getName());
					s.setString(3, rf.getType());
					 
					int rowsInserted = s.executeUpdate();
					if (rowsInserted > 0) {
					    System.out.println("A new fund was inserted successfully!");
					    FundForm.this.dispose();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			contentPane.add(btnNewButton, "8, 18, left, default");
		}
}

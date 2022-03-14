package DeailDetail.DealDetailsFX;
// Java program to implement
//Deal details Form
// using Java Swing

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
class DealDetailForm extends JFrame	implements ActionListener {
	private static final Logger logger = Logger.getLogger(DealDetailForm.class);

	private String uID;
	private String otorderingCurrency;
	private String oorderingCurrency;
	private String otimeStamp;
	private String odealAmount;

	// Components of the Form
	private Container c;
	private JLabel title;
	private JLabel lorderingCurrency;
	private JTextField torderingCurrency;
	private JLabel uniqueID;
	private JTextField tuniqueID;
	private JLabel dealAmount;
	private JTextField tdealAmount;
	
	private JLabel timeStamp;
	private JLabel toCurrency;
	private JTextField ttoCurrency;
	
	private JCheckBox term;
	private JButton sub;
	private JButton reset;
	private JLabel res;
	JFormattedTextField formattedField;
	private JTextField tformattedField;
	
	public Map<String,String> mapDetails=new HashMap<String,String>(); 

	// constructor, to initialize the components
	// with default values.
	public DealDetailForm()
	{
		setTitle("Deal Details Form");
		setBounds(300, 90, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);

		title = new JLabel("Deal Details Form");
		title.setFont(new Font("Arial", Font.PLAIN, 25));
		title.setSize(300, 30);
		title.setLocation(170, 30);
		c.add(title);

		
		uniqueID = new JLabel("Unique ID");
		uniqueID.setFont(new Font("Arial", Font.PLAIN, 15));
		uniqueID.setSize(100, 20);
		uniqueID.setLocation(100, 100);
		c.add(uniqueID);

		tuniqueID = new JTextField();
		tuniqueID.setFont(new Font("Arial", Font.PLAIN, 15));
		tuniqueID.setSize(150, 20);
		tuniqueID.setLocation(200, 100);
		c.add(tuniqueID);
		
		lorderingCurrency = new JLabel("From Currency");
		lorderingCurrency.setFont(new Font("Arial", Font.PLAIN, 15));
		lorderingCurrency.setSize(100, 20);
		lorderingCurrency.setLocation(100, 150);
		c.add(lorderingCurrency);

		torderingCurrency = new JTextField();
		torderingCurrency.setFont(new Font("Arial", Font.PLAIN, 15));
		torderingCurrency.setSize(190, 20);
		torderingCurrency.setLocation(200, 150);
		c.add(torderingCurrency);

		
		toCurrency = new JLabel("To Currency");
		toCurrency.setFont(new Font("Arial", Font.PLAIN, 15));
		toCurrency.setSize(100, 20);
		toCurrency.setLocation(100, 200);
		c.add(toCurrency);

		ttoCurrency = new JTextField();
		ttoCurrency.setFont(new Font("Arial", Font.PLAIN, 15));
		ttoCurrency.setSize(190, 20);
		ttoCurrency.setLocation(200, 200);
		c.add(ttoCurrency);

		dealAmount = new JLabel("Deal Amount");
		dealAmount.setFont(new Font("Arial", Font.PLAIN, 15));
		dealAmount.setSize(100, 20);
		dealAmount.setLocation(100, 300);
		c.add(dealAmount);

		tdealAmount = new JTextField();
		tdealAmount.setFont(new Font("Arial", Font.PLAIN, 15));
		tdealAmount.setSize(150, 20);
		tdealAmount.setLocation(200, 300);
		c.add(tdealAmount);

		formattedField = new JFormattedTextField(java.util.Calendar
	            .getInstance().getTime());

		timeStamp = new JLabel("Time Stamp");
		timeStamp.setFont(new Font("Arial", Font.PLAIN, 15));
		timeStamp.setSize(100, 20);
		timeStamp.setLocation(100, 250);
		c.add(timeStamp);

		tformattedField = new JTextField();
		tformattedField.setFont(new Font("Arial", Font.PLAIN, 15));
		tformattedField.setSize(150, 20);
		tformattedField.setLocation(200, 250);
		tformattedField.setText(formattedField.getText());
		c.add(tformattedField);
		
		sub = new JButton("Submit");
		sub.setFont(new Font("Arial", Font.PLAIN, 15));
		sub.setSize(100, 20);
		sub.setLocation(150, 450);
		sub.addActionListener(this);
		c.add(sub);

		res = new JLabel("");
		res.setFont(new Font("Arial", Font.PLAIN, 20));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		c.add(res);

		setVisible(true);
	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == sub) {
			if(isFeildsEmpty() || isIDFeildsInvalid()|| isAmountFeildsInvalid())
			{
				JOptionPane.showMessageDialog(null, "Some fields are invalid !!");
			}
			else
			
			{
				if(!isIDUnique(Integer.parseInt(tuniqueID.getText().trim())))
				{
					JOptionPane.showMessageDialog(null, "The ID is already used !!\n please enter a new one..");
				}
				else if(!isCurrencyCodeValid())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid currency code !!");
				}
				else
				{
				int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to submit?", "submit?", JOptionPane.YES_NO_OPTION);
	            if ( res == 0 ) {
	            	logger.debug("Adding values to CSV...");
					setValues();
	            	logger.debug("Close the form...");
	                this.dispose();
	            }
				}
			}
	}
		
	}
	private void setValues()
	{
		odealAmount = tdealAmount.getText();
		otorderingCurrency = torderingCurrency.getText();
		uID= tuniqueID.getText();
		oorderingCurrency = ttoCurrency.getText();
		otimeStamp = formattedField.getText();
		
		List<String[]> list = new ArrayList<>();
		
		String curDirectory = System.getProperty("user.dir");
	
		String[] header = {"DEAL_ID", "FROM_CURRENCY", "TO_CURRENCY", "TIMESTAMP","AMOUNT"};
		String[] record1 = {uID, oorderingCurrency, otorderingCurrency,otimeStamp , odealAmount};
		list.add(header);
		list.add(record1);
		
		CSVWriter writer = new CSVWriter();
			logger.info("File Name: "+curDirectory+"\\dealDetails.csv");
		writer.writeToCsvFile(list, new File(curDirectory+"\\dealDetails.csv"));
		
	}

	
	
	private Boolean isIDFeildsInvalid()
	{
		logger.info("Validating Unique ID Feilds...");

		if(tuniqueID.getText().trim() != null) {
			try {// if is number
			    Integer.parseInt(tuniqueID.getText().trim());
			} catch (NumberFormatException error) {
				logger.info("The Unique ID is invalid...");
				logger.error(error.getMessage());
				return true;
			}
	}
		return false;
	}
	
	private Boolean isAmountFeildsInvalid()
	{
		logger.info("Validating Amount Feilds...");

		if(tdealAmount.getText().trim() != null) {
			try {// if is number
			    Integer.parseInt(tdealAmount.getText().trim());
			} catch (NumberFormatException error) {
				logger.info("The Amount is invalid...");
				logger.error(error.getMessage());
				return true;
			}
	}
		return false;
	}
	
	private Boolean isFeildsEmpty()
	{
		logger.info("Checking if Feilds are empty...");
		if(torderingCurrency.getText().isEmpty() || tuniqueID.getText().isEmpty() || ttoCurrency.getText().isEmpty() || tdealAmount.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	
	 protected boolean isIDUnique(int id )
	 {
		return MongoDBConnection.isIDUnique(id);
	 }
	public boolean isCurrencyCodeValid()
	{
		boolean validCurrencyCode = false;
		String toCurrency = torderingCurrency.getText().trim();
		String fromCurrency = ttoCurrency.getText().trim();
		if ((CurrncyCodeEnum.lookupByName(toCurrency) != null) && (CurrncyCodeEnum.lookupByName(fromCurrency) != null))
		{
			validCurrencyCode = true;
		}
		return validCurrencyCode;
		
	}
}


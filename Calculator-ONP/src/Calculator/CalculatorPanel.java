package Calculator;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;


public class CalculatorPanel extends JPanel {

	List<JButton> buttons=new ArrayList<JButton>();
	ReversPolishNotation rpn=new ReversPolishNotation();
	char table[][]=new char[][]{{' ',' ','(',')'},{'7','8','9','/'},{'4','5','6','*'},{'1','2','3','-'},{'0','.',' ','+'}};
	private JTextField textField;
	
	private void count()
	{
			textField.setText(rpn.count(textField.getText()));
	}
	
	public CalculatorPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 60, 60, 60};
		gridBagLayout.rowHeights = new int[]{60, 60, 60, 60, 60, 60,60};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth=4;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					count();
			}
		});
		add(textField, gbc_textField);
		
		JButton btnBp = new JButton("BP");
		btnBp.setBackground(new Color(255, 255, 255));
		btnBp.setBorderPainted(false);
		GridBagConstraints gbc_btnBp = new GridBagConstraints();
		gbc_btnBp.fill=GridBagConstraints.BOTH;
		gbc_btnBp.insets = new Insets(0, 0, 5, 5);
		gbc_btnBp.gridx = 0;
		gbc_btnBp.gridy = 1;
		btnBp.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(textField.getText().length()!=0)
		    	{
			    	StringBuilder builder=new StringBuilder(textField.getText());
			    	builder.replace(textField.getText().length()-1, textField.getText().length(), "");
			        textField.setText(builder.toString());
		    	}
		    }
		});
		add(btnBp, gbc_btnBp);
		
		JButton btnClr = new JButton("CLR");
		btnClr.setBackground(new Color(255, 255, 255));
		btnClr.setBorderPainted(false);
		GridBagConstraints gbc_btnClr = new GridBagConstraints();
		gbc_btnClr.fill=GridBagConstraints.BOTH;
		gbc_btnClr.insets = new Insets(0, 0, 5, 5);
		gbc_btnClr.gridx = 1;
		gbc_btnClr.gridy = 1;
		btnClr.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {		        
		        textField.setText("");
		    }
		});
		add(btnClr, gbc_btnClr);
		
		JButton button_6 = new JButton("=");
		button_6.setBackground(new Color(255, 255, 255));
		button_6.setBorderPainted(false);
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill=GridBagConstraints.BOTH;
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 5;
		button_6.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
			    	count();
		    }			
		});
		add(button_6, gbc_button_6);

		for(int i=1;i<6;++i)
		{
			for(int j=0;j<4;++j)
			{
				if(table[i-1][j]!=' ')
				{
					JButton b=new JButton(String.valueOf(table[i-1][j]));
					b.setBackground(new Color(255, 255, 255));
					b.setBorderPainted(false);
					GridBagConstraints gbcbutton=new GridBagConstraints();
					gbcbutton.fill=GridBagConstraints.BOTH;
					gbcbutton.insets=new Insets(0, 0, 5, 5);
					gbcbutton.gridx=j;
					gbcbutton.gridy=i;
					b.addActionListener(new ActionListener()
					{
					    public void actionPerformed(ActionEvent e)
					    {
					        textField.setText(textField.getText()+b.getText());
					    }
					});
					add(b,gbcbutton);
					buttons.add(b);
				}
			}
		}
	}
}

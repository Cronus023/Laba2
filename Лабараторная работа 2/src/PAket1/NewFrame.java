package PAket1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.lang.Math;

public class NewFrame extends JFrame{
	//������ ����
	private static final int WIDTH = 700;
	private static final int HEIGHT = 600;
	
	// ��������� ���� ��� X,Y,Z
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	Double mem1 = 0.0;
	Double mem2 = 0.0;
	Double mem3 = 0.0;
	// ��������� ���� ��� ����������
	private JTextField textFieldResult;
	private JTextField textFieldResultMem;
	// ������ �����-������ ��� ����������� ������������ ��������� � ������
	private ButtonGroup radioButtons = new ButtonGroup();
	private ButtonGroup radioButtons1 = new ButtonGroup();
	
	// ��������� ��� ����������� �����-������ (��� ������� 1 � 2)
	private Box hboxFormulaType = Box.createHorizontalBox();
	
	// ��������� ��� ����������� �����-������ (��� ���������� 1,2,3)
	private Box hboxMemType = Box.createHorizontalBox();
	
	// ������������� ��������� �������
	private int formulaId = 1;
	// ������������� ��������� ���������� 
	private int formulaId1 = 1;
    
	Double result = 0.0; // �������� ���������� �� ��������� ����������
	
	//������� 1 � 2
	public Double calculate1(Double x, Double y, Double z) {
		return Math.pow(Math.log((1+x)*(1+x))+Math.cos(Math.PI*z*z*z),Math.cos(y))+Math.pow(Math.pow(Math.E, x*x)+Math.cos(Math.pow(Math.E, z))+Math.sqrt(1/y), 1/x);
	}
	public Double calculate2(Double x, Double y,  Double z) {
		return y*x*x/(Math.log(Math.pow(z, y))+ Math.pow(Math.cos(Math.cbrt(x)),2));
	}
		
	//��������������� ����� ��� �����-������
	private void addRadioButton(String buttonName, final int formulaId,int flag) {
		// ������� ��������� �����-������ � �������� �������
		JRadioButton button = new JRadioButton(buttonName);
		
		// ���������� � ������������ ����������,
		//������� ����� ������������� ������������� ������ ������� ������ formulaId ��� formulaId1
		if (flag == 0) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				NewFrame.this.formulaId = formulaId;
			}
			});	
			radioButtons.add(button);
			// ��������� �����-������ � ���������
			// ��� ����� ������ �� ��������� ������� ����� ������ ������
			hboxFormulaType.add(button);	
		}
		else {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				NewFrame.this.formulaId1 = formulaId;
				if (formulaId1==1) {
	    	        textFieldResultMem.setText(mem1.toString());
	    	    }
	    	   
	    	    else if(formulaId1 == 2) {
	    	        textFieldResultMem.setText(mem2.toString());
	    	    }
	    	    else if (formulaId1 == 3) {
	    	        textFieldResultMem.setText(mem3.toString());
	    	    }
			}
			});	
			radioButtons1.add(button);
			// �������� �����-������ � ���������
			// ��� ����� ������ �� ��������� ������� ����� ������ ������
			hboxMemType.add(button);
		}
    }
	
    NewFrame (){
    	super("����������");
    	setSize(WIDTH, HEIGHT);
    	
    	// ����������� ���� ���������� �� ������
    	Toolkit kit = Toolkit.getDefaultToolkit();
    	setLocation((kit.getScreenSize().width - WIDTH)/2,
    	(kit.getScreenSize().height - HEIGHT)/2);
    	
    	//------------------------------------------------------------------------
    	
    	//�������� � ����������� �� ������ �������:

    	// ������� ����� ��� ������������� �������� �� ������ ����
    	hboxFormulaType.add(Box.createHorizontalGlue());
    	
    	// ������� �����-������ ��� ������� 1 � 2 � ��������� �� � ���������
    	addRadioButton("������� 1", 1, 0);
    	addRadioButton("������� 2", 2, 0);
    	
    	// ������������� ���������� 1-�� ������ �� ������ "�������"
    	radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(),true);
    	
    	// ������� ����� ��� ������������� �������� �� ������� ����
    	hboxFormulaType.add(Box.createHorizontalGlue());
    	
    	// ������ ����� ��� ������� � ������� ������ BorderFactory 
    	hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	//------------------------------------------------------------------------------
    	//�������� ������� ��� ���������� X, Y, Z:
    	
    	// X:
    	JLabel labelForX = new JLabel("X:");
    	
    	// ������� ��������� ���� ��� ����� �������� ���������� X,
    	// (�� ��������� �������� ���� - 0)
    	textFieldX = new JTextField("0", 10);
    	
    	// ������������� ������������ ������ ������ ��������� ��� �������������� ���������������
    	textFieldX.setMaximumSize(textFieldX.getPreferredSize());
    	
    	// ���������� ��� Y � Z:
    	JLabel labelForY = new JLabel("Y:");
    	textFieldY = new JTextField("0", 10);
    	textFieldY.setMaximumSize(textFieldY.getPreferredSize());
    	
    	JLabel labelForZ = new JLabel("Z:");
    	textFieldZ = new JTextField("0", 10);
    	textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
    	
    	//----------------------------------------------------------------------------------
    	
    	// ������� ��������� �������� � �������������� �������� ��� ����� X,Y,Z
    	Box hboxVariables = Box.createHorizontalBox();
    	// ������ ����� ��� ������� � ������� ������ BorderFactory
    	hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
    	
    	// ��������� � ��������� ��� ��������:
    	
    	//X:
    	
    	// 1)����� C2-H1 � ��� ������������� �������� �� ������ ����
    	hboxVariables.add(Box.createHorizontalGlue());
    	// 2)������� ��� ���������� �
    	hboxVariables.add(labelForX);
    	// 3)��������� C2-H2 ������� 10 �������� ��� ������� ����� �������� � ��������� ����� ��� ����� �������� X
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	// 4)��������� ���� ��� ����� �
    	hboxVariables.add(textFieldX);
    	
    	
    	// ������v ��������� C2-H3 ������� 50 �������� ��� ������� �����
    	// ��������� ����� ��� ����� X � �������� ��� Y
    	hboxVariables.add(Box.createHorizontalStrut(50));
    	
    	//��� ���������� Y � Z ������ ��� � ��� X
    	
    	//Y:
    	hboxVariables.add(labelForY);
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	hboxVariables.add(textFieldY);
    	
    	// ������� ��������� ������� 50 �������� ��� ������� �����
    	// ��������� ����� ��� ����� Y � �������� ��� Z
    	hboxVariables.add(Box.createHorizontalStrut(50));
    	
    	//Z:
    	hboxVariables.add(labelForZ);
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	hboxVariables.add(textFieldZ);
    	
    	// �������� ����� ��� ������������� �������� �� ������� ����
    	hboxVariables.add(Box.createHorizontalGlue());
        
    	//----------------------------------------------------------------------
    	
    	//�������� � ����������� ��� ������ ����������:
    	
    	// ������� ����� ��� ������������� �������� �� ������ ����
    	hboxMemType.add(Box.createHorizontalGlue());
    	
    	// ������� �����-������ ��� ���������� 1, 2, 3 � ��������� �� � ���������
    	addRadioButton("���������� 1", 1, 1);
    	addRadioButton("���������� 2", 2, 1);
    	addRadioButton("���������� 3", 3, 1);
    	
    	// ��������� ����� ��� ������������� �������� �� ������� ����
    	hboxMemType.add(Box.createHorizontalGlue());
    	
    	//������ �������� ������ 1
    	radioButtons1.setSelected(radioButtons1.getElements().nextElement().getModel(),true);
    	 
    	// ������ ����� ��� ������� � ������� ������ BorderFactory
    	hboxMemType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    	
    	//--------------------------------------------------------------------------------
    	
    	// ������� ������� ��� ���� � �����������
    	JLabel labelForResult = new JLabel("���������:");
    	textFieldResult = new JTextField("0", 15);
    	textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
    	
    	// ������� ��������� �������� � �������������� �������� ��� ������ ����������
    	Box hboxResult = Box.createHorizontalBox();
    	
    	// ������� � ��������� ��� ��������:
    	
    	// 1)����� ��� ������������� �������� �� ������ ����
    	hboxResult.add(Box.createHorizontalGlue());    	
    	// 2)������� ��� ����������
    	hboxResult.add(labelForResult);
    	// 3)��������� � 10 �������� ����� �������� � ����� ����������
    	hboxResult.add(Box.createHorizontalStrut(10));  	
    	// 4)��������� ���� ��� ������ ����������
    	hboxResult.add(textFieldResult);
    	// 5)����� ��� ������������� �������� �� ������� ����
    	hboxResult.add(Box.createHorizontalGlue());
    	
    	// ������ ����� ��� ����������
    	hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//----------------------------------------------------------------------------
    	
    	// �������� ������: "���������", "��������", "M+", "MC"
    	
    	//1) �������� � ������� "���������"
    	
    	JButton buttonCalc = new JButton("���������");
    	// ��������� � �������������� ���������� ������� �� ������
    	buttonCalc.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    	// �������������� ��������� ����� � ����� � ��������� ������ �����
    	// �������������� �������������� �������� ��� ������������ ������� �����,
    	// ������� ��������� ���� try-catch
    	try {
    	    // ������� �������� X, Y, Z
    	    Double x = Double.parseDouble(textFieldX.getText());
    	    Double y = Double.parseDouble(textFieldY.getText());  
    	    Double z = Double.parseDouble(textFieldZ.getText());
    	    // �������� ��������� �� ������� � ����������� �� ��������������
    	    if (formulaId==1)
    	        result = calculate1(x, y, z);
    	    else if(formulaId == 2)
    	        result = calculate2(x, y, z);
    	    else 
    	    	result = 0.0;
    	    // ��������� ����� ������� "���������" ������ ����������
    	        textFieldResult.setText(result.toString());
    	    } 
    	catch (NumberFormatException ex) {
    	    // � ������ �������������� �������� �������� ���������
    	        JOptionPane.showMessageDialog(NewFrame.this, "������ � ������� ������ ����� � ��������� ������", "��������� ������ �����",JOptionPane.WARNING_MESSAGE);
    	    }
    	}
    	});
    	
    	//--------------------------------------------------------------------------------------------------------------
    	//2)�������� � ������� "�������� ����"
    	
    	JButton buttonReset = new JButton("�������� ����");
    	// ��������� � �������������� ���������� ������� �� ������
    	buttonReset.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    	    textFieldX.setText("0");
    	    textFieldY.setText("0");
    	    textFieldResult.setText("0");
    	    textFieldResultMem.setText("0");
    	    result = 0.0;
    	}
    	});
    	
    	//-------------------------------------------------------------------------------------------------------------------	
    	//3)�������� � ������� "M+"
    	JLabel labelForResultMem = new JLabel("�������� ����������:");
    	textFieldResultMem = new JTextField("0", 15);
    	textFieldResultMem.setMaximumSize(textFieldResultMem.getPreferredSize());
    	
    	JButton buttonM = new JButton("M+");
    	// ��������� � �������������� ���������� ������� �� ������
    	buttonM.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    		//������� ������������� �������� ��� ���������� 1,2,3
    	
    	    if (formulaId1==1) {
    	    	mem1 += result;
    	        textFieldResultMem.setText(mem1.toString());
    	    }
    	   
    	    else if(formulaId1 == 2) {
    	        mem2 += result;
    	        textFieldResultMem.setText(mem2.toString());
    	    }
    	    else if (formulaId1 == 3) {
    	    	mem3 += result;
    	        textFieldResultMem.setText(mem3.toString());
    	    }
    	 // ��������� ����� ������� "���������" ������ �������� ���������� result
    	}
    	});
    	
        Box hboxResultMem = Box.createHorizontalBox();
    	
    	// ������� � ��������� ��� ��������:
    	
    	// 1)����� ��� ������������� �������� �� ������ ����
    	hboxResultMem.add(Box.createHorizontalGlue());    	
    	// 2)������� ��� ����������
    	hboxResultMem.add(labelForResultMem);
    	// 3)��������� � 10 �������� ����� �������� � ����� ����������
    	hboxResultMem.add(Box.createHorizontalStrut(10));  	
    	// 4)��������� ���� ��� ������ ����������
    	hboxResultMem.add(textFieldResultMem);
    	// 5)����� ��� ������������� �������� �� ������� ����
    	hboxResultMem.add(Box.createHorizontalGlue());
    	
    	// ������ ����� ��� ����������
    	hboxResultMem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	//---------------------------------------------------------------------------------------------
    	//4)�������� � ������� "MC"
    	
    	JButton buttonMC = new JButton("MC");
    	// ��������� � �������������� ���������� ������� �� ������
    	buttonMC.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    		if (formulaId1==1) {
    	    	mem1=0.0;
    	        textFieldResultMem.setText(mem1.toString());
    	    }
    	    else if(formulaId1 == 2) {
    	        mem2=0.0;
    	        textFieldResultMem.setText(mem2.toString());
    	    }
    	    else if (formulaId1 == 3) {
    	    	mem3 =0.0;
    	        textFieldResultMem.setText(mem3.toString());
    	    }
    		formulaId1 = 0;
    	}
    	});
    	
    	//------------------------------------------------------------------------------------------------------
    	
    	// �������� ������� "��������� � �������������� ��������" ��� ������ "���������", "�������� ����", "MC", "M+"
    	
    	Box hboxButtons = Box.createHorizontalBox();
    	
    	// ��������� � ��������� ��� ��������:
    	
    	// 1) ����� ��� ������������� ������� � ����� �������
    	//hboxButtons.add(Box.createHorizontalGlue());
    	// 2)������ �����������
    	hboxButtons.add(buttonCalc);
    	// 3)��������� � 50 �������� ����� �������� "���������" � "�������� ����"
    	hboxButtons.add(Box.createHorizontalStrut(50));
    	// 4)������ ��������� �����
    	hboxButtons.add(buttonReset);
    	// 5)��������� � 50 �������� ����� �������� "�������� ����" � "�+"
    	hboxButtons.add(Box.createHorizontalGlue());
    	// 6)������ "M+"
    	hboxButtons.add(buttonM);
    	// 7)��������� � 50 �������� ����� �������� "M+" � "�C"
    	hboxButtons.add(Box.createHorizontalStrut(50));
    	// 8)������ "MC"
    	hboxButtons.add(buttonMC);
    	// 9)����� ��� ������������� ������� � ������ �������
    	hboxButtons.add(Box.createHorizontalGlue());
    	
    	// ������ ����� ��� ����������
    	hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    	
    	//--------------------------------------------------------------------------------------------------
    	
    	// ������� ������� ��������� �������� � ������������ ��������
    	
    	Box contentBox = Box.createVerticalBox();
    	
    	// ��������� � ��������� ��� ��������:
    	
    	// 1)����� ��� ������������� ������� ������
    	contentBox.add(Box.createVerticalGlue());
    	// 2)��������� � ������� �������
    	contentBox.add(hboxFormulaType);
    	// 3)��������� � ����������� X, Y, Z
    	contentBox.add(hboxVariables);
    	// 4)��������� � ����������� 1, 2, 3
    	contentBox.add(hboxMemType);
    	contentBox.add(hboxResultMem);
    	// 5)��������� � ����������� ����������
    	contentBox.add(hboxResult);
    	// 6)��������� � �������� "���������", "�������� ����", "MC", "M+"
    	contentBox.add(hboxButtons);
    	// 7)����� ��� ������������� ������� c����
    	contentBox.add(Box.createVerticalGlue());
    	
    	// ������������� ������������� ������� � ������� ���������� �������� ����
    	getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    
    
    public static void main(String[] args) {
    
    	   NewFrame frame = new  NewFrame ();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
    }
    
}

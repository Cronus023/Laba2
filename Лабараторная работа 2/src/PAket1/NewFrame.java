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
	//Размер окна
	private static final int WIDTH = 700;
	private static final int HEIGHT = 600;
	
	// Текстовые поля для X,Y,Z
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	Double mem1 = 0.0;
	Double mem2 = 0.0;
	Double mem3 = 0.0;
	// Текстовое поле для результата
	private JTextField textFieldResult;
	private JTextField textFieldResultMem;
	// Группы радио-кнопок для обеспечения уникальности выделения в группе
	private ButtonGroup radioButtons = new ButtonGroup();
	private ButtonGroup radioButtons1 = new ButtonGroup();
	
	// Контейнер для отображения радио-кнопок (для формулы 1 и 2)
	private Box hboxFormulaType = Box.createHorizontalBox();
	
	// Контейнер для отображения радио-кнопок (для переменных 1,2,3)
	private Box hboxMemType = Box.createHorizontalBox();
	
	// Идентификатор выбранной формулы
	private int formulaId = 1;
	// Идентификатор выбранной переменной 
	private int formulaId1 = 1;
    
	Double result = 0.0; // параметр отвечающий за результат вычислений
	
	//Формулы 1 и 2
	public Double calculate1(Double x, Double y, Double z) {
		return Math.pow(Math.log((1+x)*(1+x))+Math.cos(Math.PI*z*z*z),Math.cos(y))+Math.pow(Math.pow(Math.E, x*x)+Math.cos(Math.pow(Math.E, z))+Math.sqrt(1/y), 1/x);
	}
	public Double calculate2(Double x, Double y,  Double z) {
		return y*x*x/(Math.log(Math.pow(z, y))+ Math.pow(Math.cos(Math.cbrt(x)),2));
	}
		
	//Вспомогательный метод для радио-кнопок
	private void addRadioButton(String buttonName, final int formulaId,int flag) {
		// Создаем экземпляр радио-кнопки с заданным текстом
		JRadioButton button = new JRadioButton(buttonName);
		
		// Определяем и регестрируем обработчик,
		//который будет устанавливать идентификатор выбран формулы равным formulaId или formulaId1
		if (flag == 0) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				NewFrame.this.formulaId = formulaId;
			}
			});	
			radioButtons.add(button);
			// Добавляем радио-кнопку в контейнер
			// Для этого ссылка на контейнер сделана полем данных класса
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
			// Добавить радио-кнопку в контейнер
			// Для этого ссылка на контейнер сделана полем данных класса
			hboxMemType.add(button);
		}
    }
	
    NewFrame (){
    	super("Вычисление");
    	setSize(WIDTH, HEIGHT);
    	
    	// Располагаем окно приложения по центру
    	Toolkit kit = Toolkit.getDefaultToolkit();
    	setLocation((kit.getScreenSize().width - WIDTH)/2,
    	(kit.getScreenSize().height - HEIGHT)/2);
    	
    	//------------------------------------------------------------------------
    	
    	//Работаем с контейнером по выбору формулы:

    	// Добавим «клей» для максимального удаления от левого края
    	hboxFormulaType.add(Box.createHorizontalGlue());
    	
    	// Создаем радио-кнопки для формулы 1 и 2 и добавляем их в контейнер
    	addRadioButton("Формула 1", 1, 0);
    	addRadioButton("Формула 2", 2, 0);
    	
    	// Устанавливаем выделенной 1-ую кнопку из группы "Формула"
    	radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(),true);
    	
    	// Добавим «клей» для максимального удаления от правого края
    	hboxFormulaType.add(Box.createHorizontalGlue());
    	
    	// Задаем рамку для коробки с помощью класса BorderFactory 
    	hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	//------------------------------------------------------------------------------
    	//Создадим надписи для переменных X, Y, Z:
    	
    	// X:
    	JLabel labelForX = new JLabel("X:");
    	
    	// Создаем текстовое поле для ввода значения переменной X,
    	// (по умолчанию значение поле - 0)
    	textFieldX = new JTextField("0", 10);
    	
    	// Установливаем максимальный размер равным желаемому для предотвращения масштабирования
    	textFieldX.setMaximumSize(textFieldX.getPreferredSize());
    	
    	// Аналогично для Y и Z:
    	JLabel labelForY = new JLabel("Y:");
    	textFieldY = new JTextField("0", 10);
    	textFieldY.setMaximumSize(textFieldY.getPreferredSize());
    	
    	JLabel labelForZ = new JLabel("Z:");
    	textFieldZ = new JTextField("0", 10);
    	textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
    	
    	//----------------------------------------------------------------------------------
    	
    	// Создаем контейнер «коробка с горизонтальной укладкой» для ввода X,Y,Z
    	Box hboxVariables = Box.createHorizontalBox();
    	// Задать рамку для коробки с помощью класса BorderFactory
    	hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
    	
    	// Добавляем в контейнер ряд объектов:
    	
    	//X:
    	
    	// 1)«клей» C2-H1 – для максимального удаления от левого края
    	hboxVariables.add(Box.createHorizontalGlue());
    	// 2)подпись для переменной Х
    	hboxVariables.add(labelForX);
    	// 3)«распорка» C2-H2 шириной 10 пикселов для отступа между надписью и текстовым полем для ввода значения X
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	// 4)текстовое поле для ввода Х
    	hboxVariables.add(textFieldX);
    	
    	
    	// Добавиv «распорку» C2-H3 шириной 50 пикселов для отступа между
    	// текстовым полем для ввода X и подписью для Y
    	hboxVariables.add(Box.createHorizontalStrut(50));
    	
    	//Для переменных Y и Z делаем как и для X
    	
    	//Y:
    	hboxVariables.add(labelForY);
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	hboxVariables.add(textFieldY);
    	
    	// Добавим «распорку» шириной 50 пикселов для отступа между
    	// текстовым полем для ввода Y и подписью для Z
    	hboxVariables.add(Box.createHorizontalStrut(50));
    	
    	//Z:
    	hboxVariables.add(labelForZ);
    	hboxVariables.add(Box.createHorizontalStrut(10));
    	hboxVariables.add(textFieldZ);
    	
    	// Добавить «клей» для максимального удаления от правого края
    	hboxVariables.add(Box.createHorizontalGlue());
        
    	//----------------------------------------------------------------------
    	
    	//Работаем с контейнером для выбора переменных:
    	
    	// Добавим «клей» для максимального удаления от левого края
    	hboxMemType.add(Box.createHorizontalGlue());
    	
    	// Создаем радио-кнопки для переменных 1, 2, 3 и добавляем их в контейнер
    	addRadioButton("Переменная 1", 1, 1);
    	addRadioButton("Переменная 2", 2, 1);
    	addRadioButton("Переменная 3", 3, 1);
    	
    	// Добавляем «клей» для максимального удаления от правого края
    	hboxMemType.add(Box.createHorizontalGlue());
    	
    	//Делаем активной кнопку 1
    	radioButtons1.setSelected(radioButtons1.getElements().nextElement().getModel(),true);
    	 
    	// Задаем рамку для коробки с помощью класса BorderFactory
    	hboxMemType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    	
    	//--------------------------------------------------------------------------------
    	
    	// Создаем подпись для поля с результатом
    	JLabel labelForResult = new JLabel("Результат:");
    	textFieldResult = new JTextField("0", 15);
    	textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
    	
    	// Создаем контейнер «коробка с горизонтальной укладкой» для вывода результата
    	Box hboxResult = Box.createHorizontalBox();
    	
    	// Добавим в контейнер ряд объектов:
    	
    	// 1)«клей» для максимального удаления от левого края
    	hboxResult.add(Box.createHorizontalGlue());    	
    	// 2)подпись для результата
    	hboxResult.add(labelForResult);
    	// 3)«распорка» в 10 пикселов между подписью и полем результата
    	hboxResult.add(Box.createHorizontalStrut(10));  	
    	// 4)текстовое поле для вывода результата
    	hboxResult.add(textFieldResult);
    	// 5)«клей» для максимального удаления от правого края
    	hboxResult.add(Box.createHorizontalGlue());
    	
    	// Задаем рамку для контейнера
    	hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//----------------------------------------------------------------------------
    	
    	// Создадим кнопки: "Вычислить", "Очистить", "M+", "MC"
    	
    	//1) Работаем с кнопкой "Вычислить"
    	
    	JButton buttonCalc = new JButton("Вычислить");
    	// Определим и зарегистрируем обработчик нажатия на кнопку
    	buttonCalc.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    	// Преобразование введенных строк в числа с плавающей точкой может
    	// спровоцировать исключительную ситуацию при неправильном формате чисел,
    	// поэтому необходим блок try-catch
    	try {
    	    // Получим значение X, Y, Z
    	    Double x = Double.parseDouble(textFieldX.getText());
    	    Double y = Double.parseDouble(textFieldY.getText());  
    	    Double z = Double.parseDouble(textFieldZ.getText());
    	    // Вычислим результат по формуле в зависимости от идентификатора
    	    if (formulaId==1)
    	        result = calculate1(x, y, z);
    	    else if(formulaId == 2)
    	        result = calculate2(x, y, z);
    	    else 
    	    	result = 0.0;
    	    // Установим текст надписи "Результат" равным результату
    	        textFieldResult.setText(result.toString());
    	    } 
    	catch (NumberFormatException ex) {
    	    // В случае исключительной ситуации показать сообщение
    	        JOptionPane.showMessageDialog(NewFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",JOptionPane.WARNING_MESSAGE);
    	    }
    	}
    	});
    	
    	//--------------------------------------------------------------------------------------------------------------
    	//2)Работаем с кнопкой "Очистить поля"
    	
    	JButton buttonReset = new JButton("Очистить поля");
    	// Определим и зарегистрируем обработчик нажатия на кнопку
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
    	//3)Работаем с кнопкой "M+"
    	JLabel labelForResultMem = new JLabel("Значение переменной:");
    	textFieldResultMem = new JTextField("0", 15);
    	textFieldResultMem.setMaximumSize(textFieldResultMem.getPreferredSize());
    	
    	JButton buttonM = new JButton("M+");
    	// Определим и зарегистрируем обработчик нажатия на кнопку
    	buttonM.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ev) {
    		//Зададим фиксированные значения для переменных 1,2,3
    	
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
    	 // Установим текст надписи "Результат" равным значению переменной result
    	}
    	});
    	
        Box hboxResultMem = Box.createHorizontalBox();
    	
    	// Добавим в контейнер ряд объектов:
    	
    	// 1)«клей» для максимального удаления от левого края
    	hboxResultMem.add(Box.createHorizontalGlue());    	
    	// 2)подпись для результата
    	hboxResultMem.add(labelForResultMem);
    	// 3)«распорка» в 10 пикселов между подписью и полем результата
    	hboxResultMem.add(Box.createHorizontalStrut(10));  	
    	// 4)текстовое поле для вывода результата
    	hboxResultMem.add(textFieldResultMem);
    	// 5)«клей» для максимального удаления от правого края
    	hboxResultMem.add(Box.createHorizontalGlue());
    	
    	// Задаем рамку для контейнера
    	hboxResultMem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	//---------------------------------------------------------------------------------------------
    	//4)Работаем с кнопкой "MC"
    	
    	JButton buttonMC = new JButton("MC");
    	// Определим и зарегистрируем обработчик нажатия на кнопку
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
    	
    	// Создадим коробку "контейнер с горизонтальной укладкой" для кнопок "Вычислить", "Очистить поля", "MC", "M+"
    	
    	Box hboxButtons = Box.createHorizontalBox();
    	
    	// Добавляем в контейнер ряд объектов:
    	
    	// 1) «клей» для максимального отступа с левой стороны
    	//hboxButtons.add(Box.createHorizontalGlue());
    	// 2)кнопка «Вычислить»
    	hboxButtons.add(buttonCalc);
    	// 3)«распорка» в 50 пикселов между кнопками "Вычислить" и "Очистить поля"
    	hboxButtons.add(Box.createHorizontalStrut(50));
    	// 4)кнопка «Очистить поля»
    	hboxButtons.add(buttonReset);
    	// 5)«распорка» в 50 пикселов между кнопками "Очистить поля" и "М+"
    	hboxButtons.add(Box.createHorizontalGlue());
    	// 6)кнопка "M+"
    	hboxButtons.add(buttonM);
    	// 7)«распорка» в 50 пикселов между кнопками "M+" и "МC"
    	hboxButtons.add(Box.createHorizontalStrut(50));
    	// 8)кнопка "MC"
    	hboxButtons.add(buttonMC);
    	// 9)«клей» для максимального отступа с правой стороны
    	hboxButtons.add(Box.createHorizontalGlue());
    	
    	// Задать рамку для контейнера
    	hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    	
    	//--------------------------------------------------------------------------------------------------
    	
    	// Создаем главный контейнер «коробка с вертикальной укладкой»
    	
    	Box contentBox = Box.createVerticalBox();
    	
    	// Добавляем в контейнер ряд объектов:
    	
    	// 1)«клей» для максимального отступа сверху
    	contentBox.add(Box.createVerticalGlue());
    	// 2)контейнер с выбором формулы
    	contentBox.add(hboxFormulaType);
    	// 3)контейнер с переменными X, Y, Z
    	contentBox.add(hboxVariables);
    	// 4)контейнер с переменными 1, 2, 3
    	contentBox.add(hboxMemType);
    	contentBox.add(hboxResultMem);
    	// 5)контейнер с результатом вычислений
    	contentBox.add(hboxResult);
    	// 6)контейнер с кнопками "Вычислить", "Очистить поля", "MC", "M+"
    	contentBox.add(hboxButtons);
    	// 7)«клей» для максимального отступа cнизу
    	contentBox.add(Box.createVerticalGlue());
    	
    	// Установливаем «вертикальную коробку» в область содержания главного окна
    	getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    
    
    public static void main(String[] args) {
    
    	   NewFrame frame = new  NewFrame ();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
    }
    
}

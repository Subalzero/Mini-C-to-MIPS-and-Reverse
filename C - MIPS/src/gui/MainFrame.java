/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.*;
import net.miginfocom.swing.*;
import java.io.*;

public class MainFrame extends JFrame {
    
    /**
	 * Declarations
	 */
	private static final long serialVersionUID = 1L;
	private mipstoc.read.CodeReader assemblyreader;
    private String filename;
    private TextLineNumber inputTxtNum;
    private TextLineNumber outputTxtNum;
    private File codeFile;
    private int switchBtnState = -1;
    public static JPanel panel;
    private JLabel fileLbl;
    private JTextField fileTxtField;
    private JButton choosefileBtn;
    private JLabel inputLbl;
    private JLabel outputLbl;
    private JScrollPane inputScrlPane;
    private JScrollPane outputScrlPane;
    private JTextArea inputTxtArea;
    private JTextArea outputTxtArea;
    private JButton translateBtn;
    private JToggleButton switchBtn;
    
    public MainFrame() {
        initComponents();
    }
    
    /**
     * Initialize Components
     */
    private void initComponents() {
        
        filename = "";
        
        panel = new JPanel();
        fileLbl = new JLabel("input file: ");
        fileTxtField = new JTextField();
        choosefileBtn = new JButton("choose file");
        inputLbl = new JLabel("input: ");
        outputLbl = new JLabel("output: ");
        inputTxtArea = new JTextArea();
        outputTxtArea = new JTextArea();
        inputScrlPane = new JScrollPane(inputTxtArea);
        outputScrlPane = new JScrollPane(outputTxtArea);
        inputTxtNum = new TextLineNumber(inputTxtArea);
        outputTxtNum = new TextLineNumber(outputTxtArea);
        translateBtn = new JButton("translate");
        switchBtn = new JToggleButton("C -> MIPS");
        
        fileTxtField.setEditable(false);
        
        inputTxtArea.setEditable(true);
        inputTxtArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        
        outputTxtArea.setEditable(false);
        outputTxtArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        
        inputScrlPane.setRowHeaderView(inputTxtNum);
        outputScrlPane.setRowHeaderView(outputTxtNum);
        
        switchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                switchBtnActionPerformed(evt);
            }
        });
        
        choosefileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                choosefileBtnActionPerformed(evt);
            }
        });
        
        translateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                translateBtnActionPerformed(evt);
            }
        });
        
        
        panel.setLayout(new MigLayout(
        	"",
        	"[][][]",
        	"[][::45%][::45%]"
        ));
        panel.add(fileLbl);
        panel.add(fileTxtField, "pushx, growx");
        panel.add(choosefileBtn, "wrap");
        panel.add(inputLbl, "top");
        panel.add(inputScrlPane, "push, grow");
        panel.add(switchBtn, "wrap");
        panel.add(outputLbl, "top");
        panel.add(outputScrlPane, "push, grow");
        panel.add(translateBtn, "wrap");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 550);
        this.setTitle("C - MIPS");
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.add(panel);
    }
    
    /**
     * When the toggle button is clicked, this code
     * executes.
     */
    private void switchBtnActionPerformed(ActionEvent evt) {
        if(switchBtn.isSelected()) {
            switchBtn.setText("MIPS -> C");
            switchBtnState = 1;
        }
        else {
            switchBtn.setText("C -> MIPS");
            switchBtnState = -1;
        }
    }
    
    /**
     * When 'choose file' button is clicked, this code
     * executes.
     */
    private void choosefileBtnActionPerformed(ActionEvent evt) {
        JFileChooser filechooser = new JFileChooser();
        int returnValue = filechooser.showOpenDialog(panel);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            filename = filechooser.getSelectedFile().toString();
            fileTxtField.setText(filename);
            codeFile = filechooser.getSelectedFile();
            assemblyreader = new mipstoc.read.CodeReader(codeFile);
            inputTxtArea.setText(assemblyreader.getString());
        }
        
    }
    
    /**
     * When the translate button is clicked, this code
     * executes.
     */
    private void translateBtnActionPerformed(ActionEvent evt) {
        if(switchBtnState == 1) {
            assemblyreader = new mipstoc.read.CodeReader(inputTxtArea.getText());
            mipstoc.lexer.Lexer lex = new mipstoc.lexer.Lexer(assemblyreader);
            mipstoc.parser.Parser parser = new mipstoc.parser.Parser(lex);
            try {
                parser.program();
                outputTxtArea.setText(mipstoc.parser.Parser.output);
                mipstoc.parser.Parser.output = "";
            }
            catch(Error e) {
            	Toolkit.getDefaultToolkit().beep();
            	JOptionPane.showMessageDialog(panel, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
        	try {
        		ctomips.reader.ReadFile reader = new ctomips.reader.ReadFile(inputTxtArea.getText());
            	ctomips.lexer.LexInter li = new ctomips.lexer.LexInter();
            	ctomips.asm.Assembly ass = new ctomips.asm.Assembly(li);
            	ctomips.lexer.Lexer lex = new ctomips.lexer.Lexer(reader);
            	ctomips.parser.Parser parser = new ctomips.parser.Parser(lex, ass);
            	ctomips.inter.Node.labels = 0;
            	ctomips.lexer.Lexer.line = 1;
            	parser.program();
            	ass.program();
            	outputTxtArea.setText(ass.assembStr);
            	ctomips.parser.Parser.output = "";
        	}
        	catch(IOException ie) {
        		
        	}
        	catch(Error e) {
        		Toolkit.getDefaultToolkit().beep();
        		JOptionPane.showMessageDialog(panel, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
 
    public static void main(String[] args) {
    	try {
    		
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}
    	catch(Exception e) { e.printStackTrace();}
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

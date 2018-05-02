package Frame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.io.BufferedReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import I_O.Read;

public class Form {

	BufferedReader br = null;
	JFrame f = new JFrame("KAppembler");
	Read r = new Read();
	JTextArea input = new JTextArea(10, 60);
	JTextArea output = new JTextArea(10, 60);
	JTextArea instr = new JTextArea(20, 20);
	JScrollPane instrScroll = new JScrollPane(instr);
	JScrollPane inputScroll = new JScrollPane(input);
	JScrollPane outputScroll = new JScrollPane(output);
	// JTextArea errors=new JTextArea(6,100);
	JButton submit = new JButton("submit");
	JButton export = new JButton("export");
	JButton save = new JButton("Save Assembly");
	JButton imp = new JButton("Import Assembly");
	Listeners listener = new Listeners(input, output);
	Label in = new Label("instructions:");
	ExportListener el = new ExportListener(output, 0);
	ExportListener sl = new ExportListener(input, 1);
	importListener il = new importListener(input);
	BorderLayout b = new BorderLayout();
	GridBagLayout g = new GridBagLayout();

	public void visualizza(String FileName) {
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(g);
		f.setResizable(false);
		output.setFocusable(false);

		JPanel p1 = new JPanel();
		JPanel IO = new JPanel();
		JPanel Buttons = new JPanel();
		JPanel Ins = new JPanel();
		IO.setLayout(g);
		Buttons.setLayout(g);
		Ins.setLayout(g);
		GridBagConstraints constraints = new GridBagConstraints();
		GridBagConstraints Bcon = new GridBagConstraints();
		GridBagConstraints Icon = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		Bcon.anchor = GridBagConstraints.NORTH;
		Bcon.insets = new Insets(10, 10, 10, 10);
		Bcon.gridx = 0;
		Bcon.gridy = 0;
		Icon.anchor = GridBagConstraints.NORTH;
		Icon.insets = new Insets(10, 10, 10, 10);
		Icon.gridx = 0;
		Icon.gridy = 0;
		inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		inputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		input.setText("\tLD A,B");
		output.setEditable(false);
		instr.setEditable(false);
		submit.addActionListener(listener);
		export.addActionListener(el);
		save.addActionListener(sl);
		imp.addActionListener(il);
		IO.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "I/O Panel"));
		IO.add(inputScroll, constraints);
		constraints.gridy = 10;
		IO.add(outputScroll, constraints);
		p1.add(IO);
		Buttons.add(submit, Bcon);
		Bcon.gridy = 1;
		Buttons.add(export, Bcon);
		Bcon.gridy = 2;
		Buttons.add(save, Bcon);
		Bcon.gridy = 3;
		Buttons.add(imp, Bcon);
		p1.add(Buttons);
		Ins.add(in, Icon);
		Icon.gridy = 1;
		Ins.add(instrScroll, Icon);
		p1.add(Ins);
		instr.setText(r.readfilePass(FileName)
				.substring(r.readfilePass(FileName).indexOf("___", r.readfilePass(FileName).indexOf("___") + 1) + 3));
		f.add(p1);
		f.pack();
		f.setVisible(true);

	}

	public String GetInput() {
		String s = "";
		s = input.getText();
		return s;
	}
}

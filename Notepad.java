package notepad;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import say.swing.JFontChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.event.ChangeEvent;
public class Notepad {

	private JFrame frame;
	int filetoopen;
	int filetosave;
	JFileChooser fileopen;
	JFileChooser filesave;
	private Dimension dim;
	private int width,height,neww,newh,difw,difh;
	private String s1,s2="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notepad window = new Notepad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Notepad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 461, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Untitled -Notepad");
		Image im=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Student\\Documents\\notepad.png");
		//toolkit class is abstract superclass of every implementation in abstract window toolkit.it binds various components
		frame.setIconImage(im);
		frame.getContentPane().setLayout(null);
		width=frame.getWidth();
		height=frame.getHeight();
		dim=new Dimension(width, height);
		frame.setMinimumSize(dim);
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(SystemColor.inactiveCaptionBorder);
		mb.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mb.setBounds(0, 0, 445, 30);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		JTextArea ta = new JTextArea();
				
		JScrollPane js=new JScrollPane(ta);
		//js.setBounds(0, 30, 1365, 676);
		js.setBounds(0, 30, 445, 250);
	js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(js);
		//Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setSize(size.width, size.height);
		//frame.pack();
		JMenu file=new JMenu("File");
		JMenu edit=new JMenu("Edit");
		JMenu format=new JMenu("Format");
		JMenu view=new JMenu("View");
		JMenu help=new JMenu("Help");
	
		
		JMenuItem ne=new JMenuItem("New");
		ne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data=ta.getText().trim();
				if((!data.equals("")) && frame.getTitle().equals("Untitled -Notepad"))
				{
				int a=JOptionPane.showConfirmDialog(frame, "Do you want to save changes to untitled?");
				if(a==JOptionPane.NO_OPTION)
					{
					ta.setText("");	
				frame.setTitle("Untitled -Notepad");
				}
				else if(frame.getTitle().equals("Untitled -Notepad") && a==JOptionPane.YES_OPTION) 
		{
					FileDialog fd=new FileDialog(frame, "Save File");
					fd.setVisible(true);
					String fold=fd.getDirectory()+fd.getFile();
											try {
						FileWriter fw=new FileWriter(fold);
						String data1=ta.getText();
						fw.write(data1);
						fw.close();
						frame.setTitle("Untitled -Notepad");
					}
					catch(Exception es){
						System.out.println(e.getClass());}
						ta.setText("");
						}
				else if( !frame.getTitle().equals("Untitled -Notepad")) {
					int b=JOptionPane.showConfirmDialog(frame, "Do you want to save changes to the file ?");
					if( b==JOptionPane.YES_OPTION) {
						FileDialog fd=new FileDialog(frame, "Save File");
						fd.setVisible(true);
						String fold=fd.getDirectory()+fd.getFile();
												try {
							FileWriter fw=new FileWriter(fold);
							String data1=ta.getText();
							fw.write(data1);
							fw.close();
							frame.setTitle("Untitled -Notepad");
							ta.setText("");
						}
						catch(Exception es){
							System.out.println(e.getClass());}
						
					}
					}
				}}
		});
			
		//ne.setAccelerator(KeyStroke.getKeyStroke("N"));
		ne.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem open=new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fg=new FileDialog(frame, "open a file");
				fg.setVisible(true);
				/**JFileChooser jc=new JFileChooser();
				int i=jc.showOpenDialog(open);
				if(i==FileChooser.APPROVE_OPTION)
				{
					File f=jc.getSelectedFile();
					String fpath=f.getPath();
					try {
						BufferedReader br=new BufferedReader(new FileReader(fpath));
						String s1="",s2="";
						while((s1=br.readLine())!=null) {
							s2+=s1+"\n";}
								ta.setText(s2);
								br.close(); 
					}
					catch(Exception ex){
						ex.printStackTrace();
									}
			}}*/
				if(fg.getFile()!=null) {
					
					String fpath=fg.getDirectory()+fg.getFile();
					try {
						BufferedReader br=new BufferedReader(new FileReader(fpath));
						String s1="",s2="";
						while((s1=br.readLine())!=null) {
							s2+=s1+"\n";}
								ta.setText(s2);
								br.close(); 
								frame.setTitle(fg.getFile()+"-Notepad");
					}
					catch(Exception ex){
						ex.printStackTrace();
									}
				}
				}
		});
		open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem save=new JMenuItem("Save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String str=ta.getText();
				if(str.equals("") && frame.getTitle().equals("Untitled -Notepad"))
				{
					save.setEnabled(false);
				}
				else
					save.setEnabled(true);
					
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								FileDialog fd=new FileDialog(frame, "Save File");
				fd.setVisible(true);
				String fold=fd.getDirectory()+fd.getFile();
			File tmp=new File(fold);
			if(tmp.exists()) {
				int a=JOptionPane.showConfirmDialog(frame,""+fd.getFile()+"  already exists.\n  Do you want to replace it ?");
				if(a==JOptionPane.YES_OPTION) {
							try {
					FileWriter fw=new FileWriter(fold);
					String data=ta.getText();
					fw.write(data);
					fw.close();
					frame.setTitle(fd.getFile()+"-Notepad");
				}
				catch(Exception es){
					System.out.println(e.getClass());
													}}}
			else
			{
				try {
					FileWriter fw=new FileWriter(fold);
					String data=ta.getText();
					fw.write(data);
					fw.close();
					frame.setTitle(fd.getFile()+"-Notepad");
				}
				catch(Exception es){
					System.out.println(e.getClass());
													}
			}
				
			}
		});
		save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem saveAs=new JMenuItem("SaveAs       ");
		saveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String str=ta.getText();
				if(str.equals("") && frame.getTitle().equals("Untitled -Notepad"))
				{
					saveAs.setEnabled(false);
				}
				else
					saveAs.setEnabled(true);
			}
		});
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd=new FileDialog(frame, "Save File");
				fd.setVisible(true);
				String fold=fd.getDirectory()+fd.getFile();
				File tmp=new File(fold);
				if(tmp.exists()) {
								int a=JOptionPane.showConfirmDialog(frame,""+fd.getFile()+"already exists.\n Do you want to replace it ?");
			if(a==JOptionPane.YES_OPTION) {
							
				try {
					FileWriter fw=new FileWriter(fold);
					String data=ta.getText();
					fw.write(data);
					fw.close();
					frame.setTitle(fd.getFile()+"-Notepad");
				}
				catch(Exception es){
					System.out.println(e.getClass());
				
					}
							}}
									
			}
		});
		JMenuItem ps=new JMenuItem("Pagesetup      ");
		JMenuItem print=new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem ex=new JMenuItem("Exit");
		ex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame.getTitle().equals("Untitled -Notepad"))
				{
					int a=JOptionPane.showConfirmDialog(frame, "Do you want to save changes to Untitled ? ");
					if( a==JOptionPane.YES_OPTION) {
						FileDialog fd=new FileDialog(frame, "Save File");
						fd.setVisible(true);
						String fold=fd.getDirectory()+fd.getFile();
						File tmp=new File(fold);
						if(tmp.exists()) {
							
					int b=JOptionPane.showConfirmDialog(frame,""+fd.getFile()+"already exists.\nDo you want to replace it ?");
					if(b==JOptionPane.YES_OPTION) {
									
						try {
							FileWriter fw=new FileWriter(fold);
							String data=ta.getText();
							fw.write(data);
							fw.close();
							frame.setTitle(fd.getFile()+"-Notepad");
						}
						catch(Exception es){
							System.out.println(e.getClass());
						
							}
									}}}
					else
					{
						System.exit(0);
						
					}
					
				}
				else
				{
					int a=JOptionPane.showConfirmDialog(frame, "Do you want to save changes to  the file ? ");
											if( a==JOptionPane.YES_OPTION) {
					FileDialog fd=new FileDialog(frame, "Save File");
					fd.setVisible(true);
					String fold=fd.getDirectory()+fd.getFile();
					File tmp=new File(fold);
					if(tmp.exists()) {
										int b=JOptionPane.showConfirmDialog(frame,""+fd.getFile()+"already exists.\nDo you want to replace it ?");
				if(b==JOptionPane.YES_OPTION) {
								
					try {
						FileWriter fw=new FileWriter(fold);
						String data=ta.getText();
						fw.write(data);
						fw.close();
						frame.setTitle(fd.getFile()+"-Notepad");
					}
					catch(Exception es){
						System.out.println(e.getClass());
					
						}
								}}}
				else
				{
					System.exit(0);
					
				}	
				}
			}
		});
		file.add(ne); file.add(open);	file.add(save); 	file.add(saveAs); 	file.add(ps); 	file.add(print);
		file.add(ex);
		
		UndoManager undo=new UndoManager();
		ta.getDocument().addUndoableEditListener(new UndoableEditListener() {
						@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undo.addEdit(e.getEdit());
				
			}
		});
				JMenuItem un=new JMenuItem("Undo");
		un.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					undo.undo();
									}
				catch(CannotUndoException c)
				{
					System.out.println(c);
				}
			}
		});
	
		un.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem cut=new JMenuItem("Cut            ");
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ta.getSelectedText()!=null) {
								ta.cut();}
				
					
			}
		});
		cut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(ta.getSelectedText()!=null)
				{
					cut.setEnabled(true);
				}
				else
				{
					cut.setEnabled(false);
				}
			}
		});
			
		cut.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem copy=new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ta.getSelectedText()!=null)
				{
					ta.copy();
				}
			}
		});
		copy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(ta.getSelectedText()!=null)
				{
					copy.setEnabled(true);
				}
				else
				{
					copy.setEnabled(false);
				}
			}
		});
		copy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem paste=new JMenuItem("Paste   ");
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta.paste();
			}
		});
		paste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem delete=new JMenuItem("Delete  ");
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ta.getSelectedText()!=null) {
								ta.cut();}
				
					
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(ta.getSelectedText()!=null)
				{
					delete.setEnabled(true);
				}
				else
				{
					delete.setEnabled(false);
				}
			}
		});
		JMenuItem find=new JMenuItem("Find ");  
		find.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem findn=new JMenuItem("Find Next     ");
		JMenuItem rplce=new JMenuItem("Replace    ");
		rplce.setAccelerator(KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem go=new JMenuItem("Goto");
		go.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem slctall=new JMenuItem("SelectAll    ");
		slctall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta.selectAll();
			}
		});
		slctall.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		JMenuItem date=new JMenuItem("Time/Date ");
		date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar date=new GregorianCalendar();
				String data=date.get(Calendar.DAY_OF_MONTH)+"/"+ date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR)+"  "+
				date.get(Calendar.HOUR)+":"+date.get(Calendar.MINUTE)+":"+date.get(Calendar.SECOND);
				ta.append(data);
			}
		});
		date.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
		edit.add(un); edit.add(cut); edit.add(copy); edit.add(paste);  edit.add(delete);  edit.add(find); edit.add(findn);
		edit.add(rplce); edit.add(go);  edit.add(slctall);edit.add(date);   
		JCheckBoxMenuItem  word=new JCheckBoxMenuItem("Word wrap      ");
				word.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(word.getState()) {
					js.setHorizontalScrollBar(null);
								ta.setLineWrap(true);}
				else
					ta.setLineWrap(false);
			}
		});
		JMenuItem font=new JMenuItem("Font  ");
		font.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFontChooser  fc=new JFontChooser();
JOptionPane.showMessageDialog(frame,fc,"choose a color" ,JOptionPane.PLAIN_MESSAGE);
Font f=fc.getSelectedFont();
ta.setFont(f);
			}
		});
		JMenuItem clr=new JMenuItem("Color  ");
		clr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tmp=JColorChooser.showDialog(frame, "select a color", Color.cyan);
							ta.setForeground(tmp);
			}
		});
		format.add(word); format.add(font);format.add(clr);
		
		
		JCheckBoxMenuItem stts=new JCheckBoxMenuItem("Status Bar      ");
		view.add(stts);
		JMenuItem vh=new JMenuItem("view Help  ");
		JMenuItem an=new JMenuItem("About Notepad      ");
		help.add(vh); help.add(an);
		
		mb.add(file);mb.add(edit);mb.add(format); mb.add(view); mb.add(help); 
				frame.getContentPane().add(mb);
				
				
				
				
				frame.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						neww=frame.getWidth();
						newh=frame.getHeight();
						difw=neww-width;
						difh=newh-height;
						ta.setSize(difw+ta.getWidth(),difh+ta.getHeight());
						js.setSize(difw+js.getWidth(),difh+js.getHeight());
					mb.setSize(difw+mb.getWidth(),mb.getHeight());
						width=neww;
						height=newh;
						
					}
				});
		
		
		
		
	}
}

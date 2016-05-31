package frame;

/**
 * 1���˵����
    	JMenuBar	�˵������	����
    	JMenu		�˵����	��֦
    	JMenuItem	�˵������	��Ҷ
	2�������˵�����
    	JMenu�������Ƕ��JMenu
	3�����������
    	JToolBar	���������
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.html.ImageView;

import com.sun.awt.AWTUtilities;

import dao.DeptDao;
import dao.JobDao;
import dao.StaffDao;
import entity.Dept;
import entity.Job;
import entity.staff;
public class MainFrame extends JFrame{
	//�ļ��鶨�����
	JMenuBar jmb;//�˵������
	JMenu menu1,menu2,menu3,menu4,menu5;//���˵����ļ����༭����ʽ���鿴������
	JMenuItem item2,item3,item4,item5,item6,item7;//�Ӳ˵����½����򿪡����桢���Ϊ��ҳ�����á���ӡ���˳�
	JMenu xinjian;//�����˵�
	JMenuItem file,project;
	
	//�༭�鶨�����
	//�༭�����������С����ơ�ճ����ɾ�������ҡ�������һ�����滻��ת����ȫѡ��ʱ��/����
	JMenuItem eitem1,eitem2,eitem3,eitem4,eitem5,eitem6,eitem7,eitem8,eitem9,eitem10,eitem11;
	
	//��ʽ�鶨�����
	JMenuItem oitem1,oitem2;//��ʽ���Զ����С�����
	
	//�鿴�鶨�����
	JMenuItem vitem1;//�鿴��״̬��
	
	//�����鶨�����
	JMenuItem hitem1,hitem2;//�������鿴���������ڼ��±�
	
	JTextArea jta;
	//������
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8;
	private JTable tab;
	private DefaultTableModel dtm;
	private List staffList;
	private Object[][]rowData;
	private List<Dept> deptList;
	private List<Job> jobList;
	private JLabel titlePicture;
	private GridBagLayout layout;
	FrameListener frameListener;
	JScrollPane jsp;
	
	//���캯��
	public MainFrame(){
		//�������
		//������
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
		this.setIconImage(imageIcon.getImage());
		//AWTUtilities.setWindowOpaque(this, false);
		this.setUndecorated(true);
		this.setLayout(layout = new GridBagLayout());
		
		init();
		addControls();
		setLayout();
		addListener();

		frameListener = new FrameListener(this);
		addMouseListener(frameListener);
		addMouseMotionListener(frameListener);  
		
		//��������
		this.setTitle("��ϢѧԺѧ����֧�����¹���ϵͳ");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		}
		public int setRowData(){
			int row;
			StaffDao dao=new StaffDao();
			staffList=dao.getList();
			row=staffList.size();
			rowData=new Object[row][6];
			for(int i=0;i<row;i++){
				rowData[i][0]=i+1;
				staff s=(staff)staffList.get(i);
				rowData[i][1]=s.getStaffId();
				rowData[i][2]=s.getStaffName();
				rowData[i][3]=s.getDept().getDeptName();
				rowData[i][4]=s.getJob().getJobName();
				rowData[i][5]=s.getDetail();
			}
			return row;
		}
		public void init(){
			titlePicture = new JLabel();
			jtb=new JToolBar();
			jb1=new JButton("���Ա��");
			jb1.setToolTipText("�½�");//������ʾ��Ϣ
			jb2=new JButton("ɾ��Ա��");
			jb2.setToolTipText("��");
			jb3=new JButton("�༭Ա��");
			jb3.setToolTipText("����");
			jb4=new JButton(" ˢ �� ");
			jb4.setToolTipText("����");
			jb5=new JButton(" �� �� ");
			jb5.setToolTipText("ɾ��");
			jb6=new JButton(new ImageIcon("/image/4.png"));
			jb6.setToolTipText("�༭");
			jb7=new JButton(new ImageIcon("/image/4.png"));
			jb7.setToolTipText("��ӡ");
			jb8=new JButton(new ImageIcon("/image/4.png"));
			jb8.setToolTipText("�ر�");
			
			jmb=new JMenuBar();
			//���˵�
			menu1=new JMenu("�ļ�(F)");
			menu1.setMnemonic('F');//�������Ƿ�
			menu2=new JMenu("�༭(E)");
			menu2.setMnemonic('E');
			menu3=new JMenu("��ʽ(O)");
			menu3.setMnemonic('O');
			menu4=new JMenu("�鿴(V)");
			menu4.setMnemonic('V');
			menu5=new JMenu("����(H)");
			menu5.setMnemonic('H');
			
			//�ļ�--�½�--��Ŀ¼
			xinjian=new JMenu("�½�");
			file=new JMenuItem("�ļ�");
			project=new JMenuItem("����");
			
			item2=new JMenuItem("��(O)");
			item2.setMnemonic('O');
			item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));//����ctrl�����ϼ�
			item3=new JMenuItem("����(S)");
			item3.setMnemonic('S');
			item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
			item4=new JMenuItem("���Ϊ(A)");
			item4.setMnemonic('A');
			item5=new JMenuItem("ҳ������(U)");
			item5.setMnemonic('U');
			item6=new JMenuItem("��ӡ(P)");
			item6.setMnemonic('P');
			item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
			item7=new JMenuItem("�˳�(X)");
			item7.setMnemonic('X');
			
			eitem1=new JMenuItem("����(U)");
			eitem1.setMnemonic('U');
			eitem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));
			eitem2=new JMenuItem("����(T)");
			eitem2.setMnemonic('T');
			eitem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
			eitem3=new JMenuItem("����(C)");
			eitem3.setMnemonic('C');
			eitem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
			eitem4=new JMenuItem("ճ��(P)");
			eitem4.setMnemonic('P');
			eitem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
			eitem5=new JMenuItem("ɾ��(L)");
			eitem5.setMnemonic('L');
			eitem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
			eitem6=new JMenuItem("����(F)");
			eitem6.setMnemonic('F');
			eitem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
			eitem7=new JMenuItem("������һ��(N)");
			eitem7.setMnemonic('N');
			eitem7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
			eitem8=new JMenuItem("�滻(R)");
			eitem8.setMnemonic('R');
			eitem8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
			eitem9=new JMenuItem("ת��(G)");
			eitem9.setMnemonic('G');
			eitem9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
			eitem10=new JMenuItem("ȫѡ(A)");
			eitem10.setMnemonic('A');
			eitem10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
			eitem11=new JMenuItem("ʱ��/����(D)");
			eitem11.setMnemonic('D');
			eitem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
			
			oitem1=new JMenuItem("�Զ�����(W)");
			oitem1.setMnemonic('W');
			oitem2=new JMenuItem("����(F)");
			oitem2.setMnemonic('F');
			
			vitem1=new JMenuItem("״̬��(S)");
			vitem1.setMnemonic('S');
			
			hitem1=new JMenuItem("�鿴����(H)");
			hitem1.setMnemonic('H');
			hitem2=new JMenuItem("���ڼ��±�(A)");
			hitem2.setMnemonic('A');
			
			jta=new JTextArea();
		}
		
		public void setControls(){
			//���˵�����ӵ��˵���
			xinjian.add(file);
			xinjian.add(project);
			
			menu1.add(xinjian);
			menu1.add(item2);
			menu1.add(item3);
			menu1.add(item4);
			menu1.addSeparator();//��ӷָ���
			menu1.add(item5);
			menu1.add(item6);
			menu1.addSeparator();
			menu1.add(item7);
			
			menu2.add(eitem1);
			menu2.addSeparator();
			menu2.add(eitem2);
			menu2.add(eitem3);
			menu2.add(eitem4);
			menu2.add(eitem5);
			menu2.addSeparator();
			menu2.add(eitem6);
			menu2.add(eitem7);
			menu2.add(eitem8);
			menu2.add(eitem9);
			menu2.addSeparator();
			menu2.add(eitem10);
			menu2.add(eitem11);
			
			menu3.add(oitem1);
			menu3.add(oitem2);
			
			menu4.add(vitem1);
			
			menu5.add(hitem1);
			menu5.addSeparator();
			menu5.add(hitem2);
			
			//���˵���ӵ��˵�����
			jmb.add(menu1);
			jmb.add(menu2);
			jmb.add(menu3);
			jmb.add(menu4);
			jmb.add(menu5);
			
			titlePicture.setIcon(new ImageIcon(getClass().getResource("/images/title2.png")));
			titlePicture.setMaximumSize(getSize());
			dtm=new DefaultTableModel(null,new String[]{"���","Ա�����","����","����","ְ��","��ע"});
			StaffDao dao=new StaffDao();
			staffList=dao.getList();
			for(int i=0;i<staffList.size();i++){
				staff s=(staff)staffList.get(i);
				dtm.addRow(new Object[]{i+1,s.getStaffId(),s.getStaffName(),s.getDept().getDeptName(),s.getJob().getJobName(),s.getDetail()});
			}
			tab = new JTable(dtm);
			tab.setRowHeight(25);
			tab.setBackground(new Color(240,240,240));
			TableColumn tableColumn0 = tab.getColumnModel().getColumn(0);
			TableColumn tableColumn1 = tab.getColumnModel().getColumn(1);
			TableColumn tableColumn5 = tab.getColumnModel().getColumn(5);
			tableColumn0.setPreferredWidth(7);
			tableColumn1.setPreferredWidth(15);
			tableColumn5.setPreferredWidth(160);
			jsp=new JScrollPane(tab);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		
		public void setLayout(){
			GridBagConstraints sBagConstraints = new GridBagConstraints();
			sBagConstraints.fill = GridBagConstraints.BOTH;
			sBagConstraints.gridwidth = 5;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 0;
			sBagConstraints.gridy = 0;
			layout.setConstraints(jmb, sBagConstraints);
			sBagConstraints.gridwidth = 5;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 0;
			sBagConstraints.gridy = 1;
			layout.setConstraints(titlePicture, sBagConstraints);

			sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 0;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jb1, sBagConstraints);
			sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 1;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jb2, sBagConstraints);
			sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 2;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jb3, sBagConstraints);
			sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 3;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jb4, sBagConstraints);
			sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 4;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jb5, sBagConstraints);
	/*		sBagConstraints.gridwidth = 1;
			sBagConstraints.gridheight = 1;
			sBagConstraints.gridx = 0;
			sBagConstraints.gridy = 2;
			layout.setConstraints(jtb, sBagConstraints);*/
			sBagConstraints.gridwidth = 5;
			sBagConstraints.gridheight = 1;
			sBagConstraints.weighty=1;
			sBagConstraints.gridx = 0;
			sBagConstraints.gridy = 3;
			layout.setConstraints(jsp, sBagConstraints);
			
		}
		
		public void addControls(){

			setControls();
			//�趨���ֹ�����
			
			//�������
			//����ť��ӵ���������
	/*		jtb.add(jb1);
			jtb.add(jb2);
			jtb.add(jb3);
			jtb.add(jb4);
			jtb.add(jb5);
			jtb.add(jb6);
			jtb.add(jb7);
			jtb.add(jb8);*/
			this.add(jb1);
			this.add(jb2);
			this.add(jb3);
			this.add(jb4);
			this.add(jb5);
			
			
			
			//���˵�����ӵ�������
			this.setJMenuBar(jmb);
			this.add(titlePicture);
			
			//����������ӵ�������
			//this.add(jtb);
			
			
			//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//this.add(jsp);
			
			
			this.add(jsp);
		}
		
		public void addListener(){
			
			jb1.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							insert info=new insert();
							info.setVisible(true);
						}
					}
				);
			jb2.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
						int [] sel=tab.getSelectedRows();
						if(sel.length==1){
							for(int i=0;i<sel.length;i++){
								int id=Integer.parseInt(tab.getValueAt(sel[i], 1).toString());
							
								StaffDao dao=new StaffDao();
								dao.del(id);
								MainFrame mf=new MainFrame();
								mf.setVisible(true);
								dispose();
							}
						}
						else 
							JOptionPane.showMessageDialog(null,"��ѡ��ɾ������",
									"����" ,JOptionPane.INFORMATION_MESSAGE);
						}
					}
				);
			jb3.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							int sel=tab.getSelectedRow();
							if(sel!=-1){
								DeptDao deptdao=new DeptDao();
								deptList=deptdao.getList();
								JobDao jobdao=new JobDao();
								jobList=jobdao.getList();
								
								int id=Integer.parseInt(tab.getValueAt(sel, 1).toString());
								String name=tab.getValueAt(sel,2).toString();
								Dept dept1=new Dept();
								for(Dept dept:deptList){
									if(dept.getDeptName().equals (tab.getValueAt(sel, 3).toString())){
										dept1=dept;
										break;
									}
								}
								
								Job job1=new Job();
								for(Job job:jobList){
									if(job.getJobName().equals (tab.getValueAt(sel, 4).toString())){
										job1=job;
										break;
									}
								}
								String d=tab.getValueAt(sel,5).toString();
								staff Staff=new staff(id,name,dept1,job1,d);
								edit info=new edit(Staff);
								info.setVisible(true);
							}
							else
								JOptionPane.showMessageDialog(null,"��ѡ��༭����","����" ,JOptionPane.INFORMATION_MESSAGE);
							
						}		
					}
				);
			jb4.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							int rowCnt=dtm.getRowCount();
							int rowDate=setRowData();
							for(int i=0;i<rowCnt;i++){						
								dtm.removeRow(0);
							}
							for(int i=0;i<rowDate;i++){
								dtm.insertRow(i, rowData[i]);
							}
						}
					}
				);
			jb5.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.exit(0);
						}
					}
				);
		}
}


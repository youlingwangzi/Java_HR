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
	JMenu menu1, menu2, menu5;//���˵����ļ����༭����ʽ���鿴������
	JMenuItem item7;//�˳�
	JMenuItem insertJItem, edItem, deletItem, freshItem;
	JMenuItem file,project;
	
	//�����鶨�����
	JMenuItem hitem1,hitem2;
	JTextArea jta;
	
	//������
	JButton jb1,jb2,jb3,jb4,jb5;
	
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

		frameListener = new FrameListener(this);
		addMouseListener(frameListener);
		addMouseMotionListener(frameListener);  
		
		init();
		addControls();
		setLayout();
		addListener();
		
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
			jb1=new JButton("���Ա��");
			jb2=new JButton("ɾ��Ա��");
			jb3=new JButton("�༭Ա��");
			jb4=new JButton("ˢ���б�");
			jb5=new JButton(" ��    �� ");
			
			jmb=new JMenuBar();
			//���˵�
			menu1=new JMenu("ϵͳ(S)");
			menu1.setMnemonic('S');//�������Ƿ�
			menu2=new JMenu("Ա������(M)");
			menu2.setMnemonic('M');
			menu5=new JMenu("����(H)");
			menu5.setMnemonic('H');
			
			//�ļ�--�½�--��Ŀ¼
			item7=new JMenuItem("�˳�(X)");
			item7.setMnemonic('X');
			
			insertJItem =new JMenuItem("���Ա��(I)");
			insertJItem.setMnemonic('I');
			edItem =new JMenuItem("�༭Ա��(E)");
			edItem.setMnemonic('E');
			deletItem =new JMenuItem("ɾ��Ա��(D)");
			deletItem.setMnemonic('D');
			freshItem =new JMenuItem("ˢ���б�(F)");
			freshItem.setMnemonic('F');
			
			hitem1=new JMenuItem("�鿴����(H)");
			hitem1.setMnemonic('H');
			hitem2=new JMenuItem("���ڱ�ϵͳ(A)");
			hitem2.setMnemonic('A');
		}
		
		public void setControls(){
			//���˵�����ӵ��˵���
			menu1.add(item7);
			
			menu2.add(insertJItem);
			menu2.add(edItem);
			menu2.add(deletItem);
			menu2.add(freshItem);
			
			menu5.add(hitem1);
			menu5.addSeparator();
			menu5.add(hitem2);
			
			//���˵���ӵ��˵�����
			jmb.add(menu1);
			jmb.add(menu2);
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
			this.add(jb1);
			this.add(jb2);
			this.add(jb3);
			this.add(jb4);
			this.add(jb5);
			
			//���˵�����ӵ�������
			this.setJMenuBar(jmb);
			this.add(titlePicture);
			
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
			item7.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					System.exit(0);
				}
			});
			
			insertJItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					insert info=new insert();
					info.setVisible(true);
				}
			});
			edItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
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
			});
		deletItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
				
		});
			
		freshItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int rowCnt=dtm.getRowCount();
				int rowDate=setRowData();
				for(int i=0;i<rowCnt;i++){						
					dtm.removeRow(0);
				}
				for(int i=0;i<rowDate;i++){
					dtm.insertRow(i, rowData[i]);
				}
			}
		});
			hitem1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					new Help();
				}
			});
			hitem2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					JOptionPane.showMessageDialog(null,"��ϢѧԺѧ����֧�����¹���ϵͳ\nV1.0\n�����ߣ���������",
							"���ڱ�ϵͳ" ,JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
}


package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Help extends JFrame{

	//�趨���
	//��ҳ�汱��
	JLabel jl1;//��ǩ 
	
	//��ҳ���ϲ�
	JButton jb1,jb2,jb3;//��ť
	JPanel jp1;//���
	
	//��ҳ���в�
	JTabbedPane jtp;//ѡ�����
	JPanel jp2,jp3,jp4;//���
	
	JLabel jl2,jl3,jl4,jl5;//��ǩ
	JTextField jtf;//�ı���
	JPasswordField jpf;//�����
	JButton jb4;//��ť
	JCheckBox jcb1,jcb2;//��ѡ��
	
	//JPanel3���
	JLabel jl6,jl7;//��ǩ
	JTextField jtf1;
	JPasswordField jpf1;
	
	//JPanel4���
	JLabel jl8,jl9;//��ǩ
	JTextField jtf2;
	JPasswordField jpf2;
	JLabel insertJLabel, insertTextJLabel;
	JLabel editJLabel, editTextLabel;
	
	//���캯��
	public Help(){
		//�������
		//����JFrame����JLabel1���
		jl1=new JLabel(new ImageIcon(getClass().getResource("/images/help_title.png")));

		//����JFrame�в�JPanel2���
		
		jtp=new JTabbedPane();//ѡ�����
		jp2=new JPanel();
		jp3=new JPanel();
		
		//����JFrame�ϲ�JPanel1���
		jp1=new JPanel();
		jb1=new JButton("��       ��");
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dispose();
			}
		});
		
		//���ò��ֹ�����
		jp2.setLayout(new BorderLayout());
		jp3.setLayout(new BorderLayout());
		
		//
		insertJLabel = new JLabel(new ImageIcon(getClass().getResource("/images/insert.png")));
		insertTextJLabel = new JLabel("<HTML><h2>Ҫ�½�Ա��</h2><p></p><p>�����˵���������Ա��->�½�Ա��</p><p>����ֱ���ڹ������е���½�Ա����ť</p></HTML>");
		insertJLabel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
		
		editTextLabel = new JLabel("<HTML><h2>Ҫ�༭Ա��</h2><p>�������б���ѡ��Ҫ�༭��Ա��</p><p>Ȼ�󵥻��˵���������Ա��->�༭Ա��</p><p>����ֱ���ڹ������е���༭Ա����ť</p></HTML>");
		
		//�������
		//�������ӵ�JPanel2��
		jp2.add(insertTextJLabel,BorderLayout.CENTER);
		
		//�������ӵ�JPanel3��
		jp3.add(editTextLabel,BorderLayout.CENTER);
		
		//��ӵ�JPanel1��
		jp1.add(jb1);
		
		//�������ӵ�ѡ�������
		jtp.add("�����Ա",jp2);//��һ����������ѡ����ƣ��ڶ������������Ӧ�����
		jtp.add("�༭��Ա",jp3);
		
		//��JLabel1��ӵ�Frame����
		this.add(jl1,BorderLayout.NORTH);
		
		//��JPanle2��ӵ�Frame�в�
		this.add(jtp,BorderLayout.CENTER);
		
		//��JPanel1��ӵ�Frame�ϲ�
		this.add(jp1,BorderLayout.SOUTH);
		
		//��������
		this.setTitle("����");
		//ImageIcon icon=new ImageIcon("ͼƬ·��");
		//this.setIconImage(icon.getImage());
		this.setIconImage((new ImageIcon("images/icon.png")).getImage());
		this.setBounds(500,200,360,300);
		this.setVisible(true);
	}
}

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;
import java.io.*;	
import java.text.*;
import java.util.Date;
/*--------------------   추가  프레임   -----------------------*/
class insert_frame extends JFrame{	//room,thing 추가 클래스
	
	public static TextField userText = new TextField(10);
	public static JPanel body=new JPanel();
	JButton room_button2;
	
	insert_frame(String title,String text){		
		
		setTitle(title);
		Container g = getContentPane();
		g.setLayout(null);
		userText.setText("");
		
		JPanel input =new JPanel();
		body =new JPanel();
		input.setLayout(new FlowLayout());
		body.setLayout(new BorderLayout());
		input.setSize(300,40);
		input.setLocation(0,0);
		body.setSize(300,120);
		body.setLocation(18,40);
		
		Label namelabel = new Label(text, Label.RIGHT);
	
		input.add(namelabel);
		input.add(userText);
		room_button2=new JButton(title);
		
		room_button2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("방 추가")) 
						dispose();
			}
		});
		
		room_button2.addActionListener(new buttonevent_room());
		body.add(room_button2,BorderLayout.SOUTH);
		g.add(input);
		g.add(body);
		setSize(350, 200); 
		setVisible(true); 
	}
}

/*--------------------  디바이스 추가 프레임    -----------------------*/
class insert_frame_device extends insert_frame{
	
	public static JComboBox list1=new JComboBox(Management_system.data);
	public static JRadioButton tv =new JRadioButton("TV");
	public static JRadioButton light= new JRadioButton("전등");
	public static JRadioButton fan= new JRadioButton("선풍기");
	public static JRadioButton temp= new JRadioButton("온습도 센서");
	public static JRadioButton fire= new JRadioButton("화재 센서");
	public static JRadioButton lllum= new JRadioButton("조도 센서");
	
	insert_frame_device(String title,String text){
		super(title,text);
		
		Container g = getContentPane();
		insert_frame.body.add(new JScrollPane(list1),BorderLayout.NORTH);
		
		ButtonGroup group = new ButtonGroup();
		group.add(tv);
		group.add(light);
		group.add(fan);
		group.add(temp);
		group.add(fire);
		group.add(lllum);
		
		JLabel box1=new JLabel();
		box1.setLayout(new FlowLayout());
		
		box1.add(tv);
		box1.add(light);
		box1.add(fan);
		box1.add(temp);
		box1.add(fire);
		box1.add(lllum);
		insert_frame.body.add(box1,BorderLayout.CENTER);
		g.add(insert_frame.body);
		
		room_button2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("device 등록")) 
						dispose();
				}
		});
		
		setSize(350, 200);
	}
}

/*--------------------  검색 (Search)  -----------------------*/
 /*--------------  이름 종류검색 선택프레임  ----------------*/
class Search_Choice extends JFrame{
	Search_Choice(){
		setTitle("검색");
		Container sc = getContentPane();
		sc.setLayout(null);
		JLabel s_text=new JLabel();
		s_text.setText(" << 검색 방법 선택 >> ");
		s_text.setSize(200, 20);
		s_text.setLocation(80, 5);
		
		JButton name_B =new JButton();
		JButton type_B =new JButton();
		name_B.setText("이름 검색");
		type_B.setText("종류 검색");
		name_B.setBackground(Color.WHITE);
		type_B.setBackground(Color.WHITE);
		name_B.setSize(100, 50);
		type_B.setSize(100, 50);
		name_B.setLocation(30, 40);
		type_B.setLocation(160, 40);
		
		name_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("이름 검색")) {
						Search_frame_name sh_f=new Search_frame_name("검색","디바이스 검색 :");
						dispose();
					}
				}
		});
		
		type_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("종류 검색")) {
						Search_frame_type sh_t=new Search_frame_type();
						dispose();
					}
				}
		});
		
		sc.add(s_text);
		sc.add(name_B);
		sc.add(type_B);
		setSize(300, 150);
		setVisible(true);
	}
}
/*--------------  이름 검색 프레임  ----------------*/
class Search_frame_name extends insert_frame{
	
	Search_frame_name(String title,String text){
		super(title,text);
		room_button2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("검색")) {
						Search_name sh=new Search_name();
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write(userText.getText());
							eventlog.log.write(" 를 검색함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						dispose();
					}
				}
		});
	}
}
/*--------------   종류검색 프레임  ----------------*/
class Search_frame_type extends JFrame{
	
	JLabel kinds=new JLabel();
	public static JComboBox list2;
	JButton s_bt;
	
	Search_frame_type(){
		setTitle("검색");
		Container st = getContentPane();
		st.setLayout(new BorderLayout(0,20));
		
		kinds.setText(" < 디바이스 종류  > ");
		list2=new JComboBox(Room.get_device_kinds());
		s_bt=new JButton();
		s_bt.setText("검색");
		
		s_bt.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("검색")) {
						Search_type sh=new Search_type();
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  타입 기반 검색 실행  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						dispose();
					}
				}
		}); 
		
		st.add(kinds,BorderLayout.NORTH);
		st.add(list2,BorderLayout.CENTER);
		st.add(s_bt,BorderLayout.SOUTH);
		setSize(300, 150);
		setVisible(true);
	}
}

/*--------------  이름 검색  ----------------*/
class Search_name extends JFrame{
	
	JList List1;
	private Vector<String> dev=new Vector<String>();
	
	Search_name(){
		setTitle("검색");
		Container g = getContentPane();
		g.setLayout(new BorderLayout(0,10));
		JLabel s_text=new JLabel();
		s_text.setText(" << 검색 결과  >>  ");
		JButton bt =new JButton();
		bt.setText("조회");
		bt.setBackground(Color.WHITE);
		bt.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("조회")) {
						look_up();
					}
			 }
		});
		
		g.add(bt,BorderLayout.SOUTH);
		g.add(s_text,BorderLayout.NORTH);
		
		for(int i=0;i<Room.get_all_device_index();i++) { 
			if(Search_frame_name.userText.getText().equals(Room.get_all_device_name().get(i).toString())) {
				dev.add(Room.get_all_device_name().get(i).toString());
			}
		}
		
		if(dev.size()==0) {
			JOptionPane.showMessageDialog(null,
					"검색 결과 없음!", "Message",
					JOptionPane.ERROR_MESSAGE);
					return;
		}
		
		List1 = new JList(dev); 
		List1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		g.add(new JScrollPane(List1),BorderLayout.CENTER);
		
		setSize(220, 300); 
		setVisible(true); 
	}
	public void look_up(){
		int checks=0;
		int check_W=0;
		for(int i=0;i<Room.get_all_device_index();i++) {
			if(Room.get_all_device_name().get(i).toString().equals(List1.getSelectedValue())) {
				if((checks)==List1.getSelectedIndex()) {
					check_W++;
					if(Room.get_all_device().get(i).getClass().equals(TV.class)) {
						Room.<TV>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
						break;
					}
					else if(Room.get_all_device().get(i).getClass().equals(Fan.class)) {
						Room.<Fan>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
						break;
					}
					else if(Room.get_all_device().get(i).getClass().equals(Light.class)) {
						Room.<Light>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
						break;
					}
					else if(Room.get_all_device().get(i).getClass().equals(Tem_Hum_Sensor.class)) {
						Room.<Tem_Hum_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
						break;
					}
					else if(Room.get_all_device().get(i).getClass().equals(Fire_Sensor.class)) {
						Room.<Fire_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					}
					else if(Room.get_all_device().get(i).getClass().equals(Light_Sensor.class)) {
						Room.<Light_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					}
					else
						break;
				}
				checks++;
			}
		}
		
		if(check_W==0) {
			JOptionPane.showMessageDialog(null,
					"조회할 디바이스를 선택하세요!", "Message",
					JOptionPane.ERROR_MESSAGE);
					return;
		}
	}
}

/*--------------  종류 검색  ----------------*/
class Search_type extends JFrame{  
	
	private int index;
	JList List;
	private Vector<String> dev0;
	private Vector<String> dev1;
	private Vector<String> dev2;
	private Vector<String> dev3;
	private Vector<String> dev4;
	private Vector<String> dev5;
	
	Search_type(){
		setTitle("검색");
		Container g = getContentPane();
		g.setLayout(new BorderLayout(0,10));
		JLabel s_text=new JLabel();
		s_text.setText(" << 검색 결과  >>  ");
		JButton bt =new JButton();
		bt.setText("조회");
		bt.setBackground(Color.WHITE);
		
		bt.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("조회")) {
						look_up();
					}
			 }
		});
						
		g.add(bt,BorderLayout.SOUTH);
		g.add(s_text,BorderLayout.NORTH);
		
		index=Search_frame_type.list2.getSelectedIndex();
		
		switch(index) {
		
		case 0:
			dev0=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(TV.class)) {
					dev0.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev0); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
			
		case 1:
			dev1=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(Fan.class)) {
					dev1.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev1); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
			
		case 2:
			dev2=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(Light.class)) {
					dev2.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev2); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
			
		case 3:
			dev3=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(Tem_Hum_Sensor.class)) {
					dev3.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev3); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
		
		case 4:
			dev4=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(Fire_Sensor.class)) {
					dev4.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev4); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
			
		case 5:
			dev5=new Vector<String>();
			for(int i=0;i<Room.get_all_device_index();i++) {
				if(Room.get_all_device().get(i).getClass().equals(Light_Sensor.class)) {
					dev5.add(Room.get_all_device_name().get(i).toString());
				}
			}
			List = new JList(dev5); 
			List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			g.add(new JScrollPane(List),BorderLayout.CENTER);
			break;
			
		}
		
		setSize(220, 300); 
		setVisible(true); 
	}
	public void look_up() {
		int checks=0;
		int check_W=0;
		int index_i[]=new int[30];
		int j=0;
		int n;
		int s_chk=0;
		
		for(int i=0;i<Room.get_all_device_index();i++) {
			if(Room.get_all_device_name().get(i).toString().equals(List.getSelectedValue())) {
				switch(index) {
				case 0:
					for(int m=0;m<dev0.size();m++) {
						if(dev0.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
						
				case 1:
					for(int m=0;m<dev1.size();m++) {
						if(dev1.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
						
				case 2:
					for(int m=0;m<dev2.size();m++) {
						if(dev2.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
						
				case 3:
					for(int m=0;m<dev3.size();m++) {
						if(dev3.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
						
				case 4:
					for(int m=0;m<dev4.size();m++) {
						if(dev4.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
						
				case 5:
					for(int m=0;m<dev5.size();m++) {
						if(dev5.get(m).toString().equals(List.getSelectedValue())) {
							index_i[j++]=m;
						}
					}
					break;
					}
				break;
			}
		}
		for(n=0;n<j;n++) {
			if(List.getSelectedIndex()==index_i[n]) {
				break;
			}
		}

		for(int i=0;i<Room.get_all_device_index();i++) {
			if(Room.get_all_device_name().get(i).toString().equals(List.getSelectedValue())) {
				check_W++;
				if(s_chk<n) {
					s_chk++;
					continue;
				}
				switch(index) {
				case 0:
					Room.<TV>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
						
				case 1:
					Room.<Fan>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
						
				case 2:
					Room.<Light>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
						
				case 3:
					Room.<Tem_Hum_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
						
				case 4:
					Room.<Fire_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
						
				case 5:
					Room.<Light_Sensor>get_all_device_T(i).device_inside(Search_frame_name.userText.getText());
					break;
					}
				break;	
			}
			
		}
		if(check_W==0) {
			JOptionPane.showMessageDialog(null,
					"조회할 디바이스를 선택하세요!", "Message",
					JOptionPane.ERROR_MESSAGE);
					return;
		}
	}
}

/*--------------------  방 클래스     -----------------------*/
class Room extends JFrame{  
	
	private static Vector<String> device_kinds = new Vector<String>();
	private static Vector<String> all_device_name = new Vector<String>();
	private static Vector<Object> all_device = new Vector<Object>();
	private static int all_device_index=0;
	private String Room_name;
	private int j=0;
	private int sensor_index=0;
	private int actuator_index=0;
	private int this_device_index=0;
	private String device_name[]=new String[50];
	
	JPanel Room=new JPanel();	//틀
	JPanel img_room=new JPanel();	//이미지
	JPanel device_list=new JPanel();	//디바이스 목록
	JLabel device_n=new JLabel(); //디바이스 목록
	JButton tton=new JButton();		//방이름 버튼
	Container cr;
	
	Room(){
		this.Room_name=insert_frame.userText.getText();
		device_kinds.add("TV");device_kinds.add("선풍기");device_kinds.add("전등");
		device_kinds.add("온습도 센서");device_kinds.add("화재 센서");device_kinds.add("조도 센서");
		cr=getContentPane();
		cr.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
		setSize(900, 550); 
		setVisible(false); 
		
		Management_system.data1=insert_frame.userText.getText();
		Management_system.data[Management_system.room_index]=insert_frame.userText.getText();
		device_n.setText("Sensor: "+ sensor_index + "        " + "Actuator: " + actuator_index );
		ImageIcon originIcon = new ImageIcon("C:\\javawork\\ROOM.PNG");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JLabel pic =new JLabel(Icon);
		
		img_room.add(pic);
		device_n.setForeground(Color.white);
		img_room.setBackground(Color.BLACK);
		device_list.add(device_n);
		device_list.setBackground(Color.BLACK);
		tton.setText(Management_system.data1);
		tton.setBackground(Color.GRAY);
		tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 for(;j<Management_system.room_index;j++) {
						if(b.getText().equals(Management_system.data[j])) {
							Room_inside(Management_system.data[j]);
							break;
						}
					}
					j=0;
			}
		});
		tton.addActionListener(new buttonevent_room());
		Room.setLayout(new BorderLayout());
		Room.add(tton, BorderLayout.SOUTH);
		Room.add(device_list, BorderLayout.CENTER);
		Room.add(img_room,BorderLayout.NORTH);
		
		
		Management_system.p2.add(Room);
		Management_system.p2.setLocation(0,80);
		Management_system.p2.setSize(1000,550);
		Management_system.c.add(Management_system.p2);
		Management_system.rep();
	}
	public static Vector get_device_kinds() {
		return device_kinds;
	}
	public static int get_all_device_index() {
		return all_device_index;
	}
	public static Vector get_all_device() {
		return all_device;
	}
	public static Vector get_all_device_name() {
		return all_device_name;
	}
	public static <T> T get_all_device_T(int i) {
		return (T)all_device.get(i);
	}
	public int get_device_index() {
		return this_device_index;
	}
	
	public String get_device_name(int i) {
		return device_name[i];
	}
	
	public void Room_inside(String r_name) {
		setTitle(r_name);
		setVisible(true);
	}
	public void device_insert() {
		device_name[this_device_index]=insert_frame_device.userText.getText();
		if(insert_frame_device.tv.isSelected()) {
			TV tv_a=new TV(device_name[this_device_index],Room_name);
			all_device.add(tv_a);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ sensor_index + "        " + "Actuator: " + ++actuator_index );
			cr.add(tv_a.device);
			rep();
			Management_system.rep();
		}
		else if(insert_frame_device.fan.isSelected()) {
			Fan fan_a=new Fan(device_name[this_device_index],Room_name);
			all_device.add(fan_a);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ sensor_index + "        " + "Actuator: " + ++actuator_index );
			cr.add(fan_a.device);
			rep();
			Management_system.rep();
		}
		else if(insert_frame_device.light.isSelected()) {
			Light light_a=new Light(device_name[this_device_index],Room_name);
			all_device.add(light_a);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ sensor_index + "        " + "Actuator: " + ++actuator_index );
			cr.add(light_a.device);
			rep();
			Management_system.rep();
		}
		else if(insert_frame_device.temp.isSelected()) {
			Tem_Hum_Sensor tem_s=new Tem_Hum_Sensor(device_name[this_device_index],Room_name);
			all_device.add(tem_s);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ ++sensor_index + "        " + "Actuator: " + actuator_index );
			cr.add(tem_s.device);
			rep();
			Management_system.rep();
		}
		else if(insert_frame_device.fire.isSelected()) {
			Fire_Sensor fire_s=new Fire_Sensor(device_name[this_device_index],Room_name);
			all_device.add(fire_s);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ ++sensor_index + "        " + "Actuator: " + actuator_index );
			cr.add(fire_s.device);
			rep();
			Management_system.rep();
		}
		else if(insert_frame_device.lllum.isSelected()) {
			Light_Sensor light_s=new Light_Sensor(device_name[this_device_index],Room_name);
			all_device.add(light_s);
			all_device_name.add(device_name[this_device_index]);
			this_device_index++;
			all_device_index++;
			device_n.setText("Sensor: "+ ++sensor_index + "        " + "Actuator: " + actuator_index );
			cr.add(light_s.device);
			rep();
			Management_system.rep();
		}
		else {
			JOptionPane.showMessageDialog(null,
					"타입을 선택하세요", "Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void rep() {
		cr.revalidate();
		cr.repaint();
	}
}

/*--------------------   Device   -----------------------*/
interface Device{
	boolean On_Off_control();
	void device_inside(String d_name);
	void timer();
}
/*--------------  Device-Sensor  ----------------*/
abstract class Sensor extends JFrame implements Device{
	protected int index;  
	protected boolean OF;
	
	JPanel device=new JPanel();
	JButton d_tton=new JButton();
	ImageIcon originIcon;
	ImageIcon Icon;
	JLabel image;
	
	Container cd;
	JPanel status;
	JPanel control;
	
	JButton OF_B;
	ImageIcon Icon_off;
	ImageIcon Icon_on;
	JLabel text_OF;
	
	JLabel name_type;
	JLabel Wroom;
	JPanel stu;
	
	JLabel tmlabel;
	TextField time;
	JButton time_b;
	JLabel time_lb;
	
	Sensor(){
		this.index=0;
		this.OF=false;
	
		status=new JPanel();
		control=new JPanel();
		
		cd=getContentPane();
		cd.setLayout(null);
		
		TitledBorder s_tb=new TitledBorder(new LineBorder(Color.BLACK),"상세 정보");
		s_tb.setTitleColor(Color.BLUE);
		status.setLayout(new BorderLayout());
		status.setBorder(s_tb);
		TitledBorder c_tb=new TitledBorder(new LineBorder(Color.BLACK),"Control");
		c_tb.setTitleColor(Color.BLUE);
		control.setLayout(null);
		control.setBorder(c_tb);
		
		ImageIcon originIcon = new ImageIcon("C:\\javawork\\OFF.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(100, 50, Image.SCALE_SMOOTH );
		Icon_off = new ImageIcon(changedImg);
		
		ImageIcon originIcon2 = new ImageIcon("C:\\javawork\\ON.png");  
		Image originImg2 = originIcon2.getImage(); 
		Image changedImg2= originImg2.getScaledInstance(100, 50, Image.SCALE_SMOOTH );
		Icon_on = new ImageIcon(changedImg2);
		
		if(OF==false)
			OF_B =new JButton(Icon_off);
		else
			OF_B =new JButton(Icon_on);
		OF_B.setBackground(Color.WHITE);
		if(Management_system.grade.equals("Gold") || Management_system.grade.equals("Nomal"))
			OF_B.setEnabled(false);
		
		tmlabel = new JLabel(" 타이머 설정:", Label.RIGHT);
		time = new TextField(10);
		time_b=new JButton();
		time_lb=new JLabel();
		
		time_b.setText("설정");
		time_b.setSize(60,20);
		time_b.setLocation(250, 105);
		time_b.setBackground(Color.WHITE);
		tmlabel.setSize(80,30);
		tmlabel.setLocation(20, 100);
		time.setSize(130,20);
		time.setLocation(110, 105);
		time_lb.setSize(150,20);
		time_lb.setLocation(100, 150);
		
		OF_B.setSize(100, 50);
		OF_B.setLocation(120, 30);
		text_OF=new JLabel();
		text_OF.setText("디바이스 전원 : ");
		text_OF.setSize(90, 50);
		text_OF.setLocation(20, 30);
		control.add(OF_B);
		control.add(text_OF);
		control.add(tmlabel);
		control.add(time);
		control.add(time_b);
		control.add(time_lb);
		
		cd.add(status);
		cd.add(control);
		
		status.setLocation(0, 0);
		status.setSize(580, 150);
		control.setLocation(0, 150);
		control.setSize(580, 360);
		setSize(600, 550); 
		setVisible(false);
	}
	
	public abstract void sensing();
	public abstract void timer();
	
	public boolean On_Off_control(){
		if(OF==true)
			OF=false;
		else
			OF=true;
		return OF;
	}
	public void device_inside(String d_name){ 
		setTitle(d_name);
		setVisible(true);
	}
}
/*--------------  Device-Actuator  ----------------*/
abstract class Actuator extends JFrame implements Device{
	protected int index;
	protected boolean OF;
	
	JPanel device=new JPanel();
	JButton d_tton=new JButton();
	ImageIcon originIcon;
	ImageIcon Icon;
	JLabel image;
	
	Container cd;
	JPanel status;
	JPanel control;
	
	JLabel name_type;
	JLabel Wroom;
	JPanel stu;
	
	JButton OF_B;
	ImageIcon Icon_off;
	ImageIcon Icon_on;
	JLabel text_OF;
	
	JLabel tmlabel;
	TextField time;
	JButton time_b;
	JLabel time_lb;
	
	Actuator(){
		this.index=0;
		this.OF=false;
		
		status=new JPanel();
		control=new JPanel();
		
		cd=getContentPane();
		cd.setLayout(null);
		
		TitledBorder s_tb=new TitledBorder(new LineBorder(Color.BLACK),"상세 정보");
		s_tb.setTitleColor(Color.BLUE);
		status.setLayout(new BorderLayout());
		status.setBorder(s_tb);
		TitledBorder c_tb=new TitledBorder(new LineBorder(Color.BLACK),"Control");
		c_tb.setTitleColor(Color.BLUE);
		control.setLayout(null);
		control.setBorder(c_tb);
		
		ImageIcon originIcon = new ImageIcon("C:\\javawork\\OFF.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(100, 50, Image.SCALE_SMOOTH );
		Icon_off = new ImageIcon(changedImg);
		
		ImageIcon originIcon2 = new ImageIcon("C:\\javawork\\ON.png");  
		Image originImg2 = originIcon2.getImage(); 
		Image changedImg2= originImg2.getScaledInstance(100, 50, Image.SCALE_SMOOTH );
		Icon_on = new ImageIcon(changedImg2);
		
		if(OF==false)
			OF_B =new JButton(Icon_off);
		else
			OF_B =new JButton(Icon_on);
		OF_B.setBackground(Color.WHITE);
		if(Management_system.grade.equals("Gold") || Management_system.grade.equals("Nomal"))
			OF_B.setEnabled(false);
		
		tmlabel = new JLabel(" 타이머 설정:", Label.RIGHT);
		time = new TextField(10);
		time_b=new JButton();
		time_lb=new JLabel();
		
		OF_B.setSize(100, 50);
		OF_B.setLocation(120, 30);
		text_OF=new JLabel();
		text_OF.setText("디바이스 전원 : ");
		text_OF.setSize(90, 50);
		text_OF.setLocation(20, 30);
		control.add(OF_B);
		control.add(text_OF);
		
		cd.add(status);
		cd.add(control);
		
		status.setLocation(0, 0);
		status.setSize(580, 150);
		control.setLocation(0, 150);
		control.setSize(580, 360);
		setSize(600, 550); 
		setVisible(false);
	}
	
	public boolean On_Off_control(){
		if(OF==true)
			OF=false;
		else
			OF=true;
		return OF;
	}
	
	public abstract void timer();
	
	public void device_inside(String d_name){
		setTitle(d_name);
		setVisible(true);
	}
}
/*--------------  Device-Sensor-Tem_Hum_sensor  ----------------*/
class Tem_Hum_Sensor extends Sensor{
	
	private int hum;
	
	JLabel of_l;
	JLabel tem_l;
	JLabel hum_l;
	
	Tem_Hum_Sensor(String d_name,String r_name){
		super();
		
		originIcon = new ImageIcon("C:\\javawork\\Temp.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		tem_l=new JLabel();
		hum_l=new JLabel();
		
		if(OF==true) {
			of_l.setText("전원  : ON ");
			tem_l.setText("온도  : " + index);
			hum_l.setText("습도  : " + hum);
		}
		else {
			of_l.setText("전원  : OFF");
			tem_l.setText("온도  : OFF ");
			hum_l.setText("습도  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(tem_l,BorderLayout.CENTER);
		stu.add(hum_l,BorderLayout.SOUTH);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: 온습도 센서  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  온습도 센서 를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();
						sensing();
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();
						time_b.setEnabled(true);
					}	
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
		
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			tem_l.setText("온도  : " + index);
			hum_l.setText("습도  : " + hum);
		}
		else {
			of_l.setText("전원  : OFF");
			tem_l.setText("온도  : OFF ");
			hum_l.setText("습도  : OFF ");
		}
	}
	public void sensing() {
		SensingThread T_st=new SensingThread();
		T_st.start();
	}
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	/*-------- Sensing Thread   ----------*/
	class SensingThread extends Thread {
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				Random random=new Random();
				index=random.nextInt(20)+20;
				hum=random.nextInt(100);
				tem_l.setText("온도  : " + index);
				hum_l.setText("습도  : " + hum);
				try {
					sleep(2000); 
				}
				catch(InterruptedException e){return;}
			}
		}
	}
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
}
/*--------------  Device-Sensor-Light_sensor  ----------------*/
class Light_Sensor extends Sensor{
	
	JLabel of_l;
	JLabel lig_l;  
	
	Light_Sensor(String d_name,String r_name){
		super();
		
		originIcon = new ImageIcon("C:\\javawork\\Light_s.jpg");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		lig_l=new JLabel();

		if(OF==true) {
			of_l.setText("전원  : ON ");
			lig_l.setText("조도  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			lig_l.setText("조도  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(lig_l,BorderLayout.CENTER);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: 조도 센서  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  조도 센서 를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();
						sensing();
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();
						time_b.setEnabled(true);
					}	
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			lig_l.setText("조도  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			lig_l.setText("조도  : OFF ");
		}
	}
	public void sensing() {
		SensingThread L_st=new SensingThread();
		L_st.start();
	}
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	/*-------- Sensing Thread   ----------*/
	class SensingThread extends Thread {
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				Random random=new Random();
				index=random.nextInt(30);
				lig_l.setText("조도  : " + index);
				try {
					sleep(2000); 
				}
				catch(InterruptedException e){return;}
			}
		}
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
}
/*--------------  Device-Sensor-Fire_sensor  ----------------*/
class Fire_Sensor extends Sensor{
	
	JLabel of_l;
	JLabel fire_l;

	Fire_Sensor(String d_name,String r_name){
		super();
		
		originIcon = new ImageIcon("C:\\javawork\\Fire.jpg");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		fire_l=new JLabel();
		
		if(OF==true) {
			of_l.setText("전원  : ON ");
			fire_l.setText("연기량  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			fire_l.setText("연기량  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(fire_l,BorderLayout.CENTER);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: 화재 센서  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  화재센서 를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();
						sensing();
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();	
						time_b.setEnabled(true);
					}	
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			fire_l.setText("연기량  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			fire_l.setText("연기량  : OFF ");
		}
	}
	
	public void sensing() {
		SensingThread F_st=new SensingThread();
		F_st.start();
	}
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	/*-------- Sensing Thread   ----------*/
	class SensingThread extends Thread {
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				Random random=new Random();
				index=random.nextInt(30);
				fire_l.setText("연기량  : " + index);
				try {
					sleep(2000); 
				}
				catch(InterruptedException e){return;}
			}
		}
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
}
/*--------------  Device-Actuator-TV  ----------------*/
class TV extends Actuator{
	private int volum;
	private int temp_vol;
	
	JLabel of_l;
	JLabel ch_l;
	JLabel vol_l;
	
	TV(String d_name,String r_name){
		super();
		this.volum=0;
		
		originIcon = new ImageIcon("C:\\javawork\\TV.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		ch_l=new JLabel();
		vol_l=new JLabel();
		
		if(OF==true) {
			of_l.setText("전원  : ON ");
			ch_l.setText("채널  : " + index);
			vol_l.setText("음량  : " + volum);
		}
		else {
			of_l.setText("전원  : OFF");
			ch_l.setText("채널  : OFF ");
			vol_l.setText("음량  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(ch_l,BorderLayout.CENTER);
		stu.add(vol_l,BorderLayout.SOUTH);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: TV  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  TV 를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();	
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();
						time_b.setEnabled(true);
					}	
			}
		});
		
		ImageIcon orivol_u = new ImageIcon("C:\\javawork\\볼륨+.jpg");  
		Image originImg_vol_u = orivol_u.getImage(); 
		Image changedImg_vol_u= originImg_vol_u.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon vol_u = new ImageIcon(changedImg_vol_u);
		JButton image_vol_u =new JButton(vol_u);
		
		ImageIcon orivol_d = new ImageIcon("C:\\javawork\\볼륨-.jpg");  
		Image originImg_vol_d = orivol_d.getImage(); 
		Image changedImg_vol_d= originImg_vol_d.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon vol_d = new ImageIcon(changedImg_vol_d);
		JButton image_vol_d =new JButton(vol_d);
		
		ImageIcon orivol_x = new ImageIcon("C:\\javawork\\음소거.png");  
		Image originImg_vol_x = orivol_x.getImage(); 
		Image changedImg_vol_x= originImg_vol_x.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon vol_x = new ImageIcon(changedImg_vol_x);
		JButton image_vol_x =new JButton(vol_x);
		
		ImageIcon orivol_o = new ImageIcon("C:\\javawork\\음소거 해제.png");  
		Image originImg_vol_o = orivol_o.getImage(); 
		Image changedImg_vol_o= originImg_vol_o.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon vol_o = new ImageIcon(changedImg_vol_o);
		
		JLabel text_vol=new JLabel();
		
		text_vol.setText(" 볼륨:  ");
		text_vol.setLocation(30,110);
		text_vol.setSize(90,50);
		image_vol_u.setSize(70, 70);
		image_vol_u.setLocation(120, 100);
		image_vol_d.setSize(70, 70);
		image_vol_d.setLocation(230, 100);
		image_vol_x.setSize(70, 70);
		image_vol_x.setLocation(340, 100);
		image_vol_x.setBackground(Color.WHITE);
		
		time_b.setText("설정");
		time_b.setSize(60,20);
		time_b.setLocation(250, 305);
		time_b.setBackground(Color.WHITE);
		tmlabel.setSize(80,30);
		tmlabel.setLocation(20, 300);
		time.setSize(130,20);
		time.setLocation(110, 305);
		time_lb.setSize(150,20);
		time_lb.setLocation(100, 330);
		
		control.add(tmlabel);
		control.add(time);
		control.add(time_b);
		control.add(time_lb);
		
		image_vol_u.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(vol_u) && OF==true) {
						vol_l.setText("음량  : " + ++volum);
					}
			}
		});
		
		image_vol_d.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(vol_d) && OF==true) {
					 if(volum>0)
						 vol_l.setText("음량  : " + --volum);
					}
			}
		});
		
		image_vol_x.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(vol_x) && OF==true) {
						image_vol_x.setIcon(vol_o);
						temp_vol=volum;
						volum=0;
						vol_l.setText("음량  : " + volum);
					}
					else {
						if(OF==true) {
							image_vol_x.setIcon(vol_x);
							volum=temp_vol;
							vol_l.setText("음량  : " + volum);
						}
					}
			}
		});
		
		ImageIcon orichl_u = new ImageIcon("C:\\javawork\\증가.png");  
		Image originImg_chl_u = orichl_u.getImage(); 
		Image changedImg_chl_u= originImg_chl_u.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon chl_u = new ImageIcon(changedImg_chl_u);
		JButton image_chl_u =new JButton(chl_u);
		
		ImageIcon orichl_d = new ImageIcon("C:\\javawork\\감소.png");  
		Image originImg_chl_d = orichl_d.getImage(); 
		Image changedImg_chl_d= originImg_chl_d.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon chl_d = new ImageIcon(changedImg_chl_d);
		JButton image_chl_d =new JButton(chl_d);
		
		JLabel text_chl=new JLabel();
		
		text_chl.setText(" 채널:  ");
		text_chl.setLocation(30,210);
		text_chl.setSize(90,50);
		image_chl_u.setSize(70, 70);
		image_chl_u.setLocation(120, 200);
		image_chl_d.setSize(70, 70);
		image_chl_d.setLocation(230, 200);
		image_chl_u.setBackground(Color.WHITE);
		image_chl_d.setBackground(Color.WHITE);
		
		image_chl_u.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(chl_u) && OF==true) {
					 ch_l.setText("채널  : " + ++index);
				 }
			}
		});
		
		image_chl_d.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(chl_d) && OF==true) {
					 if(index>0)
						 ch_l.setText("채널  : " + --index);
					}
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
		
		control.add(image_vol_u);
		control.add(image_vol_d);
		control.add(image_vol_x);
		control.add(image_chl_u);
		control.add(image_chl_d);
		control.add(text_vol);
		control.add(text_chl);
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			ch_l.setText("채널  : " + index);
			vol_l.setText("음량  : " + volum);
		}
		else {
			of_l.setText("전원  : OFF");
			ch_l.setText("채널  : OFF ");
			vol_l.setText("음량  : OFF ");
		}
	}
	
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
}
/*--------------  Device-Actuator-Fan  ----------------*/
class Fan extends Actuator{
	
	JLabel of_l;
	JLabel wind_l;
	
	Fan(String d_name,String r_name){
		super();
		
		originIcon = new ImageIcon("C:\\javawork\\Fan.jpg");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		wind_l=new JLabel();

		if(OF==true) {
			of_l.setText("전원  : ON ");
			wind_l.setText("풍량  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			wind_l.setText("풍량  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(wind_l,BorderLayout.CENTER);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: 선풍기  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  선풍기를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();	
						time_b.setEnabled(true);
					}	
			}
		});
		
		ImageIcon oriwind_u = new ImageIcon("C:\\javawork\\증가.png");  
		Image originImg_wind_u = oriwind_u.getImage(); 
		Image changedImg_wind_u= originImg_wind_u.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon wind_u = new ImageIcon(changedImg_wind_u);
		JButton image_wind_u =new JButton(wind_u);
		
		ImageIcon oriwind_d = new ImageIcon("C:\\javawork\\감소.png");  
		Image originImg_wind_d = oriwind_d.getImage(); 
		Image changedImg_wind_d= originImg_wind_d.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon wind_d = new ImageIcon(changedImg_wind_d);
		JButton image_wind_d =new JButton(wind_d);
		
		JLabel text_wind=new JLabel();
		
		text_wind.setText(" 풍량:  ");
		text_wind.setLocation(30,140);
		text_wind.setSize(90,50);
		image_wind_u.setSize(70, 70);
		image_wind_u.setLocation(120, 130);
		image_wind_d.setSize(70, 70);
		image_wind_d.setLocation(230, 130);
		image_wind_u.setBackground(Color.WHITE);
		image_wind_d.setBackground(Color.WHITE);
		
		time_b.setText("설정");
		time_b.setSize(60,20);
		time_b.setLocation(250, 255);
		time_b.setBackground(Color.WHITE);
		tmlabel.setSize(80,30);
		tmlabel.setLocation(20, 250);
		time.setSize(130,20);
		time.setLocation(110, 255);
		time_lb.setSize(150,20);
		time_lb.setLocation(100, 300);
		
		control.add(tmlabel);
		control.add(time);
		control.add(time_b);
		control.add(time_lb);
		
		image_wind_u.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(wind_u) && OF==true) {
					 index%=3;
					 wind_l.setText("풍량  : " + ++index);
				 }
			}
		});
		
		image_wind_d.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(wind_d) && OF==true) {
					 if(index>0)
						 wind_l.setText("풍량  : " + --index);
					}
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
		
		control.add(image_wind_u);
		control.add(image_wind_d);
		control.add(text_wind);
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			wind_l.setText("풍량  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			wind_l.setText("풍량  : OFF ");
		}
	}
	
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
	
}

/*--------------  Device-Actuator-Light  ----------------*/
class Light extends Actuator{
	
	JLabel of_l;
	JLabel light_l;
	
	Light(String d_name,String r_name){
		super();
		
		originIcon = new ImageIcon("C:\\javawork\\Light.jpg");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(225, 150, Image.SCALE_SMOOTH );
		Icon = new ImageIcon(changedImg);
		image =new JLabel(Icon);
		d_tton.setText(d_name);
		d_tton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(d_name)) 
						device_inside(d_name);
			}
		});
		d_tton.setBackground(Color.WHITE);
		
		device.setLayout(new BorderLayout());
		device.add(image,BorderLayout.NORTH);
		device.add(d_tton,BorderLayout.CENTER);
		
		
		name_type= new JLabel();
		Wroom= new JLabel();
		stu=new JPanel();
		stu.setLayout(new BorderLayout());
		of_l=new JLabel();
		light_l=new JLabel();
		
		if(OF==true) {
			of_l.setText("전원  : ON ");
			light_l.setText("밝기  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			light_l.setText("밝기  : OFF ");
		}
		stu.add(of_l,BorderLayout.NORTH);
		stu.add(light_l,BorderLayout.CENTER);
		
		name_type.setText("디바이스 이름 :  "+ d_name+ "    -Type: 전등  ");
		Wroom.setText("설치장소 :  "+ r_name);
		
		status.add(name_type,BorderLayout.NORTH);
		status.add(Wroom,BorderLayout.CENTER);
		status.add(stu,BorderLayout.SOUTH);
		
		OF_B.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getIcon().equals(Icon_off)) {
						try{
							eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
							long time = System.currentTimeMillis(); 
							SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String str = dayTime.format(new Date(time));
							eventlog.log.write(str);
							eventlog.log.write("\r\t");
							eventlog.log.write("  전등를 제어 함  \r\n");
							eventlog.log.close();
						}
						catch (IOException a) {
							System.out.println("입출력 오류");
						}
						OF_B.setIcon(Icon_on);
						OF=On_Off_control();
						on_off_status();
						time_b.setEnabled(true);
					}
					else {
						OF_B.setIcon(Icon_off);
						OF=On_Off_control();
						on_off_status();	
						time_b.setEnabled(true);
					}	
			}
		});
		
		ImageIcon orilig_u = new ImageIcon("C:\\javawork\\증가.png");  
		Image originImg_lig_u = orilig_u.getImage(); 
		Image changedImg_lig_u= originImg_lig_u.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon lig_u = new ImageIcon(changedImg_lig_u);
		JButton image_lig_u =new JButton(lig_u);
		
		ImageIcon orilig_d = new ImageIcon("C:\\javawork\\감소.png");  
		Image originImg_lig_d = orilig_d.getImage(); 
		Image changedImg_lig_d= originImg_lig_d.getScaledInstance(70, 70, Image.SCALE_SMOOTH );
		ImageIcon lig_d = new ImageIcon(changedImg_lig_d);
		JButton image_lig_d =new JButton(lig_d);
		
		JLabel text_lig=new JLabel();
		
		text_lig.setText(" 밝기:  ");
		text_lig.setLocation(30,140);
		text_lig.setSize(90,50);
		image_lig_u.setSize(70, 70);
		image_lig_u.setLocation(120, 130);
		image_lig_d.setSize(70, 70);
		image_lig_d.setLocation(230, 130);
		image_lig_u.setBackground(Color.WHITE);
		image_lig_d.setBackground(Color.WHITE);
		
		time_b.setText("설정");
		time_b.setSize(60,20);
		time_b.setLocation(250, 255);
		time_b.setBackground(Color.WHITE);
		tmlabel.setSize(80,30);
		tmlabel.setLocation(20, 250);
		time.setSize(130,20);
		time.setLocation(110, 255);
		time_lb.setSize(150,20);
		time_lb.setLocation(100, 300);
		
		control.add(tmlabel);
		control.add(time);
		control.add(time_b);
		control.add(time_lb);
		
		image_lig_u.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(lig_u) && OF==true) {
					 index%=5;
					 light_l.setText("밝기  : " + ++index);
				 }
			}
		});
		
		image_lig_d.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
				 if(b.getIcon().equals(lig_d) && OF==true) {
					 if(index>0)
						 light_l.setText("밝기  : " + --index);
					}
			}
		});
		time_b.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals("설정")) {
						timer();
						time_b.setEnabled(false);
					}
						
			}
		});
		
		control.add(image_lig_u);
		control.add(image_lig_d);
		control.add(text_lig);
	}
	public void on_off_status() {
		if(OF==true) {
			of_l.setText("전원  : ON ");
			light_l.setText("밝기  : " + index);
		}
		else {
			of_l.setText("전원  : OFF");
			light_l.setText("밝기  : OFF ");
		}
	}
	
	public void timer() {
		TimerThread ts=new TimerThread();
		ts.start();
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				if(OF==false) {
					return;
				}
				try {
					for(int i=0;i<Integer.parseInt(time.getText());i++) {
						time_lb.setText(Integer.toString(Integer.parseInt(time.getText())-i)+" 초 후에 꺼집니다.");
						sleep(1000);
						time_lb.setText("");
					}
					time_b.setEnabled(true);
				}
				catch(InterruptedException e){return;}
				OF_B.setIcon(Icon_off);
				OF=On_Off_control();
				on_off_status();
			}
			
		}
	}
}


/*--------------------  User_Main   -----------------------*/
public class Management_system extends JFrame{ //main클래스

	
	private static Room[] room= new Room[20];
	public static String data1;
	public static String data[]=new String[30];
	public static int room_index=0;
	static JPanel p2=new JPanel();
	static Container c;
	public static String grade;
	
	public static JButton room_button;
	public static JButton thing_button;
	public static JButton search_button;
	
	Management_system() {		//생성자
		setTitle("IOT Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		
		JPanel p1=new JPanel();  //메뉴 패널
		p1.setLayout(null);
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
		
		room_button=new JButton("+ 방 추가");
		room_button.setSize(300, 50);
		room_button.setLocation(10,10);
		room_button.setBackground(Color.WHITE);
		room_button.addActionListener(new buttonevent_room());
		p1.add(room_button);
		
		thing_button=new JButton("+ IOT 디바이스 추가");
		thing_button.setSize(300, 50);
		thing_button.setLocation(325,10);
		thing_button.setBackground(Color.WHITE);
		thing_button.addActionListener(new buttonevent_room());
		p1.add(thing_button);
		
		ImageIcon originIcon = new ImageIcon("C:\\javawork\\돋보기.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		search_button=new JButton("  IOT 디바이스 검색",Icon);
		search_button.setSize(250, 50);
		search_button.setLocation(720,10);
		search_button.setBackground(Color.WHITE);
		search_button.addActionListener(new buttonevent_room());
		p1.add(search_button);
		
		c.add(p1);
		p1.setLocation(0,0);
		p1.setSize(1000,70);
		setSize(1000, 700); 
		setVisible(true); 
	}
	public static void insert_room() {
		for(int i=0;i<room_index;i++) {
			if(insert_frame.userText.getText().equals(data[i])) {
				JOptionPane.showMessageDialog(null,
						"같은 이름의 방이 존재합니다!", "Message",
						JOptionPane.ERROR_MESSAGE);
				system_start_log.check=-1;
				return;
			}
		}
		room[room_index]=new Room();
		room_index++;
		system_start_log.check=0;
	}
	
	public static int index;
	public static void insert_device() {
		index=insert_frame_device.list1.getSelectedIndex();
		
		for(int i=0;i<room[index].get_device_index();i++) {
			if(insert_frame_device.userText.getText().equals(room[index].get_device_name(i))) {
				JOptionPane.showMessageDialog(null,
						"같은 이름의 디바이스가 존재합니다!", "Message",
						JOptionPane.ERROR_MESSAGE);
				system_start_log.check_device=-1;
				return;
			}
		}
		room[index].device_insert();
		system_start_log.check_device=0;
	}
	public static void rep() {
		c.revalidate();
		c.repaint();
	}
	
	public static void main(String[] args) {
		Log_In lg=new Log_In();
	}
}
/*--------------------  Log_in    -----------------------*/

/*--------------  로그인 프레임  ----------------*/
class Log_In extends JFrame{
	
	TextField ID;
	TextField Passward;
	private int check=0;
	private String line;
	Log_In(){
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container l=getContentPane();
		l.setLayout(null);
		
		JButton newid=new JButton();
		newid.setText(" 회원가입 ");
		newid.setBackground(Color.WHITE);
		newid.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(" 회원가입 ")) {
						New_Id ne =new New_Id();
					}
				}
		});
		JButton login=new JButton();
		login.setText(" 로그인 ");
		login.setBackground(Color.WHITE);
		login.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(" 로그인 ")) {
						check();
						if(check==1) {
							try{
								eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
								long time = System.currentTimeMillis(); 
								SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								String str = dayTime.format(new Date(time));
								eventlog.log.write(str);
								eventlog.log.write("\r\t");
								eventlog.log.write(ID.getText());
								eventlog.log.write("  로그인 성공 \r\n");
								eventlog.log.close();
							}
							catch (IOException a) {
								System.out.println("입출력 오류");
							}
							dispose();
						}
					}
				}
		});
		
		Label idlabel = new Label(" ID :", Label.RIGHT);
		ID = new TextField(10);
		Label pwlabel = new Label("PassWard :", Label.RIGHT);
		Passward = new TextField(10);
		Passward.setEchoChar('*');
		
		newid.setSize(100, 30);
		newid.setLocation(175, 170);
		login.setSize(100, 30);
		login.setLocation(285, 170);
		
		idlabel.setSize(50, 30);
		idlabel.setLocation(190, 100);
		ID.setSize(130, 20);
		ID.setLocation(245, 105);
		
		pwlabel.setSize(80, 30);
		pwlabel.setLocation(160, 130);
		Passward.setSize(130, 20);
		Passward.setLocation(245, 135);
	
		l.add(newid);
		l.add(login);
		l.add(idlabel);
		l.add(ID);
		l.add(pwlabel);
		l.add(Passward);
		
		setSize(600,400);
		setVisible(true);
		eventlog ev=new eventlog();
	}
	public void check() {
		
		try {
			FileReader id_c=new FileReader("C:\\javawork\\userinfo.txt");
			BufferedReader buf=new BufferedReader(id_c);
			
			while((line=buf.readLine())!=null) {
				if(line.equals("ID")) {
					line=buf.readLine();
					if(line.equals(ID.getText())) {
						line=buf.readLine();
						line=buf.readLine();
						if(line.equals(Passward.getText())) {
							line=buf.readLine();
							if(line.equals("Master")) {
								JOptionPane.showMessageDialog(null,
										" Master회원 로그인 성공", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								Management_system qwe =new Management_system();
								Management_system.grade="Master";
								system_start_log st=new system_start_log();
								check=1;
								break;
							}
							else if(line.equals("Gold")) {
								JOptionPane.showMessageDialog(null,
										" Gold회원 로그인 성공", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								Management_system qwe =new Management_system();
								Management_system.grade="Gold";
								system_start_log st=new system_start_log();
								check=1;
								break;
							}
							else {
								JOptionPane.showMessageDialog(null,
										" Nomal회원 로그인 성공", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								Management_system qwe =new Management_system();
								Management_system.grade="Nomal";
								system_start_log st=new system_start_log();
								Management_system.thing_button.setEnabled(false);
								Management_system.room_button.setEnabled(false);
								check=1;
								break;
							}
						}
						else {
							JOptionPane.showMessageDialog(null,
									"비밀번호가 다릅니다!", "Message",
									JOptionPane.ERROR_MESSAGE);
							check=-1;
							break;
						}
					}
					
				}
			}
			if(check==0) {
				JOptionPane.showMessageDialog(null,
						"아이디가 다릅니다!", "Message",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}
/*--------------  회원가입  ----------------*/
class New_Id extends JFrame{
	
	TextField Name;
	TextField ID;
	TextField Passward;
	
	JRadioButton master;
	JRadioButton gold;
	JRadioButton nomal;
	
	private String line;
	
	New_Id(){
		setTitle(" 회원가입 ");
		Container n=getContentPane();
		n.setLayout(null);
		
		Label namelabel = new Label(" Name 입력 :", Label.RIGHT);
		Name = new TextField(10);
		Label idlabel = new Label(" ID 입력 :", Label.RIGHT);
		ID = new TextField(10);
		Label pwlabel = new Label("PassWard 입력 :", Label.RIGHT);
		Passward = new TextField(10);
		Passward.setEchoChar('*');
		
		JButton newid=new JButton();
		newid.setText(" 회원가입 ");
		newid.setBackground(Color.WHITE);
		newid.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JButton b = (JButton)e.getSource();
					if(b.getText().equals(" 회원가입 ")) {
						if(overlap()) {
							Save_ID();
							try{
								eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
								long time = System.currentTimeMillis(); 
								SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								String str = dayTime.format(new Date(time));
								eventlog.log.write(str);
								eventlog.log.write("\r\t");
								eventlog.log.write(Name.getText());
								eventlog.log.write("  회원등록  \r\n");
								eventlog.log.close();
							}
							catch (IOException a) {
								System.out.println("입출력 오류");
							}
							dispose();
						}
					}
				}
		});
		
		ButtonGroup group = new ButtonGroup();
		master= new JRadioButton("Master 등급");
		gold= new JRadioButton("Gold 등급");
		nomal= new JRadioButton("Nomal 등급");

		group.add(master);
		group.add(gold);
		group.add(nomal);
		
		JLabel box1=new JLabel();
		box1.setLayout(new FlowLayout());
		
		box1.add(master);
		box1.add(gold);
		box1.add(nomal);
		box1.setSize(400, 100);
		box1.setLocation(0, 200);
		n.add(box1);

		
		newid.setSize(100, 30);
		newid.setLocation(140, 235);
		
		namelabel.setSize(80, 30);
		namelabel.setLocation(60, 90);
		Name.setSize(130, 20);
		Name.setLocation(155, 95);
		
		idlabel.setSize(50, 30);
		idlabel.setLocation(90, 130);
		ID.setSize(130, 20);
		ID.setLocation(155, 135);
		
		pwlabel.setSize(100, 30);
		pwlabel.setLocation(40, 170);
		Passward.setSize(130, 20);
		Passward.setLocation(155, 175);
	
		n.add(namelabel);
		n.add(Name);
		n.add(idlabel);
		n.add(ID);
		n.add(pwlabel);
		n.add(Passward);
		n.add(newid);
		
		setSize(400,400);
		setVisible(true);
	}
	public boolean overlap() {
		
		try {
			FileReader id_ck=new FileReader("C:\\javawork\\userinfo.txt");
			BufferedReader buf=new BufferedReader(id_ck);
			
			while((line=buf.readLine())!=null) {
				if(line.equals("ID")) {
					line=buf.readLine();
					if(line.equals(ID.getText())) {
						JOptionPane.showMessageDialog(null,
								"이미 중복된 아이디가 존재합니다!", "Message",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}
					
				}
			}
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
		return true;
	}
	public void Save_ID() {
		try {
			FileWriter id=new FileWriter("C:\\javawork\\userinfo.txt",true);
			id.write("name\r\n");
			id.write(Name.getText());
			id.write("\r\n");
			id.write("ID\r\n");
			id.write(ID.getText());
			id.write("\r\n");
			id.write("PW\r\n");
			id.write(Passward.getText());
			id.write("\r\n");
			if(master.isSelected())
				id.write("Master\r\n");
			if(gold.isSelected())
				id.write("Gold\r\n");
			if(nomal.isSelected())
				id.write("Nomal\r\n");
			id.write("\r\n\r\n\r\n"); 
			id.close();
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}
/*--------------------  이벤트 로그   -----------------------*/
class eventlog{
	public static FileWriter log;
	eventlog(){
		try{
			log=new FileWriter("C:\\javawork\\event_log.txt",true);
			log.write("<< 이벤트 로그  >> \r\n");
			log.close();
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}

/*--------------------  시스템 로그   -----------------------*/
class system_start_log{
	String line;
	public static int check=0;
	public static int check_device=0;

	public static FileReader sr_log;
	public static FileWriter sw_log;
	system_start_log(){
		try {
			sw_log=new FileWriter("C:\\javawork\\System_log.txt",true);
			sr_log=new FileReader("C:\\javawork\\System_log.txt");
			BufferedReader buf=new BufferedReader(sr_log);
			while((line=buf.readLine())!=null) {
				if(line.equals("방 등록 ")) {
					line=buf.readLine();
					insert_frame.userText.setText(line);
					Management_system.insert_room();
					insert_frame.userText.setText(" ");
				}
				else if(line.equals("디바이스 등록 ")) {
					line=buf.readLine();
					insert_frame_device.list1.setSelectedIndex(Integer.parseInt(line));
					line=buf.readLine();
					insert_frame_device.userText.setText(line);
					line=buf.readLine();
					if(line.equals("tv"))
						insert_frame_device.tv.setSelected(true);
					
					else if(line.equals("fan"))
						insert_frame_device.fan.setSelected(true);
					
					else if(line.equals("light"))
						insert_frame_device.light.setSelected(true);
					
					else if(line.equals("temp"))
						insert_frame_device.temp.setSelected(true);
					
					else if(line.equals("fire"))
						insert_frame_device.fire.setSelected(true);
					
					else if(line.equals("lllum"))
						insert_frame_device.lllum.setSelected(true);
					Management_system.insert_device();
					insert_frame_device.tv.setSelected(false);
					insert_frame_device.fan.setSelected(false);
					insert_frame_device.light.setSelected(false);
					insert_frame_device.temp.setSelected(false);
					insert_frame_device.fire.setSelected(false);
					insert_frame_device.lllum.setSelected(false);
					insert_frame.userText.setText("");
					insert_frame_device.list1.setSelectedIndex(0);
				}
			}
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}

/*--------------------  버튼 이벤트 처리     -----------------------*/
class buttonevent_room implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		JButton but = (JButton)e.getSource();
		if(but.getText().equals("+ 방 추가")) {
			insert_frame roomset=new insert_frame("방 추가","추가할 방 이름 :");
			}
		else if(but.getText().equals("방 추가")) {
			Management_system.insert_room();
			try{
				eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
				long time = System.currentTimeMillis(); 
				SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String str = dayTime.format(new Date(time));
				eventlog.log.write(str);
				eventlog.log.write("\r\t   새로운");
				eventlog.log.write(insert_frame.userText.getText());
				eventlog.log.write("  방등록  \r\n");
				eventlog.log.close();
				if(system_start_log.check!=-1) {
					system_start_log.sw_log=new FileWriter("C:\\javawork\\System_log.txt",true);
					system_start_log.sw_log.write("방 등록 \r\n");
					system_start_log.sw_log.write(insert_frame.userText.getText());
					system_start_log.sw_log.write("\r\n");
					system_start_log.sw_log.close();
				}
			}
			catch (IOException a) {
				System.out.println("입출력 오류");
			}
		}
		else if(but.getText().equals("+ IOT 디바이스 추가")) {
			if(Management_system.room_index==0) {
				JOptionPane.showMessageDialog(null,
						"디바이스를 등록할 방이 존재하지 않습니다!", "Message",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				insert_frame_device div=new insert_frame_device("device 등록","디바이스 이름 :");
			}
		}
		else if(but.getText().equals("device 등록")) {
			Management_system.insert_device();
			try{
				eventlog.log=new FileWriter("C:\\javawork\\event_log.txt",true);
				long time = System.currentTimeMillis(); 
				SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String str = dayTime.format(new Date(time));
				eventlog.log.write(str);
				eventlog.log.write("\r\t   새로운");
				eventlog.log.write("  디바이스 등록  \r\n");
				eventlog.log.close();
				
				if(system_start_log.check_device!=-1) {
					system_start_log.sw_log=new FileWriter("C:\\javawork\\System_log.txt",true);
					system_start_log.sw_log.write("디바이스 등록 \r\n");
					system_start_log.sw_log.write(Integer.toString(insert_frame_device.list1.getSelectedIndex()));
					system_start_log.sw_log.write("\r\n");
					system_start_log.sw_log.write(insert_frame_device.userText.getText());
					system_start_log.sw_log.write("\r\n");
					
					if(insert_frame_device.tv.isSelected()) 
						system_start_log.sw_log.write("tv\r\n");
					else if(insert_frame_device.fan.isSelected())
						system_start_log.sw_log.write("fan\r\n");
					
					else if(insert_frame_device.light.isSelected())
						system_start_log.sw_log.write("light\r\n");
					
					else if(insert_frame_device.temp.isSelected())
						system_start_log.sw_log.write("temp\r\n");
					
					else if(insert_frame_device.fire.isSelected())
						system_start_log.sw_log.write("fire\r\n");
					
					else if(insert_frame_device.lllum.isSelected())
						system_start_log.sw_log.write("lllum\r\n");
					
					system_start_log.sw_log.close();
				}
				
			}
			catch (IOException a) {
				System.out.println("입출력 오류");
			}
		}
		else if(but.getText().equals("  IOT 디바이스 검색")) {
			if(Room.get_all_device_index()==0) {
				JOptionPane.showMessageDialog(null,
						"검색할 디바이스가 존재하지 않습니다!", "Message",
						JOptionPane.ERROR_MESSAGE);
			}
			else{
				Search_Choice sc=new Search_Choice();
			}
		}
		
	}
}


package Employees;

import javax.swing.*;

//import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

class EmpMain extends JFrame implements ActionListener {
	JPanel dataPane, searchPane, menuPane;
	JPanel pan;
	JButton first, forward, back, last, insert, delete, update, search, clear;
	JTextField name, birth, phone, addr, email, count;

	int n = 1;

	public EmpMain(String title) {
		super(title);

		dataPane();
		searchPane();
		menuPane();

		pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.add(searchPane, "Center");
		pan.add(menuPane, "South");

		super.add(dataPane, "Center");
		super.add(pan, "South");

		super.setSize(400, 300);
		super.setVisible(true);

		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		event();
		
		EmpBiz biz = new EmpBiz();
		EmpDTO dto = biz.getDTO(n);
		setDTO(dto);
	}

	public void dataPane() {
		dataPane = new JPanel();
		dataPane.setLayout(new GridLayout(5, 1));
		name = new JTextField();
		birth = new JTextField();
		phone = new JTextField();
		addr = new JTextField();
		email = new JTextField();

		dataPane.setBorder(BorderFactory.createTitledBorder("데이터"));
		JLabel lb1 = new JLabel("이름");
		JLabel lb2 = new JLabel("생년월일(- 로 구분)");
		JLabel lb3 = new JLabel("전화번호");
		JLabel lb4 = new JLabel("주소");
		JLabel lb5 = new JLabel("E-mail");
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb2.setHorizontalAlignment(JLabel.CENTER);
		lb3.setHorizontalAlignment(JLabel.CENTER);
		lb4.setHorizontalAlignment(JLabel.CENTER);
		lb5.setHorizontalAlignment(JLabel.CENTER);

		dataPane.add(lb1);
		dataPane.add(name);
		dataPane.add(lb2);
		dataPane.add(birth);
		dataPane.add(lb3);
		dataPane.add(phone);
		dataPane.add(lb4);
		dataPane.add(addr);
		dataPane.add(lb5);
		dataPane.add(email);
	}

	public void searchPane() {
		searchPane = new JPanel();
		first = new JButton("첫번쨰◀");
		forward = new JButton("  ◀  ");
		count = new JTextField(5);
		count.setFont(new Font("맑은고딕", Font.BOLD, 17));
		back = new JButton("  ▶  ");
		last = new JButton("▶+10");

		searchPane.setBorder(BorderFactory.createTitledBorder("데이터 조회"));
		searchPane.add(first);
		searchPane.add(forward);
		searchPane.add(count);
		searchPane.add(back);
		searchPane.add(last);
		
		count.setText("1");
	}

	public void menuPane() {
		menuPane = new JPanel();
		insert = new JButton("추가");
		delete = new JButton("삭제");
		update = new JButton("수정");
		search = new JButton("조회");
		clear = new JButton("지움");

		menuPane.setBorder(BorderFactory.createTitledBorder("메뉴"));
		menuPane.add(insert);
		menuPane.add(delete);
		menuPane.add(update);
		menuPane.add(search);
		menuPane.add(clear);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			EmpBiz biz = new EmpBiz();
			EmpDTO dto = setDTOValue();
			int n = biz.insertDTO(dto);
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "삽입 성공");
				erase();
			} else
				JOptionPane.showMessageDialog(null, "삽입 실패");
		}

		if (e.getSource() == delete) {
			EmpBiz biz = new EmpBiz();
			String name = this.name.getText();
			String phone = this.phone.getText();
			int n = biz.deleteDTO(name, phone);
			if (n > 0) {
				JOptionPane.showMessageDialog(null, "삭제 성공");
			} else
				JOptionPane.showMessageDialog(null, "삭제 실패");

			erase();
		}

		if (e.getSource() == update) {
			String name = this.name.getText();
			EmpBiz biz = new EmpBiz();
			EmpDTO dto = setDTOValue();
			int cnt = Integer.parseInt(count.getText());
			int n = biz.updateDTO(dto, cnt);

			if (n > 0) {
				JOptionPane.showMessageDialog(null, name + "님의 데이터 수정 성공");
			} else
				JOptionPane.showMessageDialog(null, "수정 실패");
		}

		if (e.getSource() == search) {
			EmpBiz biz = new EmpBiz();
			n = Integer.parseInt(count.getText());
			EmpDTO dto = biz.getDTO(n);
			erase();
			if (dto != null) {
				setDTO(dto);
			} else
				JOptionPane.showMessageDialog(null, "검색 실패");
		}

		if (e.getSource() == clear) {
			erase();
		}
		
		
		//--------------------------------------------------------------
		 if(e.getSource()==first) {
			EmpBiz biz = new EmpBiz();
			n=1;
			this.count.setText(String.valueOf(n));
			EmpDTO dto = biz.getDTO(n);
			setDTO(dto);
		 }
		 
		 if(e.getSource()==forward) {
			 if(n==1) 
				JOptionPane.showMessageDialog(null,"첫번째 사원입니다."); 
			 else { 
				EmpBiz biz = new EmpBiz();
				n--;
				this.count.setText(String.valueOf(n));
				EmpDTO dto = biz.getDTO(n);
				setDTO(dto); 
			 } 
		 }
		 
		 if(e.getSource()==back) { 
			EmpBiz biz = new EmpBiz();
			n++;
			this.count.setText(String.valueOf(n));
			EmpDTO dto = biz.getDTO(n);
			setDTO(dto);
		 }
		 
		 if(e.getSource()==last) {
			EmpBiz biz = new EmpBiz();
			n=n+10;
			this.count.setText(String.valueOf(n));
			EmpDTO dto = biz.getDTO(n);
			setDTO(dto);
		 }
		 
		 
	}

	public void event() {
		first.addActionListener(this);
		forward.addActionListener(this);
		back.addActionListener(this);
		last.addActionListener(this);
		insert.addActionListener(this);
		delete.addActionListener(this);
		update.addActionListener(this);
		search.addActionListener(this);
		clear.addActionListener(this);
	}

	public void erase() {
		name.setText("\0");
		birth.setText("\0");
		phone.setText("\0");
		addr.setText("\0");
		email.setText("\0");
	}

	public EmpDTO setDTOValue() {
		EmpDTO dto = new EmpDTO();
		dto.setName(name.getText().trim());
		dto.setBirth(birth.getText().trim());
		dto.setPhone(phone.getText().trim());
		dto.setAddr(addr.getText().trim());
		dto.setEmail(email.getText().trim());
		return dto;
	}

	private void setDTO(EmpDTO dto) {
		if (dto.getName() != null)
			this.name.setText(dto.getName());
		else
			this.name.setText("(퇴사완료된 사원.)");

		if (dto.getBirth() != null)
			this.birth.setText(dto.getBirth());
		else
			this.birth.setText("(퇴사완료된 사원.)");

		if (dto.getPhone() != null)
			this.phone.setText(dto.getPhone());
		else
			this.phone.setText("(퇴사완료된 사원.)");

		if (dto.getAddr() != null)
			this.addr.setText(dto.getAddr());
		else
			this.addr.setText("(퇴사완료된 사원.)");

		if (dto.getEmail() != null)
			this.email.setText(dto.getEmail());
		else
			this.email.setText("(퇴사완료된 사원.)");

	}

	public static void main(String[] args) throws Exception {
		new EmpMain("사원 데이터베이스");
	}
}

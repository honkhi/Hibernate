package GUI;

import Main.Main;
import MyCustom.ImagePanel;
import MyCustom.MyDialog;
import BUS.DangNhapBUS;
import DTO.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DangNhapGUI extends JFrame {

    public DangNhapGUI() {
        this.setTitle("Đăng nhập");
        this.setSize(440, 624);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        addControls();
        xuLyTaiKhoanDaGhiNho();
        addEvents();
    }

    private void xuLyTaiKhoanDaGhiNho() {
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        String line = dangNhapBUS.getTaiKhoanGhiNho();
        try {
            String[] arr = line.split(" | ");
            ckbRemember.setSelected(true);
            txtUser.setText(arr[0]);
            txtPassword.setText(arr[2]);
            txtUser.requestFocus();
        } catch (Exception e) {
            txtUser.setText("");
            txtPassword.setText("");
            txtUser.requestFocus();
        }
    }

    private JLabel btnExit, btnLogin;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JPanel pnMain;
    private JCheckBox ckbRemember;

    private void addControls() {
        Container con = getContentPane();

        pnMain = new ImagePanel("src/main/resources/image/LoginUI/background-login.png");
        pnMain.setLayout(null);

        btnExit = new JLabel(new ImageIcon("src/main/resources/image/LoginUI/btn-close.png"));
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.setBounds(399, 118, 40, 40);

        btnLogin = new JLabel(new ImageIcon("src/main/resources/image/LoginUI/btn-login.png"));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(24, 513, 392, 55);

        

        Font fontTXT = new Font("", Font.BOLD, 18);
        txtUser = new JTextField();
        txtUser.setBackground(new Color(0, 0, 0, 0f));
        txtUser.setBorder(BorderFactory.createEmptyBorder());
        txtUser.setForeground(Color.WHITE);
        txtUser.setFont(fontTXT);
        txtUser.setHorizontalAlignment(JTextField.LEFT);
        txtUser.setBounds(36, 302, 370, 50);

        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('•');
        txtPassword.setBackground(new Color(0, 0, 0, 0f));
        txtPassword.setBorder(BorderFactory.createEmptyBorder());
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setFont(fontTXT);
        txtPassword.setHorizontalAlignment(JTextField.LEFT);
        txtPassword.setBounds(36, 401, 370, 50);

        try {
			Main.changLNF("Windows");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ckbRemember = new JCheckBox("Ghi nhớ đăng nhập");
        ckbRemember.setFont(fontTXT);
        ckbRemember.setOpaque(false);
        ckbRemember.setForeground(Color.white);
        ckbRemember.setBounds(28, 464, 290, 19);
        ckbRemember.setFocusPainted(false);
        try {
			Main.changLNF("Nimbus");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        pnMain.add(btnExit);
        pnMain.add(txtUser);
        pnMain.add(txtPassword);
        pnMain.add(ckbRemember);
        pnMain.add(btnLogin);
        

        con.add(pnMain);
    }

    private void addEvents() {
        moveFrame();
        btnExit.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                xuLyThoat();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                btnExit.setIcon(new ImageIcon("src/main/resources/image/LoginUI/btn-close--hover.png"));
            }

            public void mouseExited(MouseEvent e) {
                btnExit.setIcon(new ImageIcon("src/main/resources/image/LoginUI/btn-close.png"));
            }
        });
        txtUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPassword.requestFocus();
            }
        });
        txtPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					xuLyDangNhap();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        btnLogin.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
					xuLyDangNhap();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                btnLogin.setIcon(new ImageIcon("src/main/resources/image/LoginUI/btn-login--hover.png"));
            }

            public void mouseExited(MouseEvent e) {
                btnLogin.setIcon(new ImageIcon("src/main/resources/image/LoginUI/btn-login.png"));
            }
        });
    }

    int xMouse, yMouse;

    private void moveFrame() {
        pnMain.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Move(x, y);
            }

            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void xuLyThoat() {
        System.exit(0);
    }

    private void xuLyQuenMatKhau() {
        try {
			new MyDialog("Xin liên hệ Admin để giải quyết!", MyDialog.INFO_DIALOG);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @SuppressWarnings("deprecation")
	private void xuLyDangNhap() throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        TaiKhoan tk = dangNhapBUS.getTaiKhoanDangNhap(txtUser.getText(),
                txtPassword.getText(), ckbRemember.isSelected());
        if (tk != null) {
            this.dispose();
            MainQuanLyGUI gui = new MainQuanLyGUI();
            this.dispose();
            gui.showWindow();
        }
    }

    public void showWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
        this.setVisible(true);
    }

}
package studentdb;

import javax.swing.*;

public class loginpage {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title =
                new JLabel("STUDENT DATABASE SYSTEM");

        title.setBounds(90, 20, 250, 30);
        frame.add(title);

        JLabel lblUser =
                new JLabel("Username");

        lblUser.setBounds(50, 80, 100, 25);
        frame.add(lblUser);

        JTextField txtUser =
                new JTextField();

        txtUser.setBounds(150, 80, 150, 25);
        frame.add(txtUser);

        JLabel lblPass =
                new JLabel("Password");

        lblPass.setBounds(50, 120, 100, 25);
        frame.add(lblPass);

        JPasswordField txtPass =
                new JPasswordField();

        txtPass.setBounds(150, 120, 150, 25);
        frame.add(txtPass);

        JButton btnLogin =
                new JButton("Login");

        btnLogin.setBounds(140, 180, 100, 30);
        frame.add(btnLogin);

        btnLogin.addActionListener(e -> {

            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if(user.equals("admin")
                    && pass.equals("123")) {

                frame.dispose();

                StudentReportViewer.main(null);

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Login"
                );
            }
        });

        frame.setVisible(true);
    }
}
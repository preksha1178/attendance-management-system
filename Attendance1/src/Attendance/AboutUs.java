package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

public class AboutUs {
    public void showAboutUs() {
        // Frame setup
        JFrame frame = new JFrame();
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#f1faee"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //-----------------------BACK---------------------------------
        JLabel back = new JLabel("");
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
        back.setForeground(new Color(255, 255, 255));
        back.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        back.setBounds(0, 0, 40, 35);
        frame.getContentPane().add(back);

        // Mouse listener to detect hover effect
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
                back.setOpaque(true);
                back.setBackground(new Color(211, 211, 211));
                back.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
                back.setForeground(new Color(255, 255, 255));
                back.setOpaque(false);
                back.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        // Draggable panel for the frame
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 35);
        headerPanel.setBackground(Color.decode("#457b9d"));
        headerPanel.setLayout(null);
        frame.getContentPane().add(headerPanel);

        // Close button
        JLabel closeLabel = new JLabel("");
        closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeLabel.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-cross-22.png"));
        closeLabel.setBounds(955, 0, 45, 35);
        headerPanel.add(closeLabel);

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setOpaque(true);
                closeLabel.setBackground(Color.RED);
                closeLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeLabel.setOpaque(false);
                closeLabel.repaint();
            }
        });

        //--------------------------------------------------------------
        // Minimize button
        JLabel minimizeLabel = new JLabel("");
        minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minimizeLabel.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-minimize-18 (1).png"));
        minimizeLabel.setBounds(910, 0, 45, 35);
        headerPanel.add(minimizeLabel);

        minimizeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeLabel.setOpaque(true);
                minimizeLabel.setBackground(new Color(211, 211, 211));
                minimizeLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizeLabel.setOpaque(false);
                minimizeLabel.repaint();
            }
        });

        // About Us Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#457b9d"), 2));
        contentPanel.setPreferredSize(new java.awt.Dimension(900, 600)); // Set preferred size for scrolling

        // Add content to contentPanel
        JLabel aboutTitle = new JLabel("About Us");
        aboutTitle.setHorizontalAlignment(SwingConstants.CENTER);
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 32));
        aboutTitle.setForeground(Color.decode("#1d3557"));
        aboutTitle.setBounds(350, 20, 200, 40);
        contentPanel.add(aboutTitle);

        JLabel aboutContent = new JLabel("<html>"
                + "<p style='font-size:16px; color:#1d3557; text-align:justify;'>"
                + "<b>Welcome to the Attendance Management System</b> - A modern solution to track and manage attendance effortlessly.<br><br>"
                + "Our Attendance Management System is built with the goal of simplifying the process of tracking attendance for both educational institutions and organizations. "
                + "Key features include:<br>"
                + "<ul style='font-size:16px;'>"
                + "<li>QR Code-based attendance marking for quick and accurate tracking.</li>"
                + "<li>Real-time attendance updates, allowing for immediate reporting.</li>"
                + "<li>Role Based Login Access </li>"
                + "<li>Increased Productivity with reduced overheads costs </li>"
                + "<li>Easy-to-use interface built using Java Swing and MySQL database integration.</li>"
                + "</ul>"
                + "With our system, institutions can eliminate the errors and time spent on manual attendance tracking, helping educators focus more on what matters most - teaching.<br><br>"
                + "For any queries or support, please contact us at <b>support@attendance.com</b>.</p>"
                + "</html>");

        aboutContent.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutContent.setForeground(Color.decode("#1d3557"));
        aboutContent.setBounds(20, 0, 852, 570); // Adjust size to ensure content is visible
        contentPanel.add(aboutContent);

        // Add JScrollPane to the frame for scroll functionality
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(50, 100, 900, 400); // Set the bounds of the scrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);

        // Make header draggable
        addFrameDragFunctionality(frame, headerPanel);

        // Show frame
        frame.setVisible(true);
    }

    // Method to enable dragging for undecorated frames
    private void addFrameDragFunctionality(JFrame frame, JPanel headerPanel) {
        final Point[] mouseDownCompCoords = {null};
        headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords[0] = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords[0] = null;
            }
        });

        headerPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - mouseDownCompCoords[0].x, currCoords.y - mouseDownCompCoords[0].y);
            }
        });
    }

    public static void main(String[] args) {
        new AboutUs().showAboutUs();
    }
}

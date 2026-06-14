package studentdb;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class StudentReportViewer {

    public static void main(String[] args) {

        JFrame frame =
                new JFrame("Resume Database Management System");

        frame.setSize(900, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading =
                new JLabel("RESUME DATABASE MANAGEMENT SYSTEM");

        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(180, 20, 500, 30);

        frame.add(heading);

        JLabel dateLabel =
                new JLabel("Date : " +
                        java.time.LocalDate.now());

        dateLabel.setBounds(700, 20, 150, 30);

        frame.add(dateLabel);

        JButton btnUpload =
                new JButton("Upload Resume PDF");

        btnUpload.setBounds(50, 60, 180, 30);

        frame.add(btnUpload);

        JButton btnReport =
                new JButton("Generate Report");

        btnReport.setBounds(250, 60, 150, 30);

        frame.add(btnReport);

        String[] columns = {
                "Name",
                "Email",
                "Degree",
                "Skills"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table =
                new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(50, 110, 800, 280);

        frame.add(scrollPane);

        JLabel totalLabel =
                new JLabel("Total Records : 0");

        totalLabel.setBounds(50, 410, 200, 30);

        frame.add(totalLabel);

        JLabel footer =
                new JLabel("Developed By : Mahek Mulla");

        footer.setBounds(650, 450, 220, 30);

        frame.add(footer);

        // Upload Resume PDF

        btnUpload.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            int result =
                    chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {

                File file =
                        chooser.getSelectedFile();

                try {

                    PDDocument document =
                            Loader.loadPDF(file);

                    PDFTextStripper stripper =
                            new PDFTextStripper();

                    String text =
                            stripper.getText(document);

                    document.close();

                    String name = "";
                    String email = "";
                    String degree = "";
                    String skills = "";

                    String[] lines =
                            text.split("\\r?\\n");

                    for (String line : lines) {

                        line = line.trim();

                        if (email.isEmpty()
                                && line.contains("@")) {

                            email = line;
                        }

                        

                        if (degree.isEmpty()
                                && (line.contains("BE")
                                || line.contains("B.E")
                                || line.contains("Bachelor")
                                || line.contains("Engineering")
                                || line.contains("Computer Science"))) {

                            degree = line;
                        }

                        if (skills.isEmpty()
                                && (line.toLowerCase().contains("java")
                                || line.toLowerCase().contains("python")
                                || line.toLowerCase().contains("sql")
                                || line.toLowerCase().contains("html")
                                || line.toLowerCase().contains("css"))) {

                            skills = line;
                        }
                    }

                    if (lines.length > 0) {
                        name = lines[0];
                    }

                    model.addRow(new Object[]{
                            name,
                            email,
                            degree,
                            skills
                    });

                    totalLabel.setText(
                            "Total Records : "
                                    + model.getRowCount());

                    JOptionPane.showMessageDialog(
                            frame,
                            "Resume Uploaded Successfully!");

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Error Reading PDF:\n"
                                    + ex.getMessage());
                }
            }
        });

        // Generate Report

        btnReport.addActionListener(e -> {

            JFrame reportFrame =
                    new JFrame("Resume Report");

            reportFrame.setSize(600, 500);

            JTextArea area =
                    new JTextArea();

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "========== RESUME REPORT ==========\n\n");

            for (int i = 0;
                 i < model.getRowCount();
                 i++) {

                report.append("Name : ")
                        .append(model.getValueAt(i, 0))
                        .append("\n");

                report.append("Email : ")
                        .append(model.getValueAt(i, 1))
                        .append("\n");

                report.append("Degree : ")
                        .append(model.getValueAt(i, 3))
                        .append("\n");

                report.append("Skills : ")
                        .append(model.getValueAt(i, 4))
                        .append("\n");

                report.append(
                        "----------------------------------\n");
            }

            report.append(
                    "\nTotal Records : "
                            + model.getRowCount());

            area.setText(report.toString());

            reportFrame.add(
                    new JScrollPane(area));

            reportFrame.setVisible(true);
        });

        frame.setVisible(true);
    }
}
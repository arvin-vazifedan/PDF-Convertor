import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Frame {
    private static final JFrame jFrame = new JFrame();
    private final JFileChooser fc = new JFileChooser();
    private JPanel panel;
    private JComboBox<String> comboBox;
    private JButton convert;
    private JButton chooser;
    private JLabel label;
    private File file;

    public Frame() {
        comboBox.addItem("txt");
        comboBox.addItem("html");
        chooser.addActionListener(e -> {
            //added filter to show only directories and pdf files.
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName().toLowerCase();
                    return name.endsWith(".pdf") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "PDF Files";
                }
            };
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(panel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                String name = file.getName();
                if (file.getName().length() > 20)
                    name = file.getName().substring(0, 20) + "...";
                chooser.setText(name);
            }
        });

        convert.addActionListener(e -> {
            if (file == null) {
                JOptionPane.showMessageDialog(jFrame,
                    "Please Choose a PDF file first.",
                    "Alert",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                String extension = String.valueOf(comboBox.getSelectedItem());
                //get file path without file extension
                String filePath = file.getPath();
                filePath = file.getPath().substring(0, filePath.indexOf("."));

                String title = "The operation was successful";
                String msg = "The new file is saved next to the original file.";
                Util util = new Util();
                switch (extension) {
                    case "txt" -> {
                        util.toText(file, filePath + ".txt");
                        JOptionPane.showMessageDialog(jFrame,
                            msg,
                            title,
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    case "html" -> {
                        util.toHTML(file, filePath + ".html");
                        JOptionPane.showMessageDialog(jFrame,
                            msg,
                            title,
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + extension);
                }
            }
        });
    }

    public static void main(String[] args) {
        new Frame().createAndShowGUI();
    }

    private void createAndShowGUI() {
        //Change look and feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatDraculaIJTheme.setup();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Creating and showing JFrame
        jFrame.setContentPane(new Frame().panel);
        jFrame.setTitle("PDF Converter");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(450, 300);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}

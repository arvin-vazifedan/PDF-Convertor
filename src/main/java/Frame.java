import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class Frame {
    private final String[] formats = {"txt", "html", "Docx", "Doc"};
    private final JFileChooser fc = new JFileChooser();
    JComboBox<String> comboBox;
    private JPanel panel;
    private JButton convert;
    private JButton chooser;
    private JLabel label;

    public Frame() {
        createUIComponents();

        chooser.addActionListener(e -> {
            if (e.getSource() == chooser) {
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
                    File file = fc.getSelectedFile();
                    String name = file.getName();
                    if (file.getName().length() > 30)
                        name = file.getName().substring(0, 30) + "...";
                    chooser.setText(name);
                }
            }
        });

        convert.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new Frame().panel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(229, 211, 206, 255));
        frame.setVisible(true);
    }

    private void createUIComponents() {
        comboBox = new JComboBox<>(formats);
    }
}

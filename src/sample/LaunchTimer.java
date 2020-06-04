package sample;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class LaunchTimer{
    MediaPlayer mediaPlayer;
    double timeForSeparator;
    int SeparatorInteger = 0;

    public LaunchTimer(List separatorList,  double totalTime) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("LaunchTimer");
                frame.add(new LaunchTimerPane(separatorList, totalTime));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class LaunchTimerPane extends JPanel {

        private JLabel label;
        private Timer timer;
        long firstTime = System.currentTimeMillis();

        public LaunchTimerPane(List separatorList,  double totalTime) {
            setLayout(new GridBagLayout());
            label = new JLabel(String.format("%04d:%02d:%02d.%03d", 0, 0, 0, 0));

            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long runningTime = System.currentTimeMillis() - firstTime;
                    Duration duration = Duration.ofMillis(runningTime);
                    long hours = duration.toHours();
                    duration = duration.minusHours(hours);
                    long minutes = duration.toMinutes();
                    duration = duration.minusMinutes(minutes);
                    long millis = duration.toMillis();
                    long seconds = millis / 1000;
                    millis -= (seconds * 1000);
                    label.setText(String.format("%04d:%02d:%02d.%03d", hours, minutes, seconds, millis));
                    timeForSeparator = hours * 3600 + minutes * 60 + seconds;
                    if (separatorList.size() > SeparatorInteger &&  (int) timeForSeparator == (int) separatorList.get(SeparatorInteger)) {
                        musik();
                        ++SeparatorInteger;
                    }
                    if ((int) timeForSeparator == totalTime) {
                        timer.stop();
                        musik();
                    }
                }
            });

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(40, 40, 40, 40);
            add(label, gbc);

            JButton start = new JButton("START");
            start.setSize(175, 150);
            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!timer.isRunning()) {
                        timer.start();
                    }
                }
            });

            JButton stop = new JButton("PAUSE");
            stop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timer.stop();
                }
            });

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            add(start, gbc);
            gbc.gridx++;
            add(stop, gbc);
        }
    }

    public void musik() {
        String bip = "src/sample/kessidi-dzyn.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    final JFXPanel fxPanel = new JFXPanel();
    //Чтобы избежать инициализации исключения
}

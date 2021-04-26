import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main
{
    public static Window window;
    public static Cell[][] cells = new Cell[100][100];
    static int updateFrame = 0;
    public static void main(String[] args)
    {
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 100; j++)
            {
                cells[i][j] = new Cell(i,j);
            }
        }
        JFrame frame = new JFrame("Physics Simulation");
        window = new Window();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(window, BorderLayout.CENTER);
        frame.setSize(400,400);
        frame.pack();
        frame.setVisible(true);

        Timer drawLoop = new Timer();
        drawLoop.schedule(
            new TimerTask()
            {
                @Override
                public void run()
                {
                    for(Cell[] cells : Main.cells)
                    {
                        for(Cell cell : cells)
                        {
                            if(!cell.empty&&cell.lastUpdateFrame!=updateFrame)
                            {
                                cell.update();
                                cell.lastUpdateFrame = updateFrame;
                            }
                        }
                    }
                    updateFrame++;
                    window.repaint();
                }
            },
            0, 16
        );
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static Window window;
    public static Cell[][] cells = new Cell[100][100];
    public static ArrayList<Cell> cellsToUpdate = new ArrayList<>();
    public static ArrayList<Cell> cellsToUpdateQueue = new ArrayList<>();
    public static boolean running = true;
    static int updateFrame = 0;
    public static void main(String[] args) throws InterruptedException
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

        while(running)
        {
            long t = System.currentTimeMillis();
            for(Cell cell : cellsToUpdate)
            {
                cell.updateWrapper(updateFrame);
            }
            cellsToUpdate = cellsToUpdateQueue;
            cellsToUpdateQueue = new ArrayList<>();
            updateFrame++;
            window.repaint();
            TimeUnit.MILLISECONDS.sleep(16-(System.currentTimeMillis()-t));
        }
    }

    public static void requestUpdate(Cell cell)
    {
        cellsToUpdateQueue.add(cell);
    }

    public static void spawnCell(MouseEvent e)
    {
        if(!window.mousePressed)
            return;
        try
        {
            Cell c;
            switch (window.selectedType)
            {
                case 1 -> c = new Sand(e.getX() / 4, e.getY() / 4);
                case 2 -> c = new Gravel(e.getX() / 4, e.getY() / 4);
                case 3 -> c = new Water(e.getX() / 4, e.getY() / 4);
                case 4 -> c = new Wood(e.getX() / 4, e.getY() / 4);
                case 5 -> c = new Fire(e.getX() / 4, e.getY() / 4);
                default -> c = new Stone(e.getX() / 4, e.getY() / 4);
            }
            cells[e.getX() / 4][e.getY() / 4] = c;
            requestUpdate(c);
            window.repaint();
        }
        catch (IndexOutOfBoundsException ignored)
        {
        }
    }

    public static void destroyCell(int x, int y)
    {
        cells[x][y] = new Cell(x, y);
    }
}

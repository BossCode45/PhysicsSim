import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Window extends JPanel
{
    public int button = 0;
    public ArrayList<int[]> particlePlacements = new ArrayList<>();
    public boolean mousePressed = false;
    public Window()
    {
        setFocusable(true);
        addKeyListener(new KeyboardInput());
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseMotionInput());
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(0,0,0));
        for(Cell[] cells : Main.cells)
        {
            for(Cell cell : cells)
            {
                if(!cell.empty)
                    cell.draw(g);
            }
        }
    }
}

class KeyboardInput extends KeyAdapter
{
}

class MouseInput extends MouseAdapter
{
    @Override
    public void mousePressed(MouseEvent e)
    {
        Main.window.mousePressed = true;
        Main.window.button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        Main.window.mousePressed = false;
        Main.window.button = e.getButton();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        try
        {
            if (e.getButton()==1)
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Stone(e.getX() / 4, e.getY() / 4);
            } else
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Sand(e.getX() / 4, e.getY() / 4);
            }
        }
        catch (IndexOutOfBoundsException ignored)
        {
        }
    }
}

class MouseMotionInput extends MouseMotionAdapter
{
    @Override
    public void mouseMoved(MouseEvent e)
    {
        try
        {
            if (Main.window.mousePressed && Main.window.button == 1)
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Stone(e.getX() / 4, e.getY() / 4);
            } else if (Main.window.mousePressed)
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Sand(e.getX() / 4, e.getY() / 4);
            }
        }
        catch (IndexOutOfBoundsException ignored)
        {
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        try
        {
            if (Main.window.mousePressed && Main.window.button == 1)
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Stone(e.getX() / 4, e.getY() / 4);
            } else if (Main.window.mousePressed)
            {
                Main.cells[e.getX() / 4][e.getY() / 4] = new Sand(e.getX() / 4, e.getY() / 4);
            }
        }
        catch (IndexOutOfBoundsException ignored)
        {
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JPanel
{
    public int selectedType = 0;
    public boolean mousePressed = false;
    public Window()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(400, 400));
        addKeyListener(new KeyboardInput());
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseMotionInput());
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
                cell.draw(g);
            }
        }
    }
}

class KeyboardInput extends KeyAdapter
{
    @Override
    public void keyTyped(KeyEvent e)
    {
        Main.window.selectedType++;
        if(Main.window.selectedType>6)
            Main.window.selectedType=0;
    }
}

class MouseInput extends MouseAdapter
{
    @Override
    public void mousePressed(MouseEvent e)
    {
        Main.window.mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        Main.window.mousePressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Main.window.mousePressed = true;
        Main.spawnCell(e);
        Main.window.mousePressed = false;
    }
}

class MouseMotionInput extends MouseMotionAdapter
{
    @Override
    public void mouseMoved(MouseEvent e)
    {
        Main.spawnCell(e);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        Main.spawnCell(e);
    }
}
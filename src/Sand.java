import jdk.jfr.Name;

import java.awt.*;
import java.util.Random;

@Name("sand")
public class Sand extends Cell
{
    int moveMult;
    public Sand(int x, int y)
    {
        super(x, y);
        if(Math.random()>0.5)
            moveMult=1;
        else
            moveMult=-1;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(150,150,0));
        g.fillRect(x*4,y*4, 4, 4);
    }

    @Override
    public void update()
    {
        if(y<99&&Main.cells[x][y+1].empty)
            move(0,1);
        else if(y<99&&-1<x+moveMult&&x+moveMult<100&&Main.cells[x+moveMult][y+1].empty&&Main.cells[x+moveMult][y].empty)
        {
            move(moveMult,1);
        }
        else if(y<99&&-1<x-moveMult&&x-moveMult<100&&Main.cells[x-moveMult][y+1].empty&&Main.cells[x-moveMult][y].empty)
        {
            move(-moveMult,1);
        }
    }
}

import java.awt.*;

public class Oil extends Cell
{
    public Oil(int x, int y)
    {
        super(x, y);
        isLiquid = true;
        liquidWeight = 1;
        flammability = 0.2;
        startTicksUntilFireDeath = 100;
        canSetSameTypeOnFire = true;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(38, 38, 38));
        if(onFire)
            g.setColor(new Color(75,38,38));
        g.fillRect(x*4, y*4, 4, 4);
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
        else if(-1<x+moveMult&&x+moveMult<100&&Main.cells[x+moveMult][y].empty)
        {
            move(moveMult,0);
        }
        else if(-1<x-moveMult&&x-moveMult<100&&Main.cells[x-moveMult][y].empty)
        {
            move(-moveMult,0);
            moveMult*=-1;
        }
    }
}

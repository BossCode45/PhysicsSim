import java.awt.*;

public class Gravel extends Cell
{

    public Gravel(int x, int y)
    {
        super(x, y);

        sideMoveAmnt = 2;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(50,50,50));
        g.fillRect(x*4, y*4, 4, 4);
    }

    @Override
    public void update()
    {
        if(y<99&&Main.cells[x][y+1].empty)
            move(0,1);
        else if(y<99&&-1<x+moveMult&&x+moveMult<100&&Main.cells[x+moveMult][y+1].empty)
        {
            if(Main.cells[x+moveMult][y].empty)
                move(moveMult,1);
            else if(Main.cells[x+moveMult][y].isLiquid)
            {
                if(Main.cells[x+moveMult][y].liquidMoveUp())
                    move(moveMult, 1);
                else
                {
                    swap(moveMult, 1);
                }
            }
        }
        else if(y<99&&-1<x-moveMult&&x-moveMult<100&&Main.cells[x-moveMult][y+1].empty&&Main.cells[x-moveMult][y].empty)
        {
            move(-moveMult,1);
        }
        else if(y<99&&-1<x+moveMult*2&&x+moveMult*2<100&&Main.cells[x+moveMult*2][y+1].empty&&Main.cells[x+moveMult][y].empty&&Main.cells[x+moveMult*2][y].empty)
        {
            move(moveMult*2,1);
        }
        else if(y<99&&-1<x-moveMult*2&&x-moveMult*2<100&&Main.cells[x-moveMult*2][y+1].empty&&Main.cells[x-moveMult][y].empty&&Main.cells[x-moveMult*2][y].empty)
        {
            move(-moveMult*2,1);
        }
    }
}

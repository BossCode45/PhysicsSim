import java.awt.*;

public class Fire extends Cell
{
    public Fire(int x, int y)
    {
        super(x, y);

        onFire = true;
        canSetCellsOnFire = true;
        ticksUntilFireDeath = 100;
        startTicksUntilFireDeath = 100;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(255, 100, 0));
        g.fillRect(x*4, y*4, 4, 4);
    }
}

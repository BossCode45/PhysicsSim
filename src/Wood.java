import java.awt.*;

public class Wood extends Cell
{
    public Wood(int x, int y)
    {
        super(x, y);

        flamability = 0.5;
        startTicksUntilFireDeath = 200;
        canSetCellsOnFire = true;

    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(new Color(133, 94, 66));
        if(onFire)
            g.setColor(new Color(230, 94, 66));
        g.fillRect(x * 4, y * 4, 4, 4);
    }
}

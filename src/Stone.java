import java.awt.*;

public class Stone extends Cell
{
    public Stone(int x, int y)
    {
        super(x, y);
    }

    public void draw(Graphics g)
    {
        g.setColor(new Color(100,100,100));
        g.fillRect(x*4, y*4, 4, 4);
    }
}

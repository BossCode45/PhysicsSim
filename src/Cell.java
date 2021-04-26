import jdk.jfr.Name;

import java.awt.*;

@Name("empty")
public class Cell
{
    public int lastUpdateFrame = -1;
    public int x, y;
    public final double flamability = 0;
    public boolean empty = false;
    public String name;
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        name = getClass().getAnnotation(Name.class).value();
        if(name.equals("empty"))
            empty = true;
    }
    public void update() {}
    public void draw(Graphics g) {}
    public void move(int xAmt, int yAmt)
    {
        int oX = x;
        int oY = y;
        x += xAmt;
        y += yAmt;
        x = Usefull.clamp(0, 100, x);
        y = Usefull.clamp(0, 100, y);
        Main.cells[x][y] = Main.cells[oX][oY];
        Main.cells[oX][oY] = new Cell(oX, oY);
    }
}

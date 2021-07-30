import java.awt.*;

public class Cell
{
    public boolean isLiquid = false;
    public double liquidWeight = 0;
    public int lastUpdateFrame = -1;
    public int x, y;
    public double flammability = 0;
    public boolean empty = false;
    public String cellTypeName;
    public boolean onFire = false;
    int moveMult;
    static int sideMoveAmnt = 1;
    static int verticalMoveAmnt = 1;
    int currSideMove = 0;
    int currVerticalMove = 0;
    int updatesThisFrame = 0;
    public int startTicksUntilFireDeath = 1;
    int ticksUntilFireDeath = 1;
    boolean canSetCellsOnFire = false;
    boolean canSetSameTypeOnFire = false;
    private boolean changed = false;
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        cellTypeName = getClass().getName();
        if(cellTypeName.equals("Cell"))
        {
            empty = true;
        }
        if(Math.random()>0.5)
            moveMult=1;
        else
            moveMult=-1;
    }

    public void updateWrapper(int frame)
    {
        changed = false;
        if(frame!=lastUpdateFrame)
        {
            currSideMove = 0;
            currVerticalMove = 0;
            updatesThisFrame=0;
        }
        updatesThisFrame++;
        lastUpdateFrame = frame;
        if(updatesThisFrame>25)
            return;
        int pX = x;
        int pY = y;

        if(onFire&&updatesThisFrame==1)
        {
            ticksUntilFireDeath--;
            changed = true;
        }

        if(ticksUntilFireDeath == 0)
        {
            Main.destroyCell(x, y);
            changed = true;
        }

        if(onFire&&updatesThisFrame==1)
        {
            for(int x = -1; x < 2; x++)
            {
                for(int y = -1; y < 2; y++)
                {
                    if(y==0&&x==0)
                        continue;

                    try
                    {
                        double flam = Math.random();
                        if(Main.cells[this.x-x][this.y-y].flammability >flam&&!Main.cells[this.x-x][this.y-y].onFire&&(canSetCellsOnFire||cellTypeName.equals(Main.cells[this.x-x][this.y-y].cellTypeName)&&canSetSameTypeOnFire))
                        {
                            Main.cells[this.x-x][this.y-y].onFire = true;
                            Main.cells[this.x-x][this.y-y].ticksUntilFireDeath = Main.cells[this.x-x][this.y-y].startTicksUntilFireDeath;
                            changed = true;
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }

        update();


        if(pX!=x||pY!=y)
            changed = true;

        doChange(pX, pY, frame);
    }
    private void doChange(int pX, int pY, int frame)
    {

        if(!changed)
            return;

        for(int i = -1;i <= 1;i++)
        {
            for(int j = -1; j <= 1; j++)
            {
                try
                {
                    Main.cells[pX + i][pY + j].updateWrapper(frame);
                }
                catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
        for(int i = -1;i <= 1;i++)
        {
            for(int j = -1; j <= 1; j++)
            {
                if(i==0&&j==0)
                    continue;
                try
                {
                    Main.cells[x + i][y + j].updateWrapper(frame);
                }
                catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }

        Main.requestUpdate(this);
    }

    public void update() {}
    public void draw(Graphics g) {}
    public void move(int xAmount, int yAmount)
    {
        if(!((sideMoveAmnt>=currSideMove+xAmount&&currSideMove+xAmount>=-sideMoveAmnt)&&(verticalMoveAmnt>=currVerticalMove+yAmount&&currVerticalMove+yAmount>=-verticalMoveAmnt)))
            return;
        currSideMove+=xAmount;
        currVerticalMove+=yAmount;
        int oX = x;
        int oY = y;
        x += xAmount;
        y += yAmount;
        x = Usefull.clamp(0, 100, x);
        y = Usefull.clamp(0, 100, y);
        Main.cells[x][y] = this;
        Main.cells[oX][oY] = new Cell(oX, oY);
    }
    public void swap(int xAmount, int yAmount)
    {
        if(!((sideMoveAmnt>=currSideMove+xAmount&&currSideMove+xAmount>=-sideMoveAmnt)&&(verticalMoveAmnt>=currVerticalMove+yAmount&&currVerticalMove+yAmount>=-verticalMoveAmnt)))
            return;
        Cell oCell = Main.cells[x][y];
        int oX = x;
        int oY = y;
        x += xAmount;
        y += yAmount;
        x = Usefull.clamp(0, 100, x);
        y = Usefull.clamp(0, 100, y);
        oCell.x = oX;
        oCell.y = oY;
        Main.cells[x][y] = this;
        Main.cells[oX][oY] = oCell;
    }
    public boolean liquidMoveUp()
    {
       if(!isLiquid)
           return false;
       if(y>1&&Main.cells[x][y-1].liquidMoveUp())
       {
           move(0, -1);
           return true;
       }
       return false;
    }
}

public class Usefull
{
    public static int clamp(int min, int max, int value)
    {
        return Math.max(min, Math.min(max, value));
    }
}

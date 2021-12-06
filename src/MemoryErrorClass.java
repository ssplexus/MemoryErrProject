import java.util.LinkedList;
import java.util.List;

public class MemoryErrorClass implements Runnable
{
    public static long allocSize = 0;
    private static List<Object> list = new LinkedList<>();
    private int num;

    public MemoryErrorClass(int num)
    {
        this.num = num;
    }

    public void run()
    {
        System.out.printf("Run MemoryErrorClass(%d) ",num);
        memErrorFuncHeap(num);
        System.out.printf("Total alloc size: %d bytes\n", allocSize);
    }

    static int memErrorFuncStack(int n)
    {
        if (n == 0)
            return 1;
        else
        {
            return (n + memErrorFuncStack(n-1));
        }
    }
    static int memErrorFuncHeap(int n)
    {
        if (n == 0)
            return 1;
        else
        {
            int res = (n + memErrorFuncHeap(n-1));
            allocSize += res;
            list.add(new byte[res]);
            return res;
        }
    }
}

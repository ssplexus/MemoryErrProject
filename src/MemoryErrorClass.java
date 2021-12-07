import java.util.LinkedList;
import java.util.List;

/**
 * Класс ошибок памяти
 */
public class MemoryErrorClass implements Runnable
{
    /**
        Статический счётчик выделенной памяти
     */
    public static long allocSize = 0;
    // Список для хранения массивов размерностью равной результату функции memErrorFuncHeap
    private static List<Object> list = new LinkedList<>();
    // Переменная хранения параметра для memErrorFuncHeap в методе run
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

    /**
     * Метод вызова переполнения стека
     *
     * @param n - аргумент функции
     * @return - сумма ряда чисел
     */
    public static int memErrorFuncStack(int n)
    {
        if (n == 0)
            return 1;
        else
        {
            return (n + memErrorFuncStack(n-1));
        }
    }

    /**
     * Метод вызова переполнения кучи
     *
     * @param n - аргумент функции
     * @return - сумма ряда чисел
     */

    public static int memErrorFuncHeap(int n)
    {
        if (n == 0)
            return 1;
        else
        {
            int res = (n + memErrorFuncHeap(n-1));
            allocSize += res;
            // Выделяем память под массив размерностью возвращаемой результатом работы функции
            list.add(new byte[res]);
            return res;
        }
    }
}

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        int command;

        System.out.println("Out of memory errors test:");
        int num = 0;
        while(true)
        {
            try
            {
                switch (command = getTestMenu()) // главное меню
                {
                    case 3: // вызов утечки памяти через статическое поле
                        System.out.println("Enter number of threads: ");
                        num = getNumber();
                        while(num-- > 0)
                        {
                            System.out.printf("Thread%d",num);
                            new Thread(new MemoryErrorClass(new Random().nextInt(501))).start();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 2: // вызов переполнения кучи
                        num = getNumber();
                        System.out.printf("Func(%d) = %d\n", num, MemoryErrorClass.memErrorFuncHeap(num));
                        break;
                    case 1: // вызов переполнения стека
                        num = getNumber();
                        System.out.printf("Func(%d) = %d\n", num, MemoryErrorClass.memErrorFuncStack(num));
                        break;
                    case 0: // выход
                        return;
                }
            }
            catch (OutOfMemoryError e)
            {
                System.out.println("Out of memory error!");
                System.out.printf("Alloc before crash: %d bytes \n", MemoryErrorClass.allocSize);
            }
            catch (StackOverflowError e)
            {
                System.out.println("Stack overflow!");
            }
        }
    }

    private static int getTestMenu()
    {
        return Menu.getMenu(4,()->"Select test:\n1) Stack\n2) Heap\n3) Leak\n0) exit\n");
    }

    private static int getNumber()
    {
        return Menu.getMenu(100000, ()->"Enter number:\n");
    }
}


import java.util.Random;

public class Main {

    // 测试使用stack运行opCount个push和pop操作所需要的时间,单位:秒
    private static double testStack(Stack<Integer> stack,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
    public static void main(String[] args) {
        //比较两种stack的性能
        int opCount = 100000;
        ArrayStack<Integer> arrayQueue = new ArrayStack<>();
        double time1 = testStack(arrayQueue,opCount);
        System.out.println("ArrayStack , time :" + time1 + " s");

        LinkedListStack<Integer> loopQueue = new LinkedListStack<>();
        double time2 = testStack(loopQueue,opCount);
        System.out.println("LinkedStack , time :" + time2 + " s");

    }

}

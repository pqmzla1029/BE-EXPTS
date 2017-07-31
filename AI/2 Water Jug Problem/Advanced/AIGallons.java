import java.util.Scanner;

public class AIGallons {

    static int a_max;
    static int b_max;
    static int a = 0;
    static int b = 0;
    static int temp_a = 0;
    static int temp_b = 0;
    static int goal;
    static int fin = 0;
    static int[] a_arr = new int[1000000];
    static int[] b_arr = new int[1000000];
    static int a_count = -1;
    static int b_count = -1;
    static int[] stateList = new int[1000000];
    static int stateCount = 0;
    static int state = 0;
    static int repeatCount = 0;
    static int jump1 = 1;
    static int jump2 = 1;

    public boolean checkGoal() {

        if ((a == goal) || (b == goal)) { // goal reached
            fin = 1;
            return true;
        }
        return false;

    }

    void performAction(int x, int n) {
        temp_a = a;
        temp_b = b;
        switch (x) {
            case 0:
                R1();
                break;
            case 1:
                R2();
                break;
            case 2:
                R3();
                break;
            case 3:
                R4();
                break;
            case 4:
                R5();
                break;
            case 5:
                R6();
                break;
            default:
                System.out.println("hello " + state);
                break;
        }
        if (n == 1) {
            a = temp_a;
            b = temp_b;
            wordsDisplay(x);

        } else {
        }

    }

    boolean testRepeat() {
        performAction(state, 2);
        for (int i = 0; i < a_count + 1; i++) {
            if (a_arr[i] == temp_a && b_arr[i] == temp_b) {
                return false;
            }
        }
        return true;
    }

    public void statusJugs() {

        if (testRepeat()) {
            a_count++;
            performAction(state, 1);
            a_arr[a_count] = a;
            b_count++;
            b_arr[b_count] = b;
            stateList[stateCount] = state;
            stateCount++;
        } else {
            repeatCount = repeatCount + 1;
        }

        state = state + 1;
        state = (state % 6);
//                if (state==0)
//                    state=state+1;
    }

    private void findSolution() {

        while (true) {
            //System.out.println("Hello");
            if (checkGoal()) {
                System.out.print("Solution Found");
                break;
            }
            statusJugs();
            if (repeatCount == 5) {
                repeatCount = 0;
                System.out.println("repeated");
                System.out.println(a_count + " " + jump1);
                a_count = a_count - jump1;
                b_count = b_count - jump1;
                stateCount = stateCount - jump1;
                System.out.println(state + " " + jump1);

                state = state + (jump2 - jump1);
                jump1 = jump1 + 1;

                if (state < 0) {
                    state = 6 + state;
                }

                if (jump1 == jump2) {
                    jump1 = 1;
                    jump2 = jump2 + 1;
                }
//                if (jump==0)
//                    jump=jump+1;
                if (stateCount < 0) {
                    a_count = 0;
                    b_count = 0;
                    stateCount = 0;
                }
                a = a_arr[a_count];
                b = b_arr[b_count];
                System.out.println(a_count + " " + b_count);
                System.out.println(state);
                System.out.println(a + " " + b);
            }

        }
    }

    void R1() //Fill A
    {

        temp_a = a_max;

    }

    void R2() //Fill B
    {
        temp_b = b_max;

    }

    void R3() //empty A
    {

        temp_a = 0;

    }

    void R4() //empty B
    {
        temp_b = 0;
    }

    void R5() //transfer B to A
    {

        int fin = 0;

        while (!((temp_a != a_max) || (temp_b != 0))) {

            temp_a += 1;
            temp_b -= 1;
            System.out.println(temp_a + "     " + temp_b);
        }

    }

    void R6() //transfer A to B
    {

        int fin = 0;

        while (!((temp_b != b_max) || (temp_a != 0))) {

            temp_b += 1;
            temp_a -= 1;
        }

    }

    void wordsDisplay(int x) {
        switch (state) {
            case 0:
                System.out.println("Fill A");

                break;
            case 1:
                System.out.println("Fill B");
                break;
            case 2:
                System.out.println("Empty A");
                break;
            case 3:
                System.out.println("Empty B");
                break;
            case 4:
                System.out.println("Transfer B To A");
                break;
            case 5:
                System.out.println("Transfer A To B");
                break;
            default:
                break;
        }
        System.out.println("{" + a + "," + b + "}");
    }

    void finalResult() {
        a = 0;
        b = 0;
        for (int i = 0; i < stateCount; i++) {
            performAction(i, 1);
            wordsDisplay(i);
        }
    }

    void inputSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Max Size of A");
        a_max = sc.nextInt();
        System.out.println("Enter Max Size of B");
        b_max = sc.nextInt();
        System.out.println("Define Goal");
        goal = sc.nextInt();
    }

    public static void main(String[] args) {
        AIGallons aig = new AIGallons();
        aig.inputSize();
        aig.findSolution();
        aig.finalResult();
    }

}

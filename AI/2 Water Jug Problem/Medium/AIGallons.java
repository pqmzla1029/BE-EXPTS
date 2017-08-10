package AI;

/**
 *
 * @author Igor
 */

import static java.lang.System.exit;
import java.util.*;

class AIGallons {

    static int bucketA, bucketB;
    static int steps;
    static int rules[] = new int[15];
    static int reset = 0;
    static int ct = 0;
    static int incr;
    static int tbucketA, tbucketB;
    static int maxA,maxB,goal;
//Check State
    static int check() {
        if (steps > 10 || reset == 1) {
            return 1;
        } else if (bucketA == goal) {
            return 2;
        } else {
            return 0;
        }
    }
//Fill Bucket A
    static void fillA() {
        bucketA = bucketA + 4;
    }
//Fill Bucket B
    static void fillB() {
        bucketB += maxB;
    }
//Empty Bucket A
    static void emptyA() {
        bucketA = 0;
    }
//Empty Bucket B
    static void emptyB() {
        bucketB = 0;
    }
//Transfer A To B till B is full
    static void transferAtillBfull() {
        int temp = 0;
        temp = maxB - bucketB;
        bucketB = maxB;
        bucketA = bucketA - temp;
    }
//Transfer B To A till A is full
    static void transferBtillAfull() {
        int temp = 0;
        temp = 4 - bucketA;
        bucketA = 4;
        bucketB = bucketB - temp;
    }
//Empty contents of A into B
    static void emptyAintoB() {
        bucketB = bucketB + bucketA;
        bucketA = 0;
    }
//Empty contents of B into A
    static void emptyBintoA() {
        bucketA = bucketA + bucketB;
        bucketB = 0;
    }

    public static void call(int n) {

        tbucketA = bucketA;
        tbucketB = bucketB;

/*
        if (steps > 10) {
            System.out.println("ERROR num of steps greater than 10");
        }
*/
        ++steps;
        if (n == 1) {
            fillA();
        } //emptyA();
        else if (n == 2) {
            fillB();
        } //emptyB();
        else if (n == 3) {
            emptyA();
        } //fillA();
        else if (n == 4) {
            emptyB();
        } //fillB();
        else if (n == 5) {
            transferAtillBfull();
        } else if (n == 6) {
            transferBtillAfull();
        } else if (n == 7) {
            emptyAintoB();
        } else if (n == 8) {
            emptyBintoA();
        }

        if (bucketA > 4 || bucketA < 0 || bucketB > maxB || bucketB < 0) {
            reset = 1;
        }

    }
//Rules Printing Number Format
/*    public static void checkBucketData(int rules[]) {
        bucketA = 0;
        bucketB = 0;
        steps = 0;

        System.out.println("\nBucketA\tBucketB\tRule");
        System.out.println(bucketA + "\t" + bucketB + "\t");
        call(rules[0]);
        if (rules[0] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[0]);
        }
        call(rules[1]);
        if (rules[1] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[1]);
        }
        call(rules[2]);
        if (rules[2] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[2]);
        }
        call(rules[3]);
        if (rules[3] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[3]);
        }
        call(rules[4]);
        if (rules[4] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[4]);
        }
        call(rules[5]);
        if (rules[5] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[5]);
        }
        call(rules[6]);
        if (rules[6] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[6]);
        }
        call(rules[7]);
        if (rules[7] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[7]);
        }
        call(rules[8]);
        if (rules[8] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[8]);
        }
        call(rules[9]);
        if (rules[9] != 0) {
            System.out.println(bucketA + "\t" + bucketB + "\t" + rules[9]);
        }
    }
*/
//Rules in Words
    void wordsDisplay(int state) {
        switch (state) {
            case 0:
                break;
            case 1:
                System.out.println("Fill A");
                break;
            case 2:
                System.out.println("Fill B");
                break;
            case 3:
                System.out.println("Empty A");
                break;
            case 4:
                System.out.println("Empty B");
                break;
            case 5:
                System.out.println("Transfer A To B till B is full");
                break;
            case 6:
                System.out.println("Transfer B To A till A is full");
                break;
            case 7:
                System.out.println("Empty contents of A into B");
                break;
            case 8:
                System.out.println("Empty contents of B into A");
                break;
            default:
                break;
        }
    }
//Rules in Words
    void valuesDisplay(int state) {
        if(state!=0)
        {
         call(state);
         System.out.println("{"+bucketA+","+bucketB+"}");
        }

    }
//Print Rules
    public void printRules(int flag)
    {
        for(int i=0;i<10;i++)
        {
            wordsDisplay(rules[i]);
            if(flag==1)
                valuesDisplay(rules[i]);
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }
//Input Requirements
    public void inputSize() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Max Size of A");
        maxA = sc.nextInt();
        System.out.println("Enter Max Size of B");
        maxB = sc.nextInt();
        System.out.println("Define Goal");
        goal = sc.nextInt();
        System.out.println();
    }

    public int finalOutput(int i, int j, int k, int l, int m, int n, int o, int p, int q, int r) {
        int fflag = 0;
        bucketA = 0;
        bucketB = 0;
        steps = 0;
        try {
            Thread.sleep(1000);
            rules[0] = i;
            rules[1] = j;
            rules[2] = k;
            rules[3] = l;
            rules[4] = m;
            rules[5] = n;
            rules[6] = o;
            rules[7] = p;
            rules[8] = q;
            rules[9] = r;
            System.out.println("\nSolution Found\n");
            //checkBucketData(rules);
            int count2 = 0;

            for (int z = 0; z < 10; ++z) {
                if (rules[z] != 0) {
                    ++count2;
                }
            }

            System.out.println("Number of Steps= " + (count2)+"\n ");
            fflag=1;

            Thread.sleep(1000);
        } catch (Exception e) {
        }
        return fflag;
    }

    public void performComputation()
    {
        int flag = 0;
        steps = 0;
        reset = 0;
        //int temp1 = 0;
        incr = 0;
        //Repetitive Iterations Layered
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                for (int k = 0; k < 8; ++k) {
                    for (int l = 0; l < 8; ++l) {
                        for (int m = 0; m < 8; ++m) {
                            for (int n = 0; n < 8; ++n) {
                                for (int o = 0; o < 8; ++o) {
                                    for (int p = 0; p < 8; ++p) {
                                        for (int q = 0; q < 8; ++q) {
                                            for (int r = 0; r < 8; ++r) {
                                                reset = 0;
                                                call(i);
                                                call(j);
                                                call(k);
                                                call(l);
                                                call(m);
                                                call(n);
                                                call(o);
                                                call(p);
                                                call(q);
                                                call(r);
                                                if (bucketA == goal && reset == 0) {
                                                   flag=finalOutput(i,j,k,l,m,n,o,p,q,r);
                                                   printRules(flag);
                                                }

                                                //System.out.println("\nWastage= " + wastage);
                                                //comment the following lines for all solution in the state space tree
                                                if(flag==1)
                                                    exit(0);
                                                //System.out.println("\n\n\nPlease wait for another solution.....");

                                                bucketA = 0;
                                                bucketB = 0;
                                                steps = 0;
                                                reset = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public static void main(String args[]) {

        AIGallons aig=new AIGallons();

        for(int i=0;i<10;i++)
            rules[i]=i;
        System.out.println("RULES");
        aiwj.printRules(0);
        //Input Requirements
        aig.inputSize();
        //Solving Question
        aig.performComputation();

    }
}

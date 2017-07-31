import static java.lang.System.exit;
import java.util.*;

class AIGallons {

    static int a, b;
    static int steps;
    static int rules[] = new int[15];
    static int reset = 0;
    static int ct = 0;
    static int incr;
    static int aSize,bSize;
    static int ta, tb;

//check whether full or not
    static int check() {
        if (steps > 10 || reset == 1) {
            return 1;
        } else if (a == 2) {
            return 2;
        } else {
            return 0;
        }
    }

//Empty Jug 1
    static void empty1() {
        a = 0;
    }

//Empty Jug 2
    static void empty2() {
        b = 0;
    }

 //Fill Jug 1
    static void fill1() {
        a = a + aSize;
    }

//Fill Jug 2
    static void fill2() {
        b += bSize;
    }

//Transfer contents of Jug 1 to Jug 2
    static void a_to_b() {
        int temp = 0;
        temp = 3 - b;
        b = 3;
        a = a - temp;
    }

//Transfer contents of Jug 2 to Jug 1

    static void b_to_a() {
        int temp = 0;
        temp = 4 - a;
        a = 4;
        b = b - temp;
    }

//Empty 1 Fill 2
    static void empty1_fill2() {
        b = b + a;
        a = 0;
    }

//Empty 2 Fill 1
    static void empty2_fill1() {
        a = a + b;
        b = 0;
    }

//Calling Actions
    static void call(int n) {

        ta = a;
        tb = b;



        ++steps;
        if (n == 1) {
            fill1();
        } //empty1();
        else if (n == 2) {
            fill2();
        } //empty2();
        else if (n == 3) {
            empty1();
        } //fill1();
        else if (n == 4) {
            empty2();
        } //fill2();
        else if (n == 5) {
            a_to_b();
        } else if (n == 6) {
            b_to_a();
        } else if (n == 7) {
            empty1_fill2();
        } else if (n == 8) {
            empty2_fill1();
        }

        if (a > 4 || a < 0 || b > 3 || b < 0) {
            reset = 1;
        }

    }
//repetitive checking of rules

    static void repeat(int rules[]) {
        a = 0;
        b = 0;
        steps = 0;

        System.out.println("\nJug 1\tJug 2\tRule");
        System.out.println(a + "\t" + b + "\t");
        call(rules[0]);
        if (rules[0] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[0]);
        }
        call(rules[1]);
        if (rules[1] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[1]);
        }
        call(rules[2]);
        if (rules[2] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[2]);
        }
        call(rules[3]);
        if (rules[3] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[3]);
        }
        call(rules[4]);
        if (rules[4] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[4]);
        }
        call(rules[5]);
        if (rules[5] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[5]);
        }
        call(rules[6]);
        if (rules[6] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[6]);
        }
        call(rules[7]);
        if (rules[7] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[7]);
        }
        call(rules[8]);
        if (rules[8] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[8]);
        }
        call(rules[9]);
        if (rules[9] != 0) {
            System.out.println(a + "\t" + b + "\t" + rules[9]);
        }
    }

    public static performSolving()
    {
        System.out.println("\nRules:\n1.Fill Jug 1.\n2.Fill Jug 2.\n3.Empty Jug 1.\n4.Empty Jug 2.\n5.Jug 1 to Jug 2 till full.\n6.Jug 2 to Jug 1 till full.\n7.Empty Jug 1 and add to Jug 2.\n8.Empty Jug 2 and add to Jug 1.");

        int flag = 0;
        int count1 = 0;

        flag = 0;
        count1 = 0;
        steps = 0;
        for (int i = 0; i < 10; ++i) {
            rules[i] = 0;
        }
        reset = 0;
        int temp1 = 0;
        incr = 0;

//Repititive Iterations...layered
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
                                                if (a == 2 && reset == 0) {
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
                                                        System.out.println("\n");
                                                        repeat(rules);
                                                        int count2 = 0;

                                                        for (int z = 0; z < 10; ++z) {
                                                            if (rules[z] != 0) {

                                                                ++count2;
                                                            }
                                                        }

                                                        System.out.println("\nNumber of Steps= " + (count2 + 1));
                                                        System.out.println("\n");


                                                    }
                                                    exit(0);//For Remaining Possible solutions Uncomment this
                                                }

                                                a = 0;
                                                b = 0;
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

    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Max Size of A");
        aSize=sc.nextInt();
        System.out.println("Enter Max Size of B");
        bSize=sc.nextInt();
        AIGallons aig= new AIGallons();
        aig.performSolving();

     }

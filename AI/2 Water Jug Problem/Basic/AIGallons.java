package artint;

public class AIGallons {


    int a_max = 4;
    int b_max = 3;
    int a = 0;
    int b = 0;
    int goal = 2;

    void checkGoal() {

        int fin = 0;

        while (fin != 1) {

            if ((this.a == this.goal) || (this.b == this.goal)) { // goal reached
                fin = 1;
            }

            if (this.a == 0) { // a is empty 
                R1();

            } else if ((this.a > 0) && (this.b != this.b_max)) { // a has water and b is not full
                R6();

            } else if ((this.a > 0) && (this.b == this.b_max)) { // a is not empty b is empty
                R4();

            }

        }
    }

    void R1() //Fill A
    {

        this.a = this.a_max;
        System.out.println("Fill A");
        System.out.println("{" + this.a + "," + this.b + "}");

    }

    void R2() //Fill B
    {
        this.b = this.b_max;
        System.out.println("Fill B");
        System.out.println("{" + this.a + "," + this.b + "}");
    }

    void R6() //transfer A to B
    {

        int fin = 0;

        while (fin != 1) {

            this.b += 1;
            this.a -= 1;

            if ((this.b == this.b_max) || (this.a == 0)) {
                fin = 1;
            }

        }
        
        System.out.println("Transfer A To B");
        System.out.println("{" + this.a + "," + this.b + "}");

    }

    void R3() //empty A
    {

        this.a = 0;
        System.out.println("Empty A");
        System.out.println("{" + this.a + "," + this.b + "}");

    }

    void R4() //empty B
    {
        this.b = 0;
        System.out.println("Empty B");
        System.out.println("{" + this.a + "," + this.b + "}");
    }

}
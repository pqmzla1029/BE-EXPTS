
package css;

import java.util.Scanner;


public class DoubleColumnarTransposition {
    static char[][] matrix= new char[26][26];

    public void inputReq()
    {
        String pt,key1,key2;

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the message to transmit");
        pt=sc.nextLine();
        System.out.println("Enter the key");
        key1=sc.nextLine();
        System.out.println("Enter the key");
        key2=sc.nextLine();
        String ct=perform(pt,key1);
        ct=perform(ct,key2);
        System.out.println("PT = "+pt);
        System.out.println("CT = "+ct);

    }


    public String perform(String pt,String key)
    {
        int order[];
        convertToMatrix(pt,key.length());
        order=new int[key.length()];
        order=findOrder(key);
        String ct=findMessage(order);
        return ct;
        //System.out.println("PT = "+pt);
        //System.out.println("CT = "+ct);
    }


    private void convertToMatrix(String pt,int length)
    {
        int count=0;
        for(int i=0;i<length;i++)
        {
            for(int j=0;j<length;j++)
            {
                if (count<pt.length())
                {
                    matrix[i][j]=pt.charAt(count);
                }
                 else
                    matrix[i][j]='X';
                count++;
            }
        }
    }

    private int[] findOrder(String key) {
        int position=1;
        int[] order=new int[key.length()];
        for(int i=0;i<key.length();i++)
        {
            position =1;
            for(int j=0;j<key.length();j++)
            {
                if (((int)key.charAt(i)>(int)key.charAt(j)))
                {
                    position=position+1;
                }
                else{}
            }
            order[i]=position;

        }
        return order;
    }

    private String findMessage(int [] order) {
        String ct="";
        for(int i=0;i<order.length;i++)
        {
            for(int j=0;j<order.length;j++)
            {
            int valueCol=(order[i]-1);
            ct=ct+matrix[j][valueCol];

            }

        }
        return ct;
    }

    public static void main(String[] args)
    {
        DoubleColumnarTransposition dct=new DoubleColumnarTransposition();
        dct.inputReq();
    }
}

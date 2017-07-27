/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css;

import java.util.*;


public class VernamCipher {

    String ct="";
    public void inputString()
    {
        String sentence="";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        sentence=sc.nextLine();
        sentence=sentence.toLowerCase();
        String key=encryptString(sentence);
        String plainText=decryptString(key);
    }

    private String encryptString(String sentence)
    {
        String key;
        char p,k,c;
        key=generateKey(sentence.length());
        System.out.println(key);
        for(int i=0;i<sentence.length();i++)
        {
        p=(char)(((int)sentence.charAt(i))-97);
        k=(char)(key.charAt(i)-65);
        c=(char)((p+k)%26);
        c=(char)(c+65);
        //System.out.println(c);
        ct=ct+c;
        }
        System.out.println("Cyper Text "+ct);
        return key;
    }

    private String decryptString(String key)
    {
        String pt="";
        char p,k,c;

        for(int i=0;i<ct.length();i++)
        {
        c=(char)(((int)ct.charAt(i))-65);
        k=(char)(key.charAt(i)-65);
        int x=(c-k)%26;
        if(x<0)
            x=x+26;
        p=(char)(x);
        System.out.println(p);
        p=(char)(p+97);
        System.out.println((c-k)%26);
        pt=pt+p;
        }
        System.out.println("Plain Text "+pt);
        return pt;
    }

    private String generateKey(int stringLength) {
        String key="";
        char[] r=new char[stringLength];
        int lower=0;
        int upper=25;
        int count=0,flag=0;
        while(stringLength>0)
        {
            flag=0;
        char randomAlphabet = (char)(((int)((Math.random() * (upper - lower)+lower)))+65);
        for(int i=1;i<count;i++)
        {
            if(r[count]==randomAlphabet)
            {
                flag=1;
            }
        }
        if (flag==1)
        {
            continue;
        }
        r[count]=randomAlphabet;


        //System.out.print(randomAlphabet);

        key=key+randomAlphabet;
        stringLength=stringLength-1;
        count++;
        }
        System.out.println(count);
         for(int i=0;i<count;i++)
            System.out.print(r[i]);
        System.out.println();
        return key;
    }

public static void main(String[] args) {
        VermanCipher vm = new VermanCipher();
        vm.inputString();
    }
}


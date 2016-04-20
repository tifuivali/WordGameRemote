/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tifui
 */
public class LeterBag 
{
   private String letterDistribution="AAAAAAAAABBCCDDDDEEEEEEEEEEEEFF"+
                                      "GGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOO"+
                                      "PPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ' ";
   Random random=new Random();
   private char[][]matrixLeterDistributin;
   private Boolean available=true;
   private int remainedTitles=98;
   public LeterBag(){
         matrixLeterDistributin=new char[10][10];
        int k=0;
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
                 matrixLeterDistributin[i][j]=letterDistribution.charAt(k++);
        }
        
   }
   /**
    * Returneaza 7 litere alese random din sac si pune '0' acolo unde s-a extras
    * o litera.
    * @return titles  
    */
   
   public synchronized Title[] getSevenRandomTitles()
   {
       
       
       while(!available)
       {
           try{wait();}
           catch(InterruptedException e){
               System.err.println(e.getMessage());
           }       
       }
       available=false;
       //
      int k=0;
      List<Title> free_titles=new ArrayList<>();
      for(int i=0;i<matrixLeterDistributin.length;i++)
      {
          for(int j=0;j<matrixLeterDistributin[0].length;j++)
          {
              if(matrixLeterDistributin[i][j]!='*')
              free_titles.add(new Title(i, j, matrixLeterDistributin[i][j]));
          }
      }
      int nrEelemente=free_titles.size();
      Title[] titles;
      if(nrEelemente>=7)
        titles=new Title[7];
      else titles=new Title[nrEelemente];
      for(int i=0;i<titles.length;i++)
      {
          int r=random.nextInt(nrEelemente);
          titles[i]=free_titles.get(r);
          matrixLeterDistributin[titles[i].getRow()][titles[i].getColumn()]='*';
          free_titles.remove(r);
          nrEelemente--;
      }
      available=true;
      notifyAll();
      return titles;
       
   }
   
   public synchronized Title[] getKRandomTitles(int k)
   {
       
       
       while(!available)
       {
           try{wait();}
           catch(InterruptedException e){
               System.err.println(e.getMessage());
           }       
       }
       available=false;
       //
      
      List<Title> free_titles=new ArrayList<>();
      for(int i=0;i<matrixLeterDistributin.length;i++)
      {
          for(int j=0;j<matrixLeterDistributin[0].length;j++)
          {
              if(matrixLeterDistributin[i][j]!='*')
              free_titles.add(new Title(i, j, matrixLeterDistributin[i][j]));
          }
      }
      int nrEelemente=free_titles.size();
      Title[] titles;
      if(nrEelemente>=k)
        titles=new Title[k];
      else titles=new Title[nrEelemente];
      for(int i=0;i<titles.length;i++)
      {
          int r=random.nextInt(nrEelemente);
          titles[i]=free_titles.get(r);
         
          free_titles.remove(r);
          nrEelemente--;
      }
     for(int i=0;i<titles.length;i++)
      {
          matrixLeterDistributin[titles[i].getRow()][titles[i].getColumn()]='*';
      }
      available=true;
      notifyAll();
      return titles;
       
   }
   
   private int getConsosns(Title[] titles)
   {
       int nrVocale=0;
       char[] vocals={'a','e','i','o','u'};
       for(Title tit:titles)
       {
           for(char c:vocals)
           {
               if(tit.getValue()==c)
                   nrVocale++;
           }
       }
       
       return  titles.length-nrVocale;
       
   }
   
   /**
     * Pune literele din stringul de distributie intr-o matrice
     * 10x10 si returneaza matricea.
     * @return Matrix of letter Dostribution
     */
   public char[][] getLeterDistribution()
   {
     return matrixLeterDistributin;
   }
   
   
    
   
}

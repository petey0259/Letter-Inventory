//Programmers: Neko Wolf, Peter Hovenier
//Lab 4: Letter Inventory
//CS145, 7/26/22

//The program will take input and count how many there is of each 
//letter in that input. The count and letters can then be 
//manipulated by giving the program different options.
 
import java.util.*;
 
public class LetterInventory{
 
   public static int charCounter = 26;
 
   private int[] letterCount;
   private String data;
   
   //A constructor for no parameters.
   public LetterInventory() {
      this.data = "";
      letterCount = new int[charCounter];
   }
   
   //A constructor that counts the letters from the input 
   //no matter the letters' case and disregards nonletters.
   public LetterInventory(String data) {
      data = data.toLowerCase();
      letterCount = new int[charCounter];
      int stringTra = 0;
      for (int i = 0; i < data.length(); i++) {
         char ch = data.charAt(i);
         if (Character.isLetter(ch)) {
            letterCount[(int)ch - 97]++; 
            this.data += ch;   
         }   
      }     
   }
   
   //Gets the count for what character is chosen.
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("Character is non-alphabetic"); 
      } else {
            return letterCount[(int)letter - 97];
      }
   }
   
   //Inputs new value of the count for what character is given. Disregards case.
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException("Character is non-alphabetic or value is negative"); 
      }
      letterCount[(int)letter - 97] = value;
   }
   
   //Returns the amount of all characters counted.
   public int size() { 
      int sum = 0;
      if (isEmpty() == true) {
         return sum;
      } else {
       //not sure how to make it "fast"
         for (int i = 0; i < letterCount.length; i++) {
            if (letterCount[i] > 0) {
               sum++;
            } 
         }
      }
      return sum;
   }
   
   //Returns a status of true if all the counts are empty.
   public boolean isEmpty() {
      boolean empty = true;
      for (int i : letterCount) {   
         if(letterCount[i] > 0) {
            return !empty;
         }
      }
      return empty;
   }
   
   //Returns a string of the letters counted in lowercase and 
   //alphabetical order in between square brackets.
   public String toString() {
      String letters = "";
      if (isEmpty() == true) {
         return "[]";
      } else {
         letters = "[";
         for (int i = 0; i < letterCount.length; i++) {
            if (letterCount[i] > 0) {
               int count = 0;
               while (count < letterCount[i]) {
                  char result = (char)('a' + i);
                  letters += result;
                  count++;
               }
            }
         }
      }
      return letters + "]";
   }
   
   // Returns a new LetterInventory that combines the count of 
   //a LetterInventory and another LetterInventory together.
   public LetterInventory add(LetterInventory other) {
      LetterInventory addedInventory = new LetterInventory(this.data);
         for (int i = 0; i < other.letterCount.length; i++) {
            if (other.letterCount[i] > 0) {
               this.letterCount[i] += other.letterCount[i]; 
            }
         }
      return addedInventory;
   }
   
   //Returns a new LetterInventory that gets rid of letters from 
   //one LetterInventory from another. If any letter ends up 
   //negative then null will be returned.
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtractInventory = new LetterInventory(this.data);
         for (int i = 0; i < other.letterCount.length; i++) {
            if (other.letterCount[i] > 0) {
               this.letterCount[i] -= other.letterCount[i]; 
               if(this.letterCount[i] < 0) {
                  return null;
               }
            }
         }
      return subtractInventory;
   }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz1rollingfifo;

/**
 *
 * @author YingLUO
 */
public class RollingPriorityFIFO {
    
    private class Item {
      // add constructor of your choice
      Item next;
      boolean priority;
      String value;
    }
    
    private Item tail, head; // enqueue at tail, dequeue at head
    private int itemsTotal, itemsCurrUsed;
    
    
    /* Parameter itemsTotal must be 5 or more, otherwise IllegalArgumentException
    * is thrown. Items are allocated and connected via next pointer only
    * once - here, in the constructor. After that they are re-used.
    */
   public RollingPriorityFIFO(int itemsTotal) {
       if(itemsTotal < 5){
           throw new IllegalArgumentException("Parameter itemsTotal must be 5 or more");
       }else{
           this.itemsTotal = itemsTotal;
           this.itemsCurrUsed = 0;
           this.head = new Item();
           this.tail = new Item();
           Item temp = new Item();
           head.next = temp;
           for(int i=0; i<itemsTotal-3; i++){
               temp.next = new Item();
               temp = temp.next;
           }
           temp.next = tail;
           tail.next = head;
           Item newHead = new Item();
           Item newTail = new Item();
           newHead = this.head;
           newTail = this.head;
           this.head = newHead;
           this.tail = newTail;
       }
   }
   
   private Item getItem(int index){
        Item temp = new Item();
        temp = head;
        if (index > 0) {
            for (int i = 1; i <= index; i++) {
                temp = temp.next;
            }
        }
        return temp;
    }

   // Places value in the next available Item. If FIFO is full throws exception.
   public void enqueue(String value, boolean priority) throws FIFOFullException {
       if(itemsCurrUsed == 0){
           head.priority = priority;
           head.value = value;
           this.itemsCurrUsed++;
       }else if(itemsCurrUsed < this.itemsTotal){
           Item temp = getItem(this.itemsCurrUsed);
           temp.priority = priority;
           temp.value = value;
           tail = temp;
           this.itemsCurrUsed++;
       }else{
           throw new FIFOFullException("FIFO is full");
       }
   }

   /* returns null if fifo is empty, if it is not empty then
    * priority=true items are sarched first
    * if none is found then non-priority item is returned
    */

   public String dequeue() {
        for(int i=0; i< this.itemsCurrUsed; i++){
            Item temp = getItem(i);
            if (temp.priority == true){
                if(temp == head){
                    head = head.next;
                }else{
                    getItem(i-1).next = temp.next;
                    temp.next = tail.next;
                    tail.next = temp;
                }
                this.itemsCurrUsed--;
                return temp.value;
            }
        }
        if(itemsCurrUsed == 0){
            return null;
        }else{
            Item temp = head;
            head = head.next;
            this.itemsCurrUsed--;
            return temp.value;
        }
   }

   public int size() { return itemsCurrUsed; } // current FIFO size
   public int sizeMax() { return itemsTotal; } // maximum FIFO size

   // Returns array of Strings of all items in FIFO (following next references).
   public String[] toArray() { 
       String[] strArray = new String[this.itemsCurrUsed];
       for(int i=0; i<this.itemsCurrUsed; i++){
           strArray[i] = getItem(i).value;
       }
       return strArray;
   }
   
   // Returns array of String only of priority items in FIFO.
   public String[] toArrayOnlyPriority() {
       String[] temp = new String[this.itemsCurrUsed];
       int count=0;
       for(int i=0; i<this.itemsCurrUsed; i++){
           if(getItem(i).priority == true){
               temp[count] = getItem(i).value;
               count++;
           }
       }
       if(count == 0){
           return null;
       }
       String[] strArray = new String[count];
       for(int i=0; i<count; i++){
           strArray[i] = temp[i];
       }
       return strArray;
   }

   // Items with priority=true have "*" appended, e.g. "[Jerry*,Maria,Tom*];
   // Items with priority=true have "*" appended, e.g. "[Jerry*,Maria,Tom*];
   // Items about to be dequeue()'d are listed first, items recently enqueue()'d last
   @Override
   public String toString() { 
       String str = "";
       for(int i=0; i<this.itemsCurrUsed; i++){
           if(i==0){
               str = "[";
           }
           if(getItem(i).priority == true){
               str += getItem(i).value+"*,";
           }else{
               str +=getItem(i).value+",";
           }
           if(i == this.itemsCurrUsed-1){
               str = str.substring(0, str.length()-1);
               str += "]";
           }
       }
       return str;
   }
    
}

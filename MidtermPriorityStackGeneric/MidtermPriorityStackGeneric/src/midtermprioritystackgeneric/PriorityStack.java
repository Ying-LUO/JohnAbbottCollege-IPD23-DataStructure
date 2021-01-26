package midtermprioritystackgeneric;

// Your task is to complete the implementation of the class below.

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

// You are not allowed to use complex data structures such as ArrayList, HashMap, etc.
// You are also not allowed to use Collections and Arrays *classes*.
// You are allowed to use arrays, of course.
// When in doubt - it is your responsibility ask the teacher DURING the midterm, not after.
// WARNING: Your solution will be uploaded to a plagiarism-checking system.
// Only submit your own original work.
public class PriorityStack<T> {

    private class Container<T> {

        T value;
        boolean hasPriority;
        Container<T> nextBelow;
    }

    private Container<T> top; // top of the stack element

    private int size;

    public void push(T value) {
        push(value, false);
    }

    public void push(T value, boolean hasPriority) {
        Container<T> newCont = new Container<>();
        newCont.value = value;
        newCont.hasPriority = hasPriority;
        newCont.nextBelow = top;
        top = newCont;
        size++;
    }

    public T pop() {
        // remove and return the top item
        // if no item found (size == 0) then throw NoSuchElementException
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Container<T> topItem = top;
        top = top.nextBelow;
        size--;
        return topItem.value;
    }

    public T popPriority() {
        // find item with priority starting from the top, remove it and return it
        // if no priority item found then remove and return the top item
        // if stack is empty then throw NoSuchElementException
        Container<T> previous = null;
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (current.hasPriority) {
                if (current == top) { // removing first item - special case
                    return pop();
                }
                previous.nextBelow = current.nextBelow; // go around the element being removed (current)
                size--;
                return current.value;
            }
            previous = current;
        }
        // if I reach this point in code that means there is no priority items in the stack
        return pop();
    }

    public int hasValue(T value) {
        // returns -1 if value is not on the stack
        // this code only looks for the *first* occurence of the value, starting from top
        // WARNING: you must call value.equals(item.value) to determine whether
        // two values are equal, just like you would do for a String
        // returning value 0 means the value is on top of the stack,
        // 1 means 1 below the top, and so on...
        int depth = 0;
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (current.value.equals(value)) { // WRONG!!!
                return depth;
            }
            depth++;
        }
        return -1; // not found
    }

    public T removeValue(T value) {
        // removes the first item from top containing the value and returns the value
        // if item with value is not found throw NoSuchElementException
        Container<T> previous = null;
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (current.value.equals(value)) {
                if (current == top) { // removing first item - special case
                    return pop();
                }
                previous.nextBelow = current.nextBelow; // go around the element being removed (current)
                size--;
                return current.value;
            }
            previous = current;
        }
        throw new NoSuchElementException();
    }

    public int getSize() {
        return size;
    }

    public void reorderByPriority() {
        // reorder items (re-create a new stack, if you like)
        // where all priority items are on top and non-priority items are below them
        // Note: order within the priority items group and non-priority items group must remain the same
        // Suggestion: instead of reordering the existing stack items
        // it may be easier to re-create a new stack with items in the order you need
        Object [] newStackData = new Object[size];
        int addedCount = 0;
        // collect all items with priority
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (current.hasPriority) {
                newStackData[addedCount] = current;
                addedCount++;
            }
        }
        // collect all items without priority
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (!current.hasPriority) { // NOT
                newStackData[addedCount] = current;
                addedCount++;
            }
        }
        //
        top = null;
        size = 0;
        // walk backwards and add to new stack
        for (int i = newStackData.length-1; i >= 0; i--) {
            Container<T> item = (Container<T>) newStackData[i];
            push(item.value, item.hasPriority);
        }
    }

    @Override
    public String toString() {
        // return string describing the contents of the stack, starting from the top
        // Use value.toString() to conver values kept in the stack to strings.
        // Format exactly like this (assuming T is a string to keep it simple):
        // "[Jerry:N,Terry:N,Martha:P,Tom:P,Jimmy:N]" 
        // N means item has no priority, P means item has priority
        // For full marks you must use StringBuilder, no + (string concatenation) allowed.
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // loop over all items in the linked list            
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            sb.append(current == top ? "" : ","); // no comma preceeding the first item
            sb.append(current.value.toString());
            sb.append(current.hasPriority ? ":P" : ":N");
        }
        sb.append("]");
        return sb.toString();
    }

    private T[] reversed;
    private int reversedCount;
    
    private void putReverseItem(Container<T> item) {
        if (item != null) {
            putReverseItem(item.nextBelow);
            reversed[reversedCount] = item.value;
            reversedCount++;
        }
    }
    
    public T[] toArrayReversed(Class<T> type) { // Note: this is "the twist"
        // return array with items on the stack
        // WARNING: element 0 of the array must be the BOTTOM of the stack
        // NOTE: To obtain full marks for this method you must use recursion.
        // Collect items on your way back, just before returning.
        // This case is similar to when constructors of parent classes are called (Programming II course).
        reversed = (T[]) Array.newInstance(type, size);
        putReverseItem(top);
        return reversed;
    }

    // NOTE: you are only allowed to add private methods and private fields (if needed)
}

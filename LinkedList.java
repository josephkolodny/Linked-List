//****************************************************************
//Author: Joseph Kolodny
//Linked List
//12/2/2022
//****************************************************************

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 The LinkedList class implements a Linked list.
 */

public class LinkedList
{
    /**
     The Node class stores a list element
     and a reference to the next node.
     */

    private class Node
    {
        String value;
        Node next;

        /**
         Constructor.
         @param val The element to store in the node.
         @param n The reference to the successor node.
         */

        Node(String val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         Constructor.
         @param val The element to store in the node.
         */

        Node(String val)
        {
            // Call the other (sister) constructor.
            this(val, null);
        }
    }

    private Node first;  // list head
    private Node last;   // last element in list

    /**
     Constructor.
     */

    public LinkedList()
    {
        first = null;
        last = null;
    }

    /**
     The isEmpty method checks to see
     if the list is empty.
     @return true if list is empty,
     false otherwise.
     */

    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     The size method returns the length of the list.
     @return The number of elements in the list.
     */

    public int size()
    {
        int count = 0;
        Node p = first;
        while (p != null)
        {
            // There is an element at p
            count ++;
            p = p.next;
        }
        return count;
    }

    /**
     The add method adds an element to
     the end of the list.
     @param e The value to add to the
     end of the list.
     */

    public void add(String e)
    {
        if (isEmpty())
        {
            first = new Node(e);
            last = first;
        }
        else
        {
            // Add to end of existing list
            last.next = new Node(e);
            last = last.next;
        }
    }

    /**
     The add method adds an element at a position.
     @param e The element to add to the list.
     @param index The position at which to add
     the element.
     @exception IndexOutOfBoundsException When
     index is out of bounds.
     */

    public void add(int index, String e)
    {
        if (index < 0  || index > size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Index is at least 0
        if (index == 0)
        {
            // New element goes at beginning
            first = new Node(e, first);
            if (last == null)
                last = first;
            return;
        }

        // Set a reference pred to point to the node that
        // will be the predecessor of the new node
        Node pred = first;
        for (int k = 1; k <= index - 1; k++)
        {
            pred = pred.next;
        }

        // Splice in a node containing the new element
        pred.next = new Node(e, pred.next);

        // Is there a new last element ?
        if (pred.next.next == null)
            last = pred.next;
    }

    /**
     The toString method computes the string
     representation of the list.
     @return The string form of the list.
     */

    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value + "\n");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
     The remove method removes the element at an index.
     @param index The index of the element to remove.
     @return The element removed.
     @exception IndexOutOfBoundsException When index is
     out of bounds.
     */

    public String remove(int index)
    {
        if (index < 0 || index >= size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        String element;  // The element to return
        if (index == 0)
        {
            // Removal of first item in the list
            element = first.value;
            first = first.next;
            if (first == null)
                last = null;
        }
        else
        {
            // To remove an element other than the first,
            // find the predecessor of the element to
            // be removed.
            Node pred = first;

            // Move pred forward index - 1 times
            for (int k = 1; k <= index -1; k++)
                pred = pred.next;

            // Store the value to return
            element = pred.next.value;

            // Route link around the node to be removed
            pred.next = pred.next.next;

            // Check if pred is now last
            if (pred.next == null)
                last = pred;
        }
        return element;
    }

    /**
     The remove method removes an element.
     @param element The element to remove.
     @return true if the remove succeeded,
     false otherwise.
     */

    public boolean remove(String element)
    {
        if (isEmpty())
            return false;

        if (element.equals(first.value))
        {
            // Removal of first item in the list
            first = first.next;
            if (first == null)
                last = null;
            return true;
        }

        // Find the predecessor of the element to remove
        Node pred = first;
        while (pred.next != null &&
                !pred.next.value.equals(element))
        {
            pred = pred.next;
        }

        // pred.next == null OR pred.next.value is element
        if (pred.next == null)
            return false;

        // pred.next.value  is element
        pred.next = pred.next.next;

        // Check if pred is now last
        if (pred.next == null)
            last = pred;

        return true;
    }

    public void sort()
    {
        // current node pointing to first and index node to traverse list
        Node current = first;
        Node index = null;

        // if list is empty return
        if (isEmpty()) {
            return;
        }
        else
        {
            // traverse to list
            while (current != null) {
                // set index as next node of current
                index = current.next;

                // traverse from index node
                while (index != null) {
                    // if current node's value is greater than index node then swap values
                    if (current.value.compareTo(index.value) > 0)
                    {
                        // swap values between current and index nodes
                        String temp = current.value;
                        current.value = index.value;
                        index.value = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    public void reverse()
    {
        // if list is empty then return
        if(isEmpty())
            return;

        // current node pointing to first node of list
        Node current = first;
        Node prev = null;
        Node next = null;
        while (current != null)
        {
            // set next as current's next
            next = current.next;
            // set current's next as prev
            current.next = prev;
            // update prev with current
            prev = current;
            // update current with next
            current = next;
        }

        // finally update first with prev
        last = first;
        first = prev;
    }

    public boolean find(String name)
    {
        if(isEmpty())
            return false;
        Node temp = first;
        while(temp != null)
        {
            if(temp.value.equalsIgnoreCase(name))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public static void main(String [] args)
    {
        // linked list object
        LinkedList ll = new LinkedList();
        // scanner for user input
        Scanner scanner = new Scanner(System.in);

        try{
            File file = new File("Names.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine())
            {
                ll.add(reader.nextLine());
            }
            reader.close();
        }catch (IOException ex)
        {
            System.err.println("Exception: "+ex.getMessage());
        }

        // show menu
        String command = "";
        while(!command.equalsIgnoreCase("exit"))
        {
            System.out.println("(add) new name.");
            System.out.println("(remove) a name.");
            System.out.println("(find) a name.");
            System.out.println("(sort) list.");
            System.out.println("(reverse) list.");
            System.out.println("(print) names.");
            System.out.println("(exit)");
            System.out.print("Your command: ");
            command = scanner.nextLine();

            if(command.equalsIgnoreCase("add"))
            {
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                ll.add(name);
                System.out.println("Name added!");
            }
            else if(command.equalsIgnoreCase("remove"))
            {
                System.out.print("Enter name to remove: ");
                String name = scanner.nextLine();
                if(ll.remove(name))
                    System.out.println("Name removed!");
                else
                    System.out.println("Name not found!");
            }
            else if(command.equalsIgnoreCase("find"))
            {
                System.out.print("Enter name to find: ");
                String name = scanner.nextLine();
                if(ll.find(name))
                    System.out.println("Name found!");
                else
                    System.out.println("Name not found!");
            }
            else if(command.equalsIgnoreCase("sort"))
            {
                ll.sort();
                System.out.println("Names list sorted!");
            }
            else if(command.equalsIgnoreCase("reverse"))
            {
                ll.reverse();
                System.out.println("Names list reversed!");
            }
            else if(command.equalsIgnoreCase("print"))
            {
                System.out.println("*** List Content ***");
                System.out.println(ll);
            }
            else if(command.equalsIgnoreCase("exit"))
            {
                System.out.println("Bye!!!");
            }
            else
            {
                System.out.println("Invalid Choice!");
                System.out.println("Valid choices are:\nadd remove find sort reverse print exit");
            }
            System.out.println();
        }
    }
}
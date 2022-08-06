import java.io.*;
import java.util.*;

public class Lab_13_FileListMaker {

    public static ArrayList<String> addItem(ArrayList<String> l) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        String item;
        do
        {
            System.out.print("\nEnter item to add: ");
            item = sc.nextLine();
            if(item.length() != 0)
            {

                l.add(item);
                System.out.println("Item added successfully.");
            }
            else
                System.out.println("Item data is empty. Please re-enter!");
        }while(item.length() <= 0);
        return l;
    }

    public static ArrayList<String> delItem(ArrayList<String> l) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int n;

        viewList(l);
        if(l.size() == 0)
            return l;
        do
        {

            System.out.print("\nEnter item index to delete: ");
            n = sc.nextInt();
            if(n >= 1)
            {

                l.remove(n-1);
                System.out.println("Item deleted successfully.");
            }
            else
                System.out.println("Item index is invalid. Please re-enter!");
        }while(n <= 0);
        return l;
    }

    public static ArrayList<String> loadList(String name) throws IOException
    {
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(new File(name)));
        String s ="";

        while((s = in.readLine()) != null)
            lines.add(s);
        System.out.println("\nList loaded successfully from disk.");
        return lines;
    }

    public static void clearList(ArrayList<String> l)
    {
        l.clear();
        System.out.println("\nList cleared successfully.");
    }

    public static void saveList(ArrayList<String> l, String name) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(new File(name)));
        int i;

        for(i = 0; i < l.size(); ++i)
            out.println(l.get(i));
        System.out.println("\nList saved successfully to disk.");
    }

    public static void viewList(ArrayList<String> l)
    {
        int i;
        if(l.size() > 0)
        {
            System.out.println();
            for(i = 0; i < l.size(); ++i)
                System.out.println((i+1) + ") " + l.get(i));
        }
        else
            System.out.println("List is empty!");

    }

    public static void main(String[] args) throws IOException
    {

        Scanner sc = new Scanner(System.in);
        char ch, d;
        boolean needsToBeSaved = false;
        ArrayList<String> list = new ArrayList<String>();
        String file_name = "";

        loop:
        do
        {

            System.out.println("\n******MENU*****");
            System.out.println("A – Add an item to the list");
            System.out.println("D – Delete an item from the list");
            System.out.println("O – Open a list file from disk");
            System.out.println("S – Save the current list file to disk");
            System.out.println("C – Clear removes all the elements from the current list");
            System.out.println("V - View the current list");
            System.out.println("Q – Quit the program");
            System.out.print("\nEnter your choice: ");
            ch = sc.next().toUpperCase().charAt(0);
            switch(ch)
            {
                case 'A':

                    list = addItem(list);
                    needsToBeSaved = true;
                    break;
                case 'D':

                    list = delItem(list);
                    needsToBeSaved = true;
                    break;
                case 'O':

                    if(needsToBeSaved == true && (list.size() > 0 || file_name != "" ))
                    {
                        System.out.println("\nCurrent List is not saved yet. Do you really want to discard current list & load list from file? (Y/N) ");
                        d = sc.next().toUpperCase().charAt(0);
                        if(d == 'Y')
                        {
                            System.out.print("\nEnter list filename: ");
                            sc.nextLine();
                            file_name = sc.nextLine();
                            list = loadList(file_name);
                        }
                    }
                    else
                    {
                        System.out.print("\nEnter list filename: ");
                        sc.nextLine();
                        file_name = sc.nextLine();
                        list = loadList(file_name);
                    }
                    break;
                case 'S':
                    if(file_name == null)
                    {
                        System.out.print("\nEnter filename for saving the list: ");
                        file_name = sc.nextLine();
                    }
                    saveList(list, file_name);
                    needsToBeSaved = false;
                    break;
                case 'C':
                    clearList(list);
                    needsToBeSaved = false;
                    break;
                case 'V':
                    viewList(list);
                    break;
                case 'Q':
                    if(needsToBeSaved == true  && (list.size() > 0 || file_name != "" ))
                    {
                        System.out.println("\nCurrent List is not saved yet and is gone once you exit. Do you really want to quit? (Y/N) ");
                        d = sc.next().toUpperCase().charAt(0);
                        if(d == 'Y')
                        {
                            break loop;
                        }
                    }
                    else
                        break loop;
                    break;
                default:
                    System.out.println("Invalid choice. Please re-try!");
            }
        }while(true);
    }
}

import java.io.*;
import java.util.ArrayList;

public class Main {
    static int ctr = 0;
    static ArrayList<Boolean> exists = new ArrayList<>();

    /**
     * adds a note to the folder
     * @param note note to add
     */
    public static void addNote(Note note){
        try (FileOutputStream fs= new FileOutputStream("./files/note" + (ctr++) + ".txt");){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(note);
            exists.add(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints a note (fully) with the given index
     * @param index index of the desired note
     */
    public static void readNote(int index) {
        if(exists.get(index)) {
            try (FileInputStream fi = new FileInputStream("./files/note" + (index) + ".txt");) {
                ObjectInputStream is = new ObjectInputStream(fi);
                Note note = (Note) is.readObject();
                System.out.println(note.getTitle() + " : " + note.getContent() + " (date:" + note.getDate() + ")");

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * prints first 10 chars of every file's content
     */
    public static void showNotes(){
        for (int i = 0 ; i<ctr ; i++){
            if(exists.get(i)) {
                try (FileInputStream fi = new FileInputStream("./files/note" + (i) + ".txt");) {
                    System.out.print("" + (i) + " : ");
                    ObjectInputStream is = new ObjectInputStream(fi);
                    Note note = (Note) is.readObject();
                    System.out.print(note.getContent().substring(0,9));
                    System.out.print(" ...");
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                System.out.println();
            }
        }
    }

    /**
     * delete a note with an index
     * @param index index of the target note
     */
    public static void deleteNote(int index){
        File file = new File("./files/note" + (index) + ".txt");
        if(file.delete()) {
            System.out.println("deleted successfuly");
            exists.set(index , false);
        }else
            System.out.println("could not delete");
    }


    public static void main(String[] args) {
        Note note1 = new Note("title1" , "first note to be written" , "10/10/2021");
        Note note2 = new Note("title2" , "this is the second note's content" , "12/20/2021");

        addNote(note1);
        addNote(note2);

        showNotes();

        readNote(1);
        deleteNote(1);
        showNotes();
    }
}

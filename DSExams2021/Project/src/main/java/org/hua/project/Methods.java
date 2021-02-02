package org.hua.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Methods {
    //ArrayList για κάθε κλάση
    private static ArrayList<Movies> movieList=new ArrayList<>();
    private static ArrayList<Rating> ratingList=new ArrayList<>();
    private static ArrayList<User> userList=new ArrayList<>();

    //Ονόματα αρχείων στο root του φακέλου
    private static String fileName1="movies.dat";
    private static String fileName2="ratings.dat";
    private static String fileName3="users.dat";


    public static void loadAllFiles(){
        try {
            //Φορτώνω τα αρχεία στα ArrayLists
            loadFile(fileName1);
            loadFile(fileName2);
            loadFile(fileName3);

            //Συγκρίνω και βρίσκω τις γραμμές που θέλω να εκτυπώσω
            matchMovieWithRating();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Παίρνω το BufferedReader για να διαβάσω όλες τις γραμμές του αρχείου που δίνω
     */
    private static void loadFile(String fileName) throws IOException {
        String currentLine;
        BufferedReader inputCodesStream = readFile(fileName);
        if (inputCodesStream == null) {
            System.out.println("Didn't find "+fileName +", exiting now!");
            System.exit(0);
        }
        //Για κάθε γραμμή καλώ την breakLine* για να χωρίσω τις λέξεις με ένα StringTokenizer
        while ((currentLine = inputCodesStream.readLine()) != null) {
            if (fileName==fileName1){//movieList
                breakLineFromMovies(movieList,currentLine);
            }else  if (fileName==fileName2){//ratingList
                breakLineForRatings(ratingList,currentLine);
            }else if (fileName==fileName3){//UserList
                breakLineForUser(userList,currentLine);
            }
        }
    }


    /**
     * Διαβάζω το αρχείο που λέω με έναν buffered reader και το επιστρέφω
     */
    private static BufferedReader readFile(String fileName) {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            return inputStream; //returns the whole file as a bufferedReader
        } catch (FileNotFoundException ex) { //If one or more files aren't in the project folder
            System.out.println("file " + fileName + " was not found.");
            return null;
        }
    }

    /**Τρέχω 3 iterators για κάθε List,
     * ελέγχω αν το MovieID του Rating και του Movies
     * και μετά το UserID του User και του Rating
     *Αν βρω το διπλό match το εκτυπώνω
     */
    private static void matchMovieWithRating(){
        String lineToWrite="";
        for (Rating r:ratingList){
            for (Movies m:movieList){
                if (r.getMovieID().equals(m.getMovieID())) {
                    for (User u : userList) {
                        if (u.getRowNum().equals(r.getUserID())){
                            lineToWrite=u.getRowNum()+"::"+u.getNum1()+"::"+u.getNum2()+"::"+u.getNum3()+"::"+u.getGender()+"::"+r.getUserID()+"::"+r.getRating()+"::"+r.getMovieID()+"::"+r.getTimestamp()+"::"+m.getTitle()+"::"+m.getGenres();
                            System.out.println(lineToWrite);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**Η μέθοδος αυτή σπάει την γραμμή που δίνω StringTokenizer με :: για delim.
     * Τα αποθηκεύω σε μια προσωρινή ArrayList και μετά τα βάζω εκεί που πρέπει
     * @param currentList Η λίστα που θέλω να τα αποθηκεύσω
     * @param currentLine Η τωρινή γραμμή που θέλω να σπάσω
     */

    private static void breakLineFromMovies(ArrayList<Movies> currentList, String currentLine){
        String tmp;
        StringTokenizer st = new StringTokenizer(currentLine);
        ArrayList<String> tempList=new ArrayList<>();

        while (st.hasMoreTokens()) {
            tmp = st.nextToken("::");
            tempList.add(tmp);
        }
        currentList.add(new Movies(tempList.get(0),tempList.get(1),tempList.get(2)));
    }

    private static void breakLineForRatings(ArrayList<Rating> currentList, String currentLine){
        String tmp;
        StringTokenizer st = new StringTokenizer(currentLine);
        ArrayList<String> tempList=new ArrayList<>();

        while (st.hasMoreTokens()) {
            tmp = st.nextToken("::");
            tempList.add(tmp);
        }
        currentList.add(new Rating(tempList.get(0),tempList.get(1),tempList.get(2),tempList.get(3)));
    }
    private static void breakLineForUser(ArrayList<User> currentList,String currentLine){
        String tmp;
        StringTokenizer st = new StringTokenizer(currentLine);
        ArrayList<String> tempList=new ArrayList<>();

        while (st.hasMoreTokens()) {
            tmp = st.nextToken("::");
            tempList.add(tmp);
        }
        currentList.add(new User(tempList.get(0),tempList.get(1),tempList.get(2),tempList.get(3),tempList.get(4)));
    }
}

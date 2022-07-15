import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hangman2 {

    static String formatWord(String sw, String g){
        String correct =Arrays.stream(sw.split("")).map(sl -> g.contains(sl) ? sl : "_").reduce(String::concat).get();
        return correct;
    }

    static Scanner askName = new Scanner(System.in);

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<String> words;
        try (Stream<String> line = Files.lines(Paths.get("C:\\Users\\brefo\\Documents\\IdeaProjects\\test\\src\\dictionary.txt"), StandardCharsets.ISO_8859_1)) {
            words = line.collect(Collectors.toList());
        }
        //System.out.println(words);
        System.out.println("What is your Name? ");
        String name = askName.nextLine();
        System.out.println("Hi, " + name);

        String hidden_text = words.get((int)(Math.random()*words.size()));
        System.out.println(hidden_text);
        String guesses = "";
        int score = 6;
        boolean gameOn = true;
        while(gameOn){
            System.out.println("take a guess: ");
            guesses += input.next();
            String answer = formatWord(hidden_text, guesses);
            if(answer.contains(guesses)){
                System.out.println(answer);
                drawHangman(score);
            }else{
                System.out.println(answer);
                score-= 1;
                drawHangman(score);
                System.out.println("Try Again:");
            }
            if (score == 0 ||!answer.contains("_")){
                gameOn = false;
            }
        }
        highScore(name, score);

    }


    public static void highScore(String name, int score){
        final String filePath ="C:\\Users\\brefo\\Documents\\IdeaProjects\\test\\src\\highScore.txt";
        File scoreFile = new File(filePath);
        BufferedWriter bf = null;
        BufferedReader br = null;

        Map<String, Integer> highScore = new HashMap<String, Integer>();


        try {
            br = new BufferedReader( new FileReader(scoreFile));

            String line = null;

            while ((line = br.readLine()) != null){

                String[] parts = line.split(":");

                String newName = parts[0].trim();
                int number = Integer.valueOf(parts[1].trim());

                if (!newName.equals("")){
                    highScore.put(newName, number);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            bf = new BufferedWriter(new FileWriter(scoreFile));
            if (highScore.containsKey(name)){
                if(highScore.get(name) < score){
                    bf.write( name + ":" + score);
                    bf.newLine();
                    bf.flush();
                }
            }else {
                bf.write( name + ":" + score);
                bf.newLine();
                bf.flush();
            }

        }catch (IOException e){
            e.printStackTrace();
        }


        if (highScore.containsKey(name)){
            if(highScore.get(name) < score){
                highScore.put(name, score);
                System.out.println("Hi, "+name+" your Highest Score is! "+ score);
                System.out.println("New Highest Score!!!");
            }else {
                System.out.println("Hi, "+name+" your Highest Score is! "+ highScore.get(name));
            }
        }else {
            highScore.put(name,score);
            System.out.println("Hi, "+name+" your Highest Score is! "+ score);
            System.out.println("New Highest Score!!!");
        }

    }

    public static void drawHangman(int l) {
        if(l == 6) {
            System.out.println("|----------");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else if(l == 5) {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else if(l == 4) {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|    |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else if(l == 3) {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|   -|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else if(l == 2) {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|   -|-");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else if(l == 1) {
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|   -|-");
            System.out.println("|   /");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
        else{
            System.out.println("|----------");
            System.out.println("|    O");
            System.out.println("|   -|-");
            System.out.println("|   /|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
        }
    }
}

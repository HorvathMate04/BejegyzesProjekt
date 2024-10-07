package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        List<Bejegyzes> bejegyzesek = new ArrayList<Bejegyzes>();
        bejegyzesek.add(new Bejegyzes("Csiga Pál", "Szeretem a leveleket"));
        bejegyzesek.add(new Bejegyzes("Fazekas Márton", "Ma almát ettem"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hány bejegyzést szeretne megadni?:");
        String numOfPosts = scanner.nextLine();
        try{
            Integer.parseInt(numOfPosts);
            for (int i = 0; i < Integer.parseInt(numOfPosts); i++) {
                System.out.println("Szerző neve:");
                String name = scanner.nextLine();
                System.out.println("Tartalom:");
                String tartalom = scanner.nextLine();
                bejegyzesek.add(new Bejegyzes(name, tartalom));
            }
        }
        catch(NumberFormatException e){
            System.out.println("invalid number");
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader("bejegyzesek.csv"));
            String s = br.readLine();
            while(s != null && !s.isEmpty()) {
                String[] adatok = s.split(";");
                bejegyzesek.add(new Bejegyzes(adatok[0], adatok[1]));
                s = br.readLine();
            };
            br.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        int numOfLikes = bejegyzesek.size() * 20;
        Random randomGenerator = new Random();
        for (int i = 0; i < numOfLikes; i++) {
            int index = randomGenerator.nextInt(bejegyzesek.size());
            Bejegyzes bejegyzes = bejegyzesek.get(index);
            bejegyzes.like();
        }

        System.out.println("Írjon be valami szöveget?:");
        String newContent = scanner.nextLine();
        Bejegyzes bejegyzes = bejegyzesek.get(1);
        bejegyzes.setTartalom(newContent);

        for (Bejegyzes bej : bejegyzesek)
        {
            System.out.println("-----------------------------------------------------");
            System.out.println(bej);
        }

        boolean postWithXLikes = false;
        Bejegyzes mostLiked = bejegyzesek.getFirst();
        int numOfFewLikes = 0;
        for (Bejegyzes bej : bejegyzesek)
        {
            if(bej.getLikeok() > mostLiked.getLikeok()){
                mostLiked = bej;
            }
            if(bej.getLikeok() >= 35){
                postWithXLikes = true;
            } else if (bej.getLikeok() < 15) {
                numOfFewLikes++;
            }
        }


        System.out.println("Részadatok::");
        System.out.println("Van 35 vagy több lájkal rendelkező poszt?: " + postWithXLikes);
        System.out.println("Leglájkoltabb poszt: " + mostLiked);
        System.out.println("15-nél kevesebb likeot kapott posztok száma: " + numOfFewLikes);

        List<Bejegyzes> sortedBejegyzesek = new ArrayList<Bejegyzes>();

        for (int i = 0; i < bejegyzesek.size();) {
            Bejegyzes maxLikes = bejegyzesek.get(i);
            int indexOfElement = i;
            for (int j = 0; j < bejegyzesek.size(); j++) {
                if(bejegyzesek.get(j).getLikeok() > maxLikes.getLikeok()){
                    indexOfElement = j;
                    maxLikes = bejegyzesek.get(j);
                }
            }
            sortedBejegyzesek.add(maxLikes);
            bejegyzesek.remove(indexOfElement);
        }

        try {
            File myObj = new File("sorted.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter myWriter = new FileWriter("sorted.txt");

            System.out.println("SORTED LIST:");
            StringBuilder sorted = new StringBuilder();
            for (Bejegyzes bej : sortedBejegyzesek)
            {
                System.out.println("-----------------------------------------------------");
                System.out.println(bej);
                sorted.append(bej.toString()).append("\n");

            }
            myWriter.write(sorted.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
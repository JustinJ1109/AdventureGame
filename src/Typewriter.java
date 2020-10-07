import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Scanner;

public class Typewriter {

    private static int index;
    private static int listIndex;
    private static String curText;
    private static String text;
    private String tempText;

    private final int MAX_LINE_LENGTH = 50;
    private final int BUFFER_LINE_SIZE = 5;
    private final int V_LINE_SPACING = 5;

    private Scanner scnr;

    private ArrayList<String> wrappedText;

    public Typewriter() {
        curText = "";
        index = 0;
        listIndex = 0;
        text = "";
        tempText = "";
        wrappedText = new ArrayList<>(0);
    }

    public Typewriter(String text) {
        curText = "";
        index = 0;
        listIndex = 0;
        Typewriter.text = text;
        wrappedText = new ArrayList<>(0);
        tempText = "";
    }

    public void wrapText() {
        scnr = new Scanner(text);

        String temp = "";
        int lineLength = 0;


        while (scnr.hasNext()) {
            if (MAX_LINE_LENGTH - (temp += scnr.next() + " ").length() < 0) { // over bounds
                wrappedText.add(temp); // dump what its got
                temp = "";
            }
        }
        wrappedText.add(temp.trim());

        for (int j = 0; j < wrappedText.size(); j++)
            System.out.println("lists: " + wrappedText.get(j).trim());
    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.drawString(getCurText(), 40, 60);

    }

    private int getTextDrawHeight() {

        if (getCurrentTextIndex() == MAX_LINE_LENGTH) {

        }


        return 0;
    }

    private int getCurrentTextIndex() {



        return 0;
    }

    public void tick() {
//
//        if (index + 1 <= text.length()) {
//            curText += text.charAt(index);
//            index++;
//        }

//        if (!curText.equals(text))
//            for (int i = 0; i < wrappedText.size(); i++) { // for every break in text
//                for (int l = 0; l < wrappedText.get(i).length(); l++) { // for every letter in that break
//                    curText += wrappedText.get(i).charAt(l); // add the char at the index to curText
//                }
//            }

        if (wrappedText.size() > 0 && listIndex < wrappedText.size()) {
            if (tempText.equals(wrappedText.get(listIndex))) {
                listIndex++;
                index = 0;
                tempText = "";
            }

            if (listIndex < wrappedText.size()) {
                tempText += wrappedText.get(listIndex).charAt(index);
                curText += wrappedText.get(listIndex).charAt(index);
                index++;
            }
        }
    }





    public String getCurText() {
        return curText;
    }

    public void setText(String text) {
        if (!isTyping()) {
            clearText();
            Typewriter.text = text;
            wrapText();
        }
    }

    public void clearText() {
        curText = "";
        text = "";
        index = 0;
    }

    public boolean isTyping() {
        if (!curText.equals(text))
            return true;
        return false;
    }

    public void skipTypeWriter() {
        curText = text;
        index = text.length();
    }
}

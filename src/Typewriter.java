import org.davidmoten.text.utils.WordWrap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.ClientInfoStatus;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Typewriter {

    /** Used to hold the current index to be iterated through **/
    private static int index;
    private static int listIndex;

    private Game game;

    /** Holds the current text to be assigned to specified arrayList to be displayed **/
    private static String curText;

    /** Holds the entire text to be broken into arrayList parts**/
    private static String text;

    /** boolean value of whether text was skipped or not **/
    private boolean skipped = false;

    /** Sets the horizontal bounds of the text **/
    private final int MAX_LINE_LENGTH;

    /** Spacing distance offset between lines of text **/
    private final int V_LINE_SPACING = 15;

    /** Holds all the text that is to be typed **/
    private ArrayList<String> wrappedText;

    /** Gets iterated through char by char and is displayed via render **/
    private ArrayList<String> curWrappedText;

    public Typewriter(Game game) {
        this.game = game;
        curText = "";
        MAX_LINE_LENGTH = 70;


        index = 0;
        listIndex = 0;

        text = "";
        wrappedText = new ArrayList<>(0);
        curWrappedText = new ArrayList<>(0);
    }

    public Typewriter(Game game, int MAX_LINE_LENGTH) {
        this.game = game;
        curText = "";
        this.MAX_LINE_LENGTH = MAX_LINE_LENGTH;


        index = 0;
        listIndex = 0;

        text = "";
        wrappedText = new ArrayList<>(0);
        curWrappedText = new ArrayList<>(0);
    }

    /**
     * Responsible for organizing the text into separate arrayList objects
     * to be iterated through later.
     */
    public void wrapText() {
        Scanner scnr = new Scanner(text);

        String temp = "";

        while (scnr.hasNext()) {
            if (MAX_LINE_LENGTH - (temp += scnr.next() + " ").length() < 0) { // over bounds
                wrappedText.add(temp); // dump what its got
                temp = "";
            }
        }
        wrappedText.add(temp.trim());

        for (int j = 0; j < wrappedText.size(); j++)
            curWrappedText.add(""); // add how many lists wrappedText has

    }

    /**
     * Loops through all of the currently applied text lines and displays them on screen
     *
     * @param g
     */
    public void render(Graphics g, int x, int y, int width, int height) {

        g.setColor(Color.white);

        for (int i = 0; i < curWrappedText.size(); i++) { // for every line of text, render

            g.drawString(curWrappedText.get(i),
                    x + 10, (y + height + y) / 2 + (i - 1) * V_LINE_SPACING);
        }

        // X : width + x) / 2 - (wrappedText.get(i).length()) * 3 + 60
    }

    private int centerX(int index) {
        return (game.getWidth() / 2) - (wrappedText.get(index).length()) * 3;
    }

    private int centerY(int index) {
        return (game.getHeight() / (wrappedText.size()) + game.getHeight() / 2);
    }

    /**
     * Iterates through each arrayList object and adds them one by one to the displayed list
     * to give a typewriter effect
     */
    public void tick() {


        if (!skipped && wrappedText.size() > 0 && listIndex < curWrappedText.size()) { // there is text
            if (curText.equals(wrappedText.get(listIndex))) { // is the current temp string equal to the one it drew from
                curWrappedText.set(listIndex, curText);

                listIndex++; // work on next list

                index = 0;
                curText = "";
            }

            if (listIndex < curWrappedText.size())
                if (wrappedText.get(listIndex).length() > index) {
                    curText += wrappedText.get(listIndex).charAt(index);
                    curWrappedText.set(listIndex, curText);
                    index++;
                }
        }
    }

    /**
     * Sets the text that should be displayed. Also clears all previous text to
     * prevent overlap
     *
     * @param text paragraph to be displayed
     */
    public void setText(String text) {
        if (!isTyping()) {
            clearText();
            Typewriter.text = text;
           wrapText();
        }
    }

    /**
     * Clears all current settings and displays
     */
    public void clearText() {
        curText = "";
        text = "";
        index = 0;
        listIndex = 0;
        skipped = false;

        wrappedText.clear();
        curWrappedText.clear();

    }

    /**
     * Boolean that determines if the class is currently typing something out.
     *
     * @return
     */
    public boolean isTyping() {
        if (!wrappedText.equals(curWrappedText))
            return true;
        return false;
    }

    /**
     * When called, skips the animation and automatically sets all arrayLists
     * to the full text
     */
    public void skipTypeWriter() {

        if (isTyping()) {
            int i = 0;
            while (i < curWrappedText.size()) {
                curWrappedText.set(i, wrappedText.get(i));
                i++;
            }
            index = curWrappedText.get(i - 1).length();
            listIndex = curWrappedText.size();
            curText = wrappedText.get(i - 1);

            skipped = true;

        }
    }
}

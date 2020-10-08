public class StoryTeller {


    private static int index;
    private String[] paragraphs;

    public StoryTeller() {
        index = 0;
        paragraphs = new String[] {"This one is going to be quite a long one though. To be able to test the text wrapping, I will need one of sizeable length.",
                "This is the first sentence. We can see that the writer can effectively change narration without issue!", "Here, we have the final sentence in this example!",
        };
    }

    public String getNextPara() {

        if (index + 1 <= paragraphs.length) {
            String temp = paragraphs[index];
            index++;
            return temp;
        }
        return "";
    }
}

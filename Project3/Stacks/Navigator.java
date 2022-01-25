package stacks;

/**
 * The class that can play with links. Basically this class does what real
 * browser does, having back links, forward links and currentLink. And is able
 * to play with those links like browser does.
 */
public class Navigator {

    private String CurrentLink;
    private StackList<String> backLinks = new StackList<>("Back");
    private StackList<String> forwardLinks = new StackList<>("Forward");
    private static String DEFAULT_CURRENT_LINKS = null;

    //Default Constructor : set CurrentLink = null.
    public Navigator() {
        CurrentLink = DEFAULT_CURRENT_LINKS;
    }

    //go to a new link(inputLink), and set the backLinks. Then, clear the forwardLinks.
    public void setCurrentLink(String inputLink) {
        //inputLink can't be null.
        if (inputLink != null) {
            backLinks.push(CurrentLink);
            CurrentLink = inputLink;
            forwardLinks.clear();
        }
    }

    //get the the current link.
    public String getCurrentLink() {
        return CurrentLink;
    }

    /**
     * go to backlink from currentlink if we do have backlinks. And the old currentLink will go the top of forwardLinks
     * if we do not have backlinks, do nothing and print out the warning message.
     */
    public void goBack() {
        //check if backLinks is empty.
        if (!(backLinks.isEmpty())) {
            forwardLinks.push(CurrentLink);
            CurrentLink = backLinks.pop();
        } else {
            //empty case: print out warning message.
            System.out.println();
            System.out.print("WARNING, No backlinks available!!");
        }
    }

    /**
     * go to forwardlink from currentlink if we do have forwardlinks. And the old currentLink will go the top of backLinks
     * if we do not have forwardlinks, do nothing and print out the warning message.
     */
    public void goForward() {
        //check if forwardLinks is empty.
        if (!forwardLinks.isEmpty()) {
            backLinks.push(CurrentLink);
            CurrentLink = forwardLinks.pop();
        } else {
            //empty case: print out warning message.
            System.out.println();
            System.out.print("WARNING, No forwardlinks available!!");
        }
    }

    //get the stack of back links.
    public StackList<String> getBackLinks() {
        return backLinks;
    }

    //get the stack of forward links.
    public StackList<String> getForwardLinks() {
        return forwardLinks;
    }
}

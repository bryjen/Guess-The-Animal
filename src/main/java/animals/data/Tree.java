package animals.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.w3c.dom.Node;

import java.util.prefs.NodeChangeEvent;

@JsonInclude
public class Tree {

    public boolean isQueryNode;
    public String query;
    public String fact;

    public String animal;
    public String animalWithPrefix;

    public Tree no;
    public Tree yes;

    @JsonIgnore
    public Tree parent;
    @JsonIgnore
    public boolean checked;

    public Tree() {
        super();
    }

    public Tree(String animal, String animalWithPrefix) {
        this.no = null;
        this.yes = null;
        this.isQueryNode = false;

        this.animal = animal;
        this.animalWithPrefix = animalWithPrefix;

        this.parent = null;
    }

    public void extend(String newAnimal, String newAnimalWithPrefix, String fact, String query, boolean shiftCurrentNodeRight) {
        if (shiftCurrentNodeRight) {
            yes = new Tree(animal, animalWithPrefix);
            yes.parent = this;
            no = new Tree(newAnimal, newAnimalWithPrefix);
            no.parent = this;
        } else {
            no = new Tree(animal, animalWithPrefix);
            no.parent = this;
            yes = new Tree(newAnimal, newAnimalWithPrefix);
            yes.parent = this;
        }
        this.isQueryNode = true;
        animal = null;
        animalWithPrefix = null;

        this.query = query;
        this.fact = fact;
    }

    /**Recursive Function - In-Order traversal
     * Although the parents of a root cannot be serialized using ObjectMapper, its children can without causing
     * an infinite loop. The function traverses the loop. The function peeks if any of the node's child is not null,
     * if so, it sets its parent to the current node.
     * @param node The current node.
     */
    public void initParents(Tree node) {
        if (!node.no.checked) {
            if (node.no.parent == null) {
                node.no.parent = node;
            }

            if (node.no.isQueryNode) {
                initParents(node.no);
            }
        }
        if (!node.yes.checked) {
            if (node.yes.parent == null) {
                node.yes.parent = node;
            }

            if (node.yes.isQueryNode) {
                initParents(node.yes);
            }
        }
    }

    public void deleteNode() {

        Tree parentsParent = this.parent.parent;

        if (parentsParent == null) {
            System.out.println("here");
            if (this.parent.no == this) {
                this.parent = new Tree(this.parent.yes.animal, this.parent.yes.animalWithPrefix);
            } else {
                this.parent = new Tree(this.parent.no.animal, this.parent.no.animalWithPrefix);
            }
            return;
        }

        if (parentsParent.yes.no == this || parentsParent.yes.yes == this) {
            parentsParent.yes = new Tree(this.animal, this.animalWithPrefix);
            parentsParent.yes.parent = parentsParent;
        } else {
            parentsParent.no = new Tree(this.animal,this.animalWithPrefix);
            parentsParent.no.parent = parentsParent;
        }
    }
}

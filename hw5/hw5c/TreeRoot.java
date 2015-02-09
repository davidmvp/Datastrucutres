import java.util.StringTokenizer;
/**
 * TreeRoot class.
 * @author Da Lu
 * dlu6@jhu.edu
 * hw5
 *
 * @param <T>
 */
public class TreeRoot<T> {
    /**
     * Parent root of this root.
     */
    private TreeRoot<T> parent;
    /**
     * Type T data that this root has.
     */
    private T data;
    /**
    * The left child of this root.
    */
    private TreeRoot<T> left;
    /**
     * The right child of this root.
    */
    private TreeRoot<T> right;
    /**
     * This root.
     */
    private TreeRoot<T> root;
    /**
     * used to ses if the data is operand or not.
     */
    private boolean operand;
    /**
     * previous root that we just inserted.
     */
    private TreeRoot<T> previous = null;
    /**
     * String used to store the postfix order.
     */
    private String s = "";
    /**
     * the validity of the input.
     */
    private boolean validity = true;

    /**
     * Constrcutor for treeRoot.
     * @param d The data we want to insert.
     */
    public TreeRoot(T d) {

        this(d, null);
        this.root = this;
        this.previous = this.root;

    }
    /**
     * Check the data and see if it is operator or opearnd.
     * @param d Data we are going to insert.
     * @param pare Its parent node.
     */
    public TreeRoot(T d, TreeRoot<T> pare) {

        if (d.equals("&&") || d.equals("||") || d.equals("!")) {
            this.operand = false;
        } else {
            this.operand = true;
        }
        this.data = d;
        this.parent = pare;
        this.left = null;
        this.right = null;
    }


    /**
     * Get the parent of this root.
     * @return parent
     */
    public TreeRoot<T> parent() {
        return this.parent;

    }
    /**
     * Check to see if it is an operand.
     * @return true if it is.
     */
    public boolean isOperand() {
        return this.operand;
    }
    /**
     * Check to see if it is an operator.
     * @return true if it is.
     */
    public boolean isOperator() {
        return !this.operand;
    }

    /**
     * Add child to the root.
     * @param d the data.
     * @return if the child is added, return true.
     */
    public boolean addChild(T d) {

        TreeRoot<T> temp = this.previous;

        if (temp.data.equals("!")) {
            if (d.equals("true") || d.equals("false")) {
                TreeRoot<T> n = new TreeRoot<T>(d);
                temp.left = n;
                n.parent = temp;
                this.previous = n;
                n.operand = true;
                return true;
            } else {

                return false;
            }

        } else if (temp.isOperator()) {

            TreeRoot<T> n = new TreeRoot<T>(d);
            temp.left = n;
            n.parent = temp;
            this.previous = n;
            return true;
        }

        if (temp.isOperand()) {

            temp = temp.parent;
            if (temp.data.equals("!")) {
                temp = temp.parent;
            }
            while (temp != null) {
                if (temp.right == null) {
                    TreeRoot<T> n = new TreeRoot<T>(d);
                    temp.right = n;
                    n.parent = temp;
                    this.previous = n;
                    return true;
                }
                temp = temp.parent;
            }

        }

        return false;
    }

    /**
     * Find out the value of the expression.
     * @return return true if true, false if false.
     */
    public boolean value() {
        Stacks<String> stack = new Stacks<String>();
        boolean value = false;
        String q;
        StringTokenizer token = new StringTokenizer(this.s);
        String str = "";
        while (token.hasMoreTokens()) {

            str = token.nextToken();

            if (str.equals("true") || str.equals("false")) {
                stack.push(str);
            } else if (str.equals("&&") && stack.size() > 1) {
                String temp1 = stack.pop();
                String temp2 = stack.pop();
                value = Boolean.parseBoolean(temp1)
                    && Boolean.parseBoolean(temp2);
                q = new Boolean(value).toString();
                stack.push(q);
            } else if (str.equals("||") && stack.size() > 1) {
                value = Boolean.parseBoolean(stack.pop())
                          || Boolean.parseBoolean(stack.pop());
                q = new Boolean(value).toString();
                stack.push(q);
            } else if (str.equals("!") && stack.size() > 0) {
                q = stack.pop();
                value = !(Boolean.parseBoolean(q));
                q = new Boolean(value).toString();
                stack.push(q);
            }
        }

        return value;
    }
    /**
     * Check if the input is correct.
     * @return true or false.
     */
    public boolean checkValidity() {
        this.checkValidity(this.root);


        return this.validity;

    }

   /**
    * helper method so we can call the function recursively.
    * @param ro the root of the tree.
    */
   public void checkValidity(TreeRoot<T> ro) {
        if (ro == null) {
            return;
        }
        this.checkValidity(ro.left);
        this.checkValidity(ro.right);
        if (ro.data.equals("!") || ro.data.equals("||") || ro.data.equals("&&")
                || ro.data.equals("true") || ro.data.equals("false")) {
            System.out.print("");
        }  else {
            this.validity = false;
            return;
        }
        if (ro.data.equals("!")) {
            if (ro.left == null) {
                this.validity = false;
                return;
            } else {
                return;
            }

        } else if (ro.isOperand()) {
            if (ro.left != null || ro.right != null) {
                this.validity = false;
                return;
            } else {
                return;
            }
        } else if (ro.isOperator()) {
            if (ro.left == null || ro.right == null) {
                this.validity = false;
                return;
            } else {
                return;
            }
        }
        return;
    }

   /**
    * To print the tree in infix order.
    */
    public void infix() {
        this.infix(this.root);

    }
    /**
     * helper method for infix so we can use the recursive method.
     * @param ro ro is the root.
     */
    public void infix(TreeRoot<T> ro) {

        if (ro == null) {
            return;
        }
        if (ro.parent != null && ro == ro.parent.left) {
            System.out.print("(");
        }
        this.infix(ro.left);
        if (ro.parent != null && ro.parent.data.equals("!")) {
            System.out.print(" ! " + ro.data + ") ");
        } else if (ro.data.equals("!")) {
            System.out.print("");
        } else {
            System.out.print(" " + ro.data + " ");
        }

        this.infix(ro.right);
        if (ro.parent != null && ro == ro.parent.right) {
            System.out.print(")");
        }
    }
    /**
     * return a string so the user can read and understand.
     * @return string in post order.
     */
    public String postOrder() {
        this.s = "";
        this.postOrder(this.root);
        return this.s;
    }
    /**
     * helper method for postOrder.
     * @param ro the root of the current tree.
     */
    public void postOrder(TreeRoot<T> ro) {
        if (ro == null) {
            return;
        }
        this.postOrder(ro.left);
        this.postOrder(ro.right);
        this.s = this.s + ro.data + " ";
        System.out.print(ro.data + " ");

    }

}



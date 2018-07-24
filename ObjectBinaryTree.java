/**
 * Class for binary search tree
 */
public class ObjectBinaryTree {
    private ObjectTreeNode root;

    /**
     * Constructor for bst
     */
    public ObjectBinaryTree() {
        root = null;
    }

    /**
     * gets the root of tree
     * @return root
     */
    public ObjectTreeNode getRoot() {
        return root;
    }

    /**
     * sets left child of tree
     * @param parent parent node
     * @param r left
     */
    public void setLeftChild(ObjectTreeNode parent, ObjectTreeNode r) {
        if (parent == null || parent.getLeft() != null) {
            System.out.println("Runtime Error: setLeftChild()");
            System.exit(1);
        }
        parent.setLeft(r);
    }

    /**
     * sets right child of tree
     * @param parent parent node
     * @param r right
     */
    public void setRightChild(ObjectTreeNode parent, ObjectTreeNode r) {
        if (parent == null || parent.getRight() != null) {
            System.out.println("Runtime Error: setRightChild()");
            System.exit(1);
        }
        parent.setRight(r);
    }

    /**
     * inserts into the binary search tree
     * @param o object to be inserted
     */
    public void insertBST(Object o) {
        ObjectTreeNode p, q;

        ObjectTreeNode r = new ObjectTreeNode(o);
        if (root == null)
            root = r;
        else {
            p = root;
            q = root;
            while (q != null) {
                p = q;
                if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                    q = p.getLeft();
                else
                    q = p.getRight();
            }
            if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                setLeftChild(p, r);
            else
                setRightChild(p, r);
        }
    }

    /**
     * inserts into binary tree and checks for case of duplicates
     * @param o object to be inserted
     */
    public void insertBSTDup(Object o) {
        ObjectTreeNode p, q;

        ObjectTreeNode r = new ObjectTreeNode(o);
        if (root == null)
            root = r;
        else {
            p = root;
            q = root;
            while (q != null && ((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) != 0) {
                p = q;
                if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                    q = p.getLeft();
                else
                    q = p.getRight();
            }
            if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                setLeftChild(p, r);
            else if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) > 0)
                setRightChild(p, r);
            else ((TreeComparable) (p.getInfo())).operate((TreeComparable) (r.getInfo()));
        }
    }

    /**
     * searches for item in tree
     * @param o object searched for
     * @return if it is found
     */
    public ObjectTreeNode searchBST(Object o) {
        ObjectTreeNode p;

        ObjectTreeNode r = new ObjectTreeNode(o);
        if (root != null) {
            p = root;
            while (p != null) {
                if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                    p = p.getLeft();
                else if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) > 0)
                    p = p.getRight();
                else
                    return p;
            }
        }
        return null;
    }

    /**
     * preorder traversal of tree
     * @param tree
     */
    public void preTrav(ObjectTreeNode tree) {
        if (tree != null) {
            ((TreeComparable) tree.getInfo()).visit();
            preTrav(tree.getLeft());
            preTrav(tree.getRight());
        }
    }

    /**
     * inorder traversal of tree
     * @param tree
     */
    public void inTrav(ObjectTreeNode tree) {
        if (tree != null) {
            inTrav(tree.getLeft());
            ((TreeComparable) tree.getInfo()).visit();
            inTrav(tree.getRight());
        }
    }

    /**
     * postorder traversal of tree
     * @param tree
     */
    public void postTrav(ObjectTreeNode tree) {
        if (tree != null) {
            postTrav(tree.getLeft());
            postTrav(tree.getRight());
            ((TreeComparable) tree.getInfo()).visit();
        }
    }

    /**
     * deletes node from tree
     * @param o object to be deleted
     */
    public void delete(Object o) {
        ObjectTreeNode s, t, v;
        boolean found = false;

        ObjectTreeNode r = new ObjectTreeNode(o);
        ObjectTreeNode p = root;
        ObjectTreeNode q = null;
        // Search for the node with info key, set p to point to that node and set q to point to its parent, if any.
        while (p != null && !found) {
            if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) == 0)
                found = true;
            else {
                q = p;
                if (((TreeComparable) (r.getInfo())).compareTo((TreeComparable) (p.getInfo())) < 0)
                    p = p.getLeft();
                else
                    p = p.getRight();
            }
        }
        if (found) {
            // Set v to point to the node that will replace the node
            // that p points to.
            if (p.getLeft() == null)
                v = p.getRight();
            else if (p.getRight() == null)
                v = p.getLeft();
            else {
                // the node that p points to has two children;
                // set v to the inorder successor of p;
                // set t to the parent of v
                t = p;
                v = p.getRight();
                s = v.getLeft();  // s is the left child of v
                while (s != null) {
                    t = v;
                    v = s;
                    s = v.getLeft();
                }
                // At this point, v is the inorder successor of p
                if (t != p) {
                    // p is not the parent of v and v = t.getLeft()
                    t.setLeft(v.getRight());
                    // Remove the node that v points to from its
                    // current position to take the place of the
                    // node that p points to.
                    v.setRight(p.getRight());
                }
                v.setLeft(p.getLeft());
            }
            // Insert the node that v points to into the position
            // formally occupied by the node that p points to
            if (q == null)
                // The node that p points to was the root of the tree
                root = v;
            else if (p == q.getLeft())
                q.setLeft(v);
            else q.setRight(v);
        }
    }
}

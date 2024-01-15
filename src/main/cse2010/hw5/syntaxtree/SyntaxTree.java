package cse2010.hw5.syntaxtree;

import cse2010.hw5.tree.Position;
import cse2010.hw5.tree.binary.linked.LinkedBinaryTree;
import static cse2010.hw5.syntaxtree.Utils.isNumeric;
import static cse2010.hw5.tree.TreeUtils.spaces;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Linked-based Arithmetic expression tree.
 */
public class SyntaxTree extends LinkedBinaryTree<String> {

    /**
     * Evaluate syntax tree.
     * @return evaluated arithmetic expression
     */
    public double evaluate() {
        return evaluate(root());
    }

    private double evaluate(Position<String> position) {
        if (isExternal(position)) {
            if (isNumeric(position.getElement())) {
                return Double.parseDouble(position.getElement());
            } else {
                throw new IllegalArgumentException("Invalid Operand");
            }
        } else {
            double leftValue = evaluate(left(position));
            double rightValue = evaluate(right(position));
            return calculate(position.getElement(), leftValue, rightValue);
        }
    }

    private double calculate(String operator, double operand1, double operand2) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new IllegalArgumentException("Invalid Operator");
        };
    }

    /**
     * Returns postfix expression corresponding to this syntax tree.
     * @return postfix representation of this syntax tree
     */
    public String toPostFix() {
        return cvtToString(postOrder());
    }

    /**
     * Returns prefix expression corresponding to this syntax tree.
     * @return prefix representation of this syntax tree
     */
    public String toPreFix() {
        return cvtToString(preOrder());
    }

    /**
     * Returns fully parenthesized infix expression corresponding to this syntax tree.
     * @return fully parenthesized prefix representation of this syntax tree
     */
    public String toInfix() {
        return toInfix(root());
    }

    /**
     * Returns fully parenthesized infix expression corresponding to this syntax subtree.
     * @return fully parenthesized infix representation of this syntax subtree
     */
    private String toInfix(Position<String> position) {
        StringBuilder builder = new StringBuilder();

        if (hasLeft(position)) {
            builder.append("(");
            builder.append(toInfix(left(position)));
            builder.append(" ");
        }

        builder.append(position.getElement());

        if (hasRight(position)) {
            builder.append(" ");
            builder.append(toInfix(right(position)));
            builder.append(")");
        }

        return builder.toString();
    }

    /**
     * Returns a formatted string representation of tree hierarchy.
     * The formatted string representation of the expression tree corresponding
     * to {@code (a + b) * (c - d)} looks as follows:
     * *
     *   +
     *     a
     *     b
     *   -
     *     c
     *     d
     * @return a formatted string representation of tree hierarchy
     */
    public String showTree() {
        return indentTree(root(), 0);
    }

    /**
     * Returns a formatted string representation of the subtree hierarchy.
     * @param position a node position
     * @param level indentation level; 0 means no indentation; the unit of
     *              the indentation level is two spaces.
     * @return  a formatted string representation of the subtree hierarchy
     */
    private String indentTree(Position<String> position, int level) {
        StringBuilder builder = new StringBuilder();

        builder.append(spaces(level));
        builder.append(position.getElement());
        builder.append("\n");

        if (hasLeft(position)) {
            builder.append(indentTree(left(position), level + 1));
        }
        if (hasRight(position)) {
            builder.append(indentTree(right(position), level + 1));
        }

        return builder.toString();
    }

    /**
     * Convert list of Positions to a serialized string in which
     * each element of the position is delimited by the ' ' character.
     * @param positions a list of Position<String>s
     * @return a string serialized as a list of elements
     */
    private String cvtToString(List<Position<String>> positions) {
        return positions.stream().map(Position::getElement).collect(Collectors.joining(" "));
    }
}
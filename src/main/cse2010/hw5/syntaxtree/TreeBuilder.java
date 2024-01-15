package cse2010.hw5.syntaxtree;

import cse2010.hw5.tree.Position;
import static cse2010.hw5.syntaxtree.Utils.parse;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeBuilder {
    private static Map<String, Integer> operators = new HashMap<>();
    static {
        // We will consider only four binary operators: *, /, +, -
        operators.put("(", 1);
        operators.put("*", 2);
        operators.put("/", 2);
        operators.put("+", 3);
        operators.put("-", 3);
        operators.put(")", 4);
        // you may put other entry/entries if needed ...
    }

    private static Stack<SyntaxTree> operandStack = new Stack<>();
    private static Stack<String> operatorStack = new Stack<>();

    /**
     * Construct a syntax free from infix arithmetic expression.
     * @param expression an infix arithmetic expression
     * @return the syntax tree
     */
    public static SyntaxTree buildFromInfix(String expression) {
        initStacks();

        String[] tokens = parse(expression);

        for (String token : tokens) {
            if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    createSubTree();
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                }
            } else if (operators.containsKey(token)) {
                int currentPriority = operators.get(token);

                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")
                        && operators.containsKey(operatorStack.peek())
                        && currentPriority >= operators.get(operatorStack.peek())) {
                    createSubTree();
                }

                operatorStack.push(token);
            } else {
                SyntaxTree operand = new SyntaxTree();
                operand.addRoot(token);
                operandStack.push(operand);
            }
        }

        while (!operatorStack.isEmpty()) {
            createSubTree();
        }

        return operandStack.pop();
    }

    private static void createSubTree() {
        String operator = operatorStack.pop();

        SyntaxTree operatorTree = new SyntaxTree();
        operatorTree.addRoot(operator);

        if (!operandStack.isEmpty()) {
            SyntaxTree rightOperand = operandStack.pop();
            if (!operandStack.isEmpty()) {
                SyntaxTree leftOperand = operandStack.pop();
                operatorTree.attach(operatorTree.root(), leftOperand, rightOperand);
            }
            operandStack.push(operatorTree);
        }
    }

    /**
     * Construct a syntax free from postfix arithmetic expression.
     * @param expression a postfix arithmetic expression
     * @return the syntax tree
     */
    public static SyntaxTree buildFromPostfix(String expression) {
        initStacks();

        String[] tokens = parse(expression);

        for (String token : tokens) {
            if (operators.containsKey(token)) {
                SyntaxTree operator = new SyntaxTree();
                Position<String> operatorPosition = operator.addRoot(token);
                SyntaxTree rightOperand = operandStack.pop();
                SyntaxTree leftOperand = operandStack.pop();
                operator.attach(operatorPosition, leftOperand, rightOperand);
                operandStack.push(operator);
            } else {
                SyntaxTree operand = new SyntaxTree();
                operand.addRoot(token);
                operandStack.push(operand);
            }
        }

        return operandStack.pop();
    }

    /**
     * Reset stacks
     */
    private static void initStacks() {
        operandStack.clear();
        operatorStack.clear();
    }
}
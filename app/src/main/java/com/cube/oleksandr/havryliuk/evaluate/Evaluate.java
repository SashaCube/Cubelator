package com.cube.oleksandr.havryliuk.evaluate;

public class Evaluate {

    private int pos = -1, ch;
    private String str;
    private boolean error = false;

    public double calculate(final String str) {
        this.str = str;
        return parse();
    }

    public void nextChar() {
        pos++;
        if (pos < str.length()) {
            ch = str.charAt(pos);
        } else {
            ch = -1;
        }
    }

    public boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public double parse() {
        pos = -1;
        nextChar();
        double x = parseExpression();
        if (pos < str.length()) {
            error = true;
            x = 0;
        }
        return x;
    }

    // Grammar:
    // expression = term | expression `+` term | expression `-` term
    // term = factor | term `*` factor | term `/` factor
    // factor = `+` factor | `-` factor | `(` expression `)`
    //        | number | functionName factor | factor `^` factor

    public double parseExpression() {
        double x = parseTerm();
        for (; ; ) {
            if (eat('+')) {
                x += parseTerm(); // addition

            } else if (eat('-')) {
                x -= parseTerm(); // subtraction
            } else return x;
        }
    }

    public double parseTerm() {
        double x = parseFactor();
        while (true) {
            if (eat('*')) x *= parseFactor(); // multiplication
            else if (eat('/')) x /= parseFactor(); // division
            else return x;
        }
    }

    public double parseFactor() {
        if (eat('+')) return parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') {
                nextChar();
            }
            x = Double.parseDouble(str.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') {
                nextChar();
            }
            String func = str.substring(startPos, this.pos);
            x = parseFactor();
            if (func.equals("sqrt")) x = Math.sqrt(x);
            else if (func.equals("sin")) x = Math.sin(x);
            else if (func.equals("cos")) x = Math.cos(x);
            else if (func.equals("tan")) x = Math.tan(x);
            else if (func.equals("exp")) x = Math.exp(x);
            else if (func.equals("ln")) x = Math.log(x);
        } else {
            error = true;
            x = 0;
        }

        if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

        return x;

    }
}

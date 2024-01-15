package cse2010.hw2;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * An array implementation of the polynomial API.
 */
public class Poly implements Polynomial {
    private Term[] terms;   // array of terms, not sorted
    private int next = 0;   // denotes next available slot & (term count)

    /**
     * Creates a new polynomial which can hold up to `maxCount` Term`s.
     *
     * @param maxCount number of terms
     */
    public Poly(int maxCount) {
        terms = new Term[maxCount];
    }

    /**
     * Creates a new polynomial with given terms as parameters.
     *
     * @param terms array of terms to be added.
     */
    public Poly(Term ... terms) {
        this(terms.length);

        for (Term term : terms) {
            addTerm(term.coef, term.exp);
        }
    }

    /**
     * Returns the degree of this polynomial.
     *
     * @return degree of polynomial
     */
    @Override
    public int degree() {
        int degree = 0;
        for (Term term : terms) {
            if (term == null) continue;
            degree = Math.max(degree, term.exp);
        }
        return degree;
    }

    /**
     * Returns the number of terms in this polynomial.
     *
     * @return the number of terms
     */
    @Override
    public int getTermCount() {
        return next;
    }

    /**
     * Returns the coefficient of the term with the given exponent.
     *
     * @param exponent exponent
     * @return coefficient of the term with the given exponent
     */
    @Override
    public int getCoefficient(int exponent) {
        for (Term term : terms) {
            if (term == null) continue;
            if (term.exp == exponent) return term.coef;
        }
        return 0;
    }

    /**
     * Insert a new term into a given polynomial.
     *
     * @param coef     coefficient
     * @param exponent exponent
     */
    @Override
    public void addTerm(int coef, int exponent) {
        if (coef == 0) return;
        terms[next++] = new Term(coef, exponent);
    }

    /**
     * Adds the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the sum of two polynomials (C = A.add(B). Note that A should not be
     * modified as a result of this operation.
     *
     * @param rhs a polynomial
     * @return a new polynomial (`rhs` + `this`)
     */
    @Override
    public Polynomial add(Polynomial rhs) {
        Polynomial ans = new Poly(this.getTermCount());

        int maxDegree = Math.max(this.degree(), rhs.degree());

        for (int i = 0; i <= maxDegree; i++) {
            int coef = this.getCoefficient(i) + rhs.getCoefficient(i);
            ans.addTerm(coef, i);
        }

        return ans;
    }

    /**
     * Multiply the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the product of two polynomials (C = A.mutiply(B). Note that A should not be
     * modified as a result of this operation.
     *
     * @param rhs a polynomial
     * @return a new polynomial (`rhs` * `this`)
     */
    @Override
    public Polynomial mult(Polynomial rhs) {
        Polynomial ans = new Poly(this.degree() * rhs.degree() + 1);
        int[] coefficients = new int[this.degree() * rhs.degree() + 1];

        for (int i = 0; i <= this.getTermCount(); i++) {
            for (int j = 0; j <= rhs.getTermCount(); j++) {
                int coef = this.getCoefficient(i) * rhs.getCoefficient(j);
                int exponent = i + j;
                coefficients[exponent] += coef;
            }
        }

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] != 0) {
                ans.addTerm(coefficients[i], i);
            }
            if (coefficients[i] == 0) break;
        }

        return ans;
    }


    /**
     * Evaluates the polynomial for a given value of x.
     *
     * @param x a value of x
     * @return the value of the polynomial for the given value of x
     */
    @Override
    public double eval(double x) {
        double ans = 0;
        for (Term term : terms) {
            if (term == null) continue;
            ans += term.coef * Math.pow(x, term.exp);
        }
        return ans;
    }

    /**
     * Returns a string representation of this polynomial.
     *
     * @return a string representation of this polynomial
     */
    @Override
    public String toString() {
        // Sample code ... you can freely modify this code if necessary
        Arrays.sort(terms, 0, next, (a, b) -> b.exp - a.exp);
        return Arrays.stream(terms)
                .filter(Objects::nonNull)
                .map(Term::toString)
                .collect(Collectors.joining(" + "));
    }
}
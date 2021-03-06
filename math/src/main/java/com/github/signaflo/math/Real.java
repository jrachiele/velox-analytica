/*
 * Copyright (c) 2016 Jacob Rachiele
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to
 * do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Contributors:
 *
 * Jacob Rachiele
 */
package com.github.signaflo.math;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * A numerical approximation of a <a target="_blank" href=https://en.wikipedia.org/wiki/Real_number>
 * real number</a>. This class is immutable and thread-safe.
 *
 * @author Jacob Rachiele
 */
@EqualsAndHashCode
public final class Real implements FieldElement<Real> {

    private final double value;

    /**
     * Create a new real number using the given double.
     *
     * @param value the primitive double approximating the real number.
     * @return a new real number from the given double.
     */
    public static Real from(final double value) {
        return new Real(value);
    }

    public static Real zero() {
        return Real.from(0.0);
    }

    /**
     * Create a new real number using the given double.
     *
     * @param value the primitive double approximating the real number.
     */
    private Real(final double value) {
        this.value = value;
    }

    /**
     * Add this real number to the given real number and return the result.
     *
     * @param other the real number to add to this one.
     * @return the sum of this real number and the given real number.
     */
    @Override
    public Real plus(final Real other) {
        return Real.from(this.value + other.value);
    }

    public Complex plus(final Complex complex) {
        return Complex.from(this).plus(complex);
    }

    /**
     * Subtract the given real number from this real number and return the result.
     *
     * @param other the real number to subtract from this one.
     * @return the difference of the given real number from this real number.
     */
    @Override
    public Real minus(final Real other) {
        return Real.from(this.value - other.value);
    }

    public Complex minus(final Complex complex) {
        return Complex.from(this).minus(complex);
    }

    /**
     * Multiply this real number by the given real number and return the result.
     *
     * @param other the real number to multiply this one by.
     * @return this real number multiplied by the given real number.
     */
    public Real times(Real other) {
        return Real.from(this.value * other.value);
    }

    @Override
    public Real sqrt() {
        if (this.value < 0.0) {
            throw new IllegalStateException("Attempt to take the square root of a negative number on a Real type.");
        }
        return Real.from(Math.sqrt(this.value));
    }

    @Override
    public Complex complexSqrt() {
        if (this.value < 0.0) {
            Complex c = new Complex(this.value);
            return c.complexSqrt();
        }
        return new Complex(Math.sqrt(this.value));
    }

    @Override
    public Real conjugate() {
        return this;
    }

    public Real times(double other) {
        return Real.from(this.value * other);
    }

    /**
     * Square this real number and return the result.
     *
     * @return the square of this real number.
     */
    public Real squared() {
        return Real.from(this.value * this.value);
    }

    /**
     * Cube this real number and return the result.
     *
     * @return the cube of this real number.
     */
    public Real cubed() {
        return Real.from(this.value * this.value * this.value);
    }

    /**
     * Divide this real number by the given real number and return the result.
     *
     * @param other the real number to divide this one by.
     * @return this real number divided by the given real number.
     */
    @Override
    public Real dividedBy(Real other) {
        return Real.from(this.value / other.value);
    }

    /**
     * Take the additive inverse, or negative, of this real number and return the result.
     *
     * @return the additive inverse, or negative, of this real number.
     */
    @Override
    public Real additiveInverse() {
        return Real.from(-this.value);
    }

    @Override
    public double abs() {
        return Math.abs(this.value);
    }

    public double asDouble() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Real: " + Double.toString(this.value);
    }

    @Override
    public int compareTo(@NonNull Real other) {
        return Double.compare(this.value, other.value);
    }

    /**
     * An interval on the real line.
     */
    public static final class Interval {

        private final Real lower;
        private final Real upper;

        Interval(final Real lower, final Real upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public Interval(final double lower, final double upper) {
            this.lower = Real.from(lower);
            this.upper = Real.from(upper);
        }

        public double lowerDbl() {
            return this.lower.asDouble();
        }

        public double upperDbl() {
            return this.upper.asDouble();
        }

        public Real lower() {
            return this.lower;
        }

        public Real upper() {
            return this.upper;
        }

        /**
         * Test if the endpoints of this interval are equal. Note that this method tests for <em>exact</em>
         * equality. To test for equality within a given tolerance, use {@link #endpointsEqual(double)}.
         *
         * @return true if the endpoints are equal and false otherwise.
         */
        public boolean endpointsEqual() {
            return this.lowerDbl() == this.upperDbl();
        }

        /**
         * Test if the endpoints of this interval are equal within the given tolerance level.
         *
         * @param epsilon the tolerance level.
         * @return true if the endpoints are equal within the given tolerance and false otherwise.
         */
        public boolean endpointsEqual(double epsilon) {
            return Math.abs(this.lower.value - this.upper.value) < epsilon;
        }

        public boolean contains(final double value) {
            if (lower.value < upper.value) {
                return value >= lower.value && value <= upper.value;
            }
            return value <= lower.value && value >= upper.value;
        }

        public boolean doesntContain(final double value) {
            return !contains(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (!lower.equals(interval.lower)) return false;
            return upper.equals(interval.upper);
        }

        @Override
        public int hashCode() {
            int result = lower.hashCode();
            result = 31 * result + upper.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "(" + Double.toString(this.lower.asDouble()) + ", " + Double.toString(this.upper.asDouble()) + ")";
        }
    }
}

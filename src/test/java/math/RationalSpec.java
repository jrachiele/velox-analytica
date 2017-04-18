/*
 * Copyright (c) 2017 Jacob Rachiele
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

package math;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class RationalSpec {

    Rational r1 = Rational.from(3, 4);
    Rational r2 = Rational.from(7, 9);
    Rational r3 = Rational.from(4);

    @Test
    public void whenAdditiveInverseThenSumIsZero() {
        assertThat(r1.plus(r1.additiveInverse()), is(Rational.from(0)));
    }

    @Test
    public void testHashCodeAndEquals() {
        Rational r4 = Rational.from(36, 48);;
        Real real = Real.from(3.0/4.0);
        assertThat(r1.equals(r4), is(true));
        assertThat(r1.hashCode(), is(r4.hashCode()));
        assertThat(r1.hashCode(), is(not(r2.hashCode())));
        assertThat(r1.equals(real), is(false));
        assertThat(r4.hashCode(), is(not(real.hashCode())));
    }
}
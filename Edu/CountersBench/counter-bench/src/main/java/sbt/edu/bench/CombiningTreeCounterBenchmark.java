/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package sbt.edu.bench;

import org.openjdk.jmh.annotations.*;
import sbt.edu.sharedcounter.SharedCounter;
import sbt.edu.sharedcounter.combiningtree.CombiningTreeCounter;


import java.util.concurrent.TimeUnit;

@State(Scope.Group)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class CombiningTreeCounterBenchmark {


    private final static SharedCounter counter = new CombiningTreeCounter(40);

    private final int bound = 1_000_000;


    @Benchmark
    @Group("oneThreadTest")
    @GroupThreads(value = 1)
    public void oneThread() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }


    }

    @Benchmark
    @Group("twoThreadTest")
    @GroupThreads(value = 2)
    public void twoThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/2; i++) {
            result = counter.getAndIncrement();
        }
    }


    @Benchmark
    @Group("fourThreadTest")
    @GroupThreads(value = 4)
    public void fourThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/4; i++) {
            result = counter.getAndIncrement();
        }
    }
 /*  @Benchmark
    @Group("sixThreadTest")
    @GroupThreads(value = 6)
    public void sixThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/6; i++) {
            result = counter.getAndIncrement();
        }
    }
*/
    @Benchmark
    @Group("eightThreadTest")
    @GroupThreads(value = 8)
    public void eightThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/8; i++) {
            result = counter.getAndIncrement();
        }
    }
    /*
    @Benchmark
    @Group("tenThreadTest")
    @GroupThreads(value = 10)
    public void tenThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/10; i++) {
            result = counter.getAndIncrement();
        }
    }
    @Benchmark
    @Group("twelveThreadTest")
    @GroupThreads(value = 12)
    public void twelveThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/12; i++) {
            result = counter.getAndIncrement();
        }
    }
*/
    @Benchmark
    @Group("sixteenThreadTest")
    @GroupThreads(value = 16)
    public void sixteenThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound/16; i++) {
            result = counter.getAndIncrement();
        }
    }
}

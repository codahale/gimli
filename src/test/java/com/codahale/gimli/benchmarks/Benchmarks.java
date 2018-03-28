/*
 * Copyright © 2017 Coda Hale (coda.hale@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codahale.gimli.benchmarks;

import com.codahale.gimli.Gimli;
import com.codahale.gimli.GimliDigest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class Benchmarks {

  public static void main(String[] args) throws IOException, RunnerException {
    Main.main(args);
  }

  @Benchmark
  public int[] permute() {
    final int[] x = new int[12];
    Gimli.permute(x);
    return x;
  }

  @Benchmark
  public byte[] permuteBytes() {
    final byte[] b = new byte[48];
    Gimli.permute(b);
    return b;
  }

  @Benchmark
  public byte[] hash() {
    final byte[] input = new byte[1024];
    return new GimliDigest(32).digest(input);
  }
}

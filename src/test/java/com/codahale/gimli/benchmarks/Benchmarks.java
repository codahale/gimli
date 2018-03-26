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

  private final int[] x =
      new int[] {
        0x00000000, 0x9e3779ba, 0x3c6ef37a, 0xdaa66d46,
        0x78dde724, 0x1715611a, 0xb54cdb2e, 0x53845566,
        0xf1bbcfc8, 0x8ff34a5a, 0x2e2ac522, 0xcc624026
      };

  @Benchmark
  public void permute() {
    Gimli.permute(x);
  }
}

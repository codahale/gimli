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
package com.codahale.gimli.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.gimli.Gimli;
import com.google.common.io.BaseEncoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GimliTest {

  private final int[] x = new int[12];
  private final int[] xOut =
      new int[] {
        0xba11c85a,
        0x91bad119,
        0x380ce880,
        0xd24c2c68,
        0x3eceffea,
        0x277a921c,
        0x4f73a0bd,
        0xda5a9cd8,
        0x84b673f0,
        0x34e52ff7,
        0x9e2bef49,
        0xf41bb8d6
      };

  private final byte[] b = new byte[48];
  private final byte[] bOut =
      BaseEncoding.base16()
          .decode(
              "5AC811BA19D1BA9180E80C38682C4CD2EAFFCE3E1C927A27BDA0734FD89C5ADAF073B684F72FE53449EF2B9ED6B81BF4");

  @BeforeEach
  void setUp() {
    // from test.c
    for (int i = 0; i < 12; ++i) {
      x[i] = i * i * i + i * 0x9e3779b9;
    }

    // convert to little endian
    for (int i = 0; i < x.length; i++) {
      final int v = x[i];
      b[i * 4] = (byte) v;
      b[i * 4 + 1] = (byte) (v >>> 8);
      b[i * 4 + 2] = (byte) (v >>> 16);
      b[i * 4 + 3] = (byte) (v >>> 24);
    }
  }

  @Test
  void testVector() {
    Gimli.permute(x);
    assertThat(x).isEqualTo(xOut);
  }

  @Test
  void testVectorBytes() {
    Gimli.permute(b);
    assertThat(b).isEqualTo(bOut);
  }
}

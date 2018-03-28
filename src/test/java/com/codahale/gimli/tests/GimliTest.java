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
import org.junit.jupiter.api.Test;

class GimliTest {

  @Test
  void testVector() {
    final int[] x =
        new int[] {
          0x00000000, 0x9e3779ba, 0x3c6ef37a, 0xdaa66d46,
          0x78dde724, 0x1715611a, 0xb54cdb2e, 0x53845566,
          0xf1bbcfc8, 0x8ff34a5a, 0x2e2ac522, 0xcc624026
        };

    Gimli.permute(x);

    assertThat(x)
        .containsExactly(
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
            0xf41bb8d6);
  }

  @Test
  void bytes() {
    final byte[] x =
        new byte[] {
          0x00,
          0x00,
          0x00,
          0x00,
          (byte) 0xba,
          0x79,
          0x37,
          (byte) 0x9e,
          0x7a,
          (byte) 0xf3,
          0x6e,
          0x3c,
          0x46,
          0x6d,
          (byte) 0xa6,
          (byte) 0xda,
          0x24,
          (byte) 0xe7,
          (byte) 0xdd,
          0x78,
          0x1a,
          0x61,
          0x15,
          0x17,
          0x2e,
          (byte) 0xdb,
          0x4c,
          (byte) 0xb5,
          0x66,
          0x55,
          (byte) 0x84,
          0x53,
          (byte) 0xc8,
          (byte) 0xcf,
          (byte) 0xbb,
          (byte) 0xf1,
          0x5a,
          0x4a,
          (byte) 0xf3,
          (byte) 0x8f,
          0x22,
          (byte) 0xc5,
          0x2a,
          0x2e,
          0x26,
          0x40,
          0x62,
          (byte) 0xcc
        };

    Gimli.permute(x);

    assertThat(x)
        .containsExactly(
            0x5a, 0xc8, 0x11, 0xba, 0x19, 0xd1, 0xba, 0x91, 0x80, 0xe8, 0x0c, 0x38, 0x68, 0x2c,
            0x4c, 0xd2, 0xea, 0xff, 0xce, 0x3e, 0x1c, 0x92, 0x7a, 0x27, 0xbd, 0xa0, 0x73, 0x4f,
            0xd8, 0x9c, 0x5a, 0xda, 0xf0, 0x73, 0xb6, 0x84, 0xf7, 0x2f, 0xe5, 0x34, 0x49, 0xef,
            0x2b, 0x9e, 0xd6, 0xb8, 0x1b, 0xf4);
  }
}

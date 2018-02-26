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

import static org.junit.Assert.assertArrayEquals;

import com.codahale.gimli.Gimli;
import org.junit.Test;

public class GimliTest {

  @Test
  public void testVector() {
    final int[] x =
        new int[] {
          0x00000000, 0x9e3779ba, 0x3c6ef37a, 0xdaa66d46,
          0x78dde724, 0x1715611a, 0xb54cdb2e, 0x53845566,
          0xf1bbcfc8, 0x8ff34a5a, 0x2e2ac522, 0xcc624026
        };

    Gimli.permute(x);

    assertArrayEquals(
        new int[] {
          0xba11c85a, 0x91bad119, 0x380ce880, 0xd24c2c68,
          0x3eceffea, 0x277a921c, 0x4f73a0bd, 0xda5a9cd8,
          0x84b673f0, 0x34e52ff7, 0x9e2bef49, 0xf41bb8d6
        },
        x);
  }
}

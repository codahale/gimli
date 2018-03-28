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

import com.codahale.gimli.GimliDigest;
import com.google.common.io.BaseEncoding;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.junit.jupiter.api.Test;

class GimliDigestTest {

  private final byte[] input =
      "There's plenty for the both of us, may the best Dwarf win.".getBytes(StandardCharsets.UTF_8);
  private final byte[] output =
      BaseEncoding.base16()
          .decode("4AFB3FF784C7AD6943D49CF5DA79FACFA7C4434E1CE44F5DD4B28F91A84D22C8");

  @Test
  void hashingThings() {
    final MessageDigest digest = new GimliDigest(32);
    final byte[] d = digest.digest(input);
    assertThat(d).isEqualTo(output);
  }
}

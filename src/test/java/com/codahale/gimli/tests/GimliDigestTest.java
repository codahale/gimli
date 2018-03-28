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
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GimliDigestTest {

  // from
  // https://crypto.stackexchange.com/questions/51025/doubt-about-published-test-vectors-for-gimli-hash
  private static final String[][] TEST_VECTORS = {
    {
      "54686572 65277320 706c656e 74792066 6f722074 68652062 6f746820 "
          + "6f662075 732c206d 61792074 68652062 65737420 44776172 66207769\n"
          + "6e2e",
      "4afb3ff784c7ad6943d49cf5da79facfa7c4434e1ce44f5dd4b28f91a84d22c8"
    },
    {
      "49662061 6e796f6e 65207761 7320746f 2061736b 20666f72 206d7920\n"
          + "6f70696e 696f6e2c 20776869 63682049 206e6f74 65207468 65792772\n"
          + "65206e6f 742c2049 27642073 61792077 65207765 72652074 616b696e\n"
          + "67207468 65206c6f 6e672077 61792061 726f756e 642e",
      "ba82a16a7b224c15bed8e8bdc88903a4006bc7beda78297d96029203ef08e07c"
    },
    {
      "53706561 6b20776f 72647320 77652063 616e2061 6c6c2075 6e646572\n" + "7374616e 6421",
      "8dd4d132059b72f8e8493f9afb86c6d86263e7439fc64cbb361fcbccf8b01267"
    },
    {
      "49742773 20747275 6520796f 7520646f 6e277420 73656520 6d616e79\n"
          + "20447761 72662d77 6f6d656e 2e20416e 6420696e 20666163 742c2074\n"
          + "68657920 61726520 736f2061 6c696b65 20696e20 766f6963 6520616e\n"
          + "64206170 70656172 616e6365 2c207468 61742074 68657920 61726520\n"
          + "6f667465 6e206d69 7374616b 656e2066 6f722044 77617266 2d6d656e\n"
          + "2e20416e 64207468 69732069 6e207475 726e2068 61732067 6976656e\n"
          + "20726973 6520746f 20746865 2062656c 69656620 74686174 20746865\n"
          + "72652061 7265206e 6f204477 6172662d 776f6d65 6e2c2061 6e642074\n"
          + "68617420 44776172 76657320 6a757374 20737072 696e6720 6f757420\n"
          + "6f662068 6f6c6573 20696e20 74686520 67726f75 6e642120 57686963\n"
          + "68206973 2c206f66 20636f75 7273652c 20726964 6963756c 6f75732e",
      "8887a5367d961d6734ee1a0d4aee09caca7fd6b606096ff69d8ce7b9a496cd2f"
    },
    {"", "b0634b2c0b082aedc5c0a2fe4ee3adcfc989ec05de6f00addb04b3aaac271f67"}
  };

  private static byte[] decode(String hex) {
    return BaseEncoding.base16().decode(hex.replaceAll("\\s+", "").toUpperCase(Locale.ENGLISH));
  }

  private static Stream<Arguments> testVectors() {
    return Stream.of(TEST_VECTORS)
        .map(Stream::of)
        .map(v -> v.map(GimliDigestTest::decode).toArray())
        .map(Arguments::of);
  }

  @ParameterizedTest
  @MethodSource("testVectors")
  void matchTestVector(byte[] input, byte[] hash) {
    assertThat(new GimliDigest(32).digest(input)).isEqualTo(hash);
  }
}

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
package com.codahale.gimli;

import java.security.MessageDigest;
import java.util.Arrays;

public class GimliDigest extends MessageDigest {

  private static final int RATE = 16;
  private final byte[] state = new byte[48];
  private final int digestLength;
  private int blockSize = 0;

  public GimliDigest(int digestLength) {
    super("Gimli");
    this.digestLength = digestLength;
  }

  @Override
  protected void engineUpdate(byte input) {
    state[blockSize++] ^= input;
    if (blockSize == RATE) {
      Gimli.permute(state);
      blockSize = 0;
    }
  }

  @Override
  protected void engineUpdate(byte[] input, int offset, int len) {
    for (int i = offset; i < len + offset; i++) {
      engineUpdate(input[i]);
    }
  }

  @Override
  protected byte[] engineDigest() {
    final byte[] buf = Arrays.copyOf(state, state.length);

    buf[blockSize] ^= 0x1F;
    buf[RATE - 1] ^= 0x80;
    Gimli.permute(buf);

    final byte[] out = new byte[digestLength];
    int outputLen = out.length;
    int offset = 0;
    while (outputLen > 0) {
      final int blockSize = Integer.min(outputLen, RATE);
      System.arraycopy(buf, 0, out, offset, blockSize);
      offset += blockSize;
      outputLen -= blockSize;
      if (outputLen > 0) {
        Gimli.permute(buf);
      }
    }
    engineReset();
    return out;
  }

  @Override
  protected void engineReset() {
    for (int i = 0; i < state.length; i++) {
      state[i] = 0;
    }
    blockSize = 0;
  }
}

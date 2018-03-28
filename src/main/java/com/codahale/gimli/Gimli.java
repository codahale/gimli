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

/** An implementation of the secure Gimli permutation. */
public class Gimli {

  private Gimli() {
    /* singleton */
  }

  /**
   * Performs the Gimli permutation.
   *
   * @param state a 384-bit array (twelve 32-bit words)
   */
  public static void permute(int[] state) {
    for (int round = 24; round > 0; round--) {
      for (int column = 0; column < 4; column++) {
        final int x = Integer.rotateLeft(state[column], 24);
        final int y = Integer.rotateLeft(state[4 + column], 9);
        final int z = state[8 + column];

        state[8 + column] = x ^ (z << 1) ^ ((y & z) << 2);
        state[4 + column] = y ^ x ^ ((x | z) << 1);
        state[column] = z ^ y ^ ((x & y) << 3);
      }

      // small swap
      if ((round % 4) == 0) {
        final int x = state[0];
        state[0] = state[1];
        state[1] = x;
        final int y = state[2];
        state[2] = state[3];
        state[3] = y;
      }

      //  big swap
      if ((round % 4) == 2) {
        final int x = state[0];
        state[0] = state[2];
        state[2] = x;
        final int y = state[1];
        state[1] = state[3];
        state[3] = y;
      }

      // add constant
      if ((round % 4) == 0) {
        state[0] ^= (0x9e377900 | round);
      }
    }
  }

  /**
   * Performs the Gimli permutation.
   *
   * @param state a 384-bit array (forty-eight 8-bit words)
   */
  public static void permute(byte[] state) {
    final int[] buf = new int[12];
    for (int i = 0; i < buf.length; i++) {
      int off = i * 4;
      int n = state[off] & 0xff;
      n |= (state[++off] & 0xff) << 8;
      n |= (state[++off] & 0xff) << 16;
      n |= state[++off] << 24;
      buf[i] = n;
    }
    permute(buf);
    for (int i = 0; i < buf.length; i++) {
      int off = i * 4;
      state[off] = (byte) (buf[i]);
      state[++off] = (byte) (buf[i] >>> 8);
      state[++off] = (byte) (buf[i] >>> 16);
      state[++off] = (byte) (buf[i] >>> 24);
    }
  }
}

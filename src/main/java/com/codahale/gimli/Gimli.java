/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codahale.gimli;

/**
 * An implementation of the secure Gimli permutation.
 */
public interface Gimli {

  /**
   * Performs the Gimli permutation.
   *
   * @param state an 384-bit of 12 32-bit words
   */
  static void permute(int[] state) {
    for (int round = 24; round > 0; round--) {
      int x;
      int y;
      int z;

      for (int column = 0; column < 4; column++) {
        x = Integer.rotateLeft(state[column], 24);
        y = Integer.rotateLeft(state[4 + column], 9);
        z = state[8 + column];

        state[8 + column] = x ^ (z << 1) ^ ((y & z) << 2);
        state[4 + column] = y ^ x ^ ((x | z) << 1);
        state[column] = z ^ y ^ ((x & y) << 3);
      }

      // small swap
      if ((round % 4) == 0) {
        x = state[0];
        state[0] = state[1];
        state[1] = x;
        x = state[2];
        state[2] = state[3];
        state[3] = x;
      }

      //  big swap
      if ((round % 4) == 2) {
        x = state[0];
        state[0] = state[2];
        state[2] = x;
        x = state[1];
        state[1] = state[3];
        state[3] = x;
      }

      // add constant
      if ((round % 4) == 0) {
        state[0] ^= (0x9e377900 | round);
      }
    }
  }
}

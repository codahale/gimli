# gimli

[![CircleCI](https://circleci.com/gh/codahale/gimli.svg?style=svg)](https://circleci.com/gh/codahale/gimli)

A Java implementation of the [Gimli cryptographic permutation](https://gimli.cr.yp.to) and hash 
algorithm:

> Gimli is a 384-bit permutation designed to achieve high security with high performance across a
broad range of platforms, including 64-bit Intel/AMD server CPUs, 64-bit and 32-bit ARM smartphone
CPUs, 32-bit ARM microcontrollers, 8-bit AVR microcontrollers, FPGAs, ASICs without side-channel
protection, and ASICs with side-channel protection.

In conforms to the test vectors produced by the reference C implementation. **N.B:** The test
vectors for the hash algorithm [are
incorrect](https://crypto.stackexchange.com/questions/51025/doubt-about-published-test-vectors-for-gimli-hash)
and this implementation conforms to the corrected test vectors.


## Add to your project

```xml
<dependency>
  <groupId>com.codahale</groupId>
  <artifactId>gimli</artifactId>
  <version>0.1.3</version>
</dependency>
```

*Note: module name for Java 9+ is `com.codahale.gimli`.*

## Use the thing

```java
import com.codahale.gimli.Gimli;
import java.util.Arrays;

class Example {
  public void main(String... args) {
    final int[] state = new int[12]; // the input is 12 32-bit integers
    Gimli.permute(state);
    System.out.println(Arrays.toString(state));
  }
}
```

## License

Copyright © 2017 Coda Hale

Distributed under the Apache License 2.0.

# jblis
This is a java wrapper around the blis linear algebra library:

https://github.com/flame/blis

The wrapper around the native library is managed by JPassport, which uses the Foreign Function
and Memory api (FFM). As such, this wrapper is 100% java with no JNI shims required.

I got the idea for this project from this DEVOXX video by Paul Sandoz

https://www.youtube.com/watch?v=hBffN0xW784&t=691s

## Before you begin

- BLIS only works on linux, there is no windows port, I don't know about Mac.
- JDK 24 or higher is required since that is what JPassport requires
- You need to build BLIS yourself using the instructions they provide
- The generated library (.so) needs to be put somewhere that java can find it ('make install' should do this)
- The compiled .so I've included here is the Haswell build since that's what blis slected automatically for me

# Starting out

1. git clone https://github.com/flame/blis.git
2. git checkout 2.0   <- important since this is what I built and experimented against
3. ./configure auto
4. make
5. make install or find the .so build by make and copy it to the jblis folder root.

# Examples

I've ported the blis examples

https://github.com/flame/blis/tree/master/examples
to
https://github.com/boulder-on/jblis/tree/main/src/test/java/blistest

The ported examples mainly match the expected output from the C versions, though there are discrepencies. 

If you know how to use blis then the main thing you will need to add to your code is:

```java
//Get access to the object API
var blis = blisFactory.getBlisOAPI();

//or

//Get access to the typed API
var blis = blisFactory.getBlisTAPI();
```

# Interesting notes
In the DEVOXX video, the speaker showed some demo code that was generated using jextract.
I tried to use jextract to see how it behaved when working with blis. When pointing jextract
at blis.h, it generated:

- 1660 source files
- 120,000 lines of code

The jextract translation was not helpful. There was no way to navigate it to figure out
what was useful and what was not.

In order to make the library presented here, I used the extensive BLIS documentation.
I also used the jextract generated code to help with a few items (some method signatures
that were wrong in the docs as well as some struct definitions that were not clear
in the C code).
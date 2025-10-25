# jblis
This is a java wrapper around the blis linear algebra library:

https://github.com/flame/blis

The wrapper around the native library is managed by JPassport, which uses the Foreign Function
and Memory api (FFM). As such, this wrapper is 100% java with no JNI shims required.

https://github.com/boulder-on/JPassport

I got the idea for this project from this DEVOXX video by Paul Sandoz

https://www.youtube.com/watch?v=hBffN0xW784&t=691s

## Before you begin

- BLIS only works on linux (and some unix ports) and Mac, there is no Windows version
  - On Windows I've used WSL to build and use BLIS, but you must also run in the WSL as there is no way to build a DLL.
- JDK 24 or higher is required since that is what JPassport requires
- You need to build BLIS yourself using the instructions they provide
  - https://github.com/flame/blis/blob/master/docs/BuildSystem.md
- The generated library (.so) needs to be put somewhere that java can find it
  - One of the last statements from the make command is the location of the .so file. The .so can be put in the execution folder for java.


# Starting out

1. git clone https://github.com/flame/blis.git
2. git checkout 2.0   
   3. This is the tag I built and experimented against
3. ./configure -t openmp auto   
   4. This builds the library with the recommended openmp threading enabled
4. make
5. copy the built .so to the folder you intend to run from (ex. the root folder for this project)

# Examples

I've ported the blis examples

https://github.com/flame/blis/tree/master/examples

to

https://github.com/boulder-on/jblis/tree/main/src/test/java/blistest

The ported examples all match the expected output from the C versions. 

If you know how to use BLIS then the main thing you will need to add to your code is:

```java
//Get access to the object API
var blis = blisFactory.getBlisOAPI();

//or

//Get access to the typed API
var blis = blisFactory.getBlisTAPI();
```

This is a very direct port of the BLIS API, so the BLIS documentation should be your starting point.
I've done very little documentation here because it would all be redundant.

# Interesting notes
In the DEVOXX video, the speaker showed some demo code that was generated using jextract.
I tried to use jextract to see how it behaved when working with blis. When pointing jextract
at blis.h, it generated:

- 1660 source files
- 125,000 lines of code

The jextract translation was not helpful. There was no way to navigate it to figure out
what was useful and what was not.

In order to make the library presented here, I used the extensive BLIS documentation.

- https://github.com/flame/blis/blob/master/docs/BuildSystem.md
- https://github.com/flame/blis/blob/master/docs/BLISTypedAPI.md
- https://github.com/flame/blis/blob/master/docs/BLISObjectAPI.md
- https://github.com/flame/blis/blob/master/docs/Multithreading.md

I also used the jextract generated code to help with a few items (some method signatures
that were wrong in the docs as well as some struct definitions that were not clear
in the C code).
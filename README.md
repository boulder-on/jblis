# jblis
This is a java wrapper around blis:

https://github.com/flame/blis

This project was initially intended to work with JPassport, another project of mine that
acts as a wrapper for the Foreign Function and Memory (FFM) api.

This project is not yet complete and needs some further documentation. Of note:

- BLIS only works on linux, there is no windows port.
- You need to build BLIS yourself using the instructions they provide
- The generated library (.so) needs to be put somewhere that java can find it ('make install' should do this)
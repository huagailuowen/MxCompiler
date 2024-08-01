
rm -r ../../tmp
mkdir ../../tmp
cp *.g4 ../../tmp
cd ../../tmp
antlr4 Mxparser.g4
javac *.java

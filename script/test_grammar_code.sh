#!/usr/bin/zsh
export CLASSPATH=".:/usr/local/lib/antlr-4.13.1-complete.jar:$CLASSPATH"
alias antlr4='java -jar /usr/local/lib/antlr-4.13.1-complete.jar'
alias grun='java org.antlr.v4.gui.TestRig'
rm -rf ./tmp
mkdir ./tmp

# test antlr4
cp ./src/antlr/*.g4 ./tmp
cd ./tmp
which antlr4
antlr4 Mxparser.g4 -o . -visitor
javac *.java
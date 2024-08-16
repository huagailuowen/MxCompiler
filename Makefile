.PHONY: build run Compiler

build: Compiler

run:
	mvn exec:java

Compiler:
	mvn install
	mvn compile
	mvn package
	mkdir -p bin
	cp target/*.jar bin/
	chmod +x bin/my-project-1.0.0.jar
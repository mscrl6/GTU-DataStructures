# Define the compiler and flags
JC = javac
JFLAGS = -g

# Define the main target and its dependencies
TARGET = Main
SOURCES = Main.java Generator.java AVLTree.java GUIVisualization.java Stock.java StockDataManager.java
CLASSES = $(SOURCES:.java=.class)

# Define the path for Javadoc output
JAVA_DOCS = javaDocs

# Default rule to compile and run the project
all: compile run

# Compilation target for Java sources
compile: $(CLASSES)

$(CLASSES): %.class: %.java
	$(JC) $(JFLAGS) -d . $<

# Run the main Java program with an input file
run: $(CLASSES)
	java -Xint $(TARGET) input.txt

# Generate Javadoc documentation
docs:
	mkdir -p $(JAVA_DOCS)
	javadoc -d $(JAVA_DOCS) $(SOURCES)

# Clean the build directory
clean:
	rm -f $(CLASSES) *.jar

# Clean up the Javadoc directory
cleandoc:
	rm -rf $(JAVA_DOCS)

# Special targets that do not correspond to filenames
.PHONY: all compile run clean docs cleandoc

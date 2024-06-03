SRCS = Main.java Person.java SocialNetworkGraph.java

CLASSES = $(SRCS:.java=.class)

DOCS_SRCS = $(SRCS:.java=.html)


all: compile

compile: $(CLASSES)

$(CLASSES): %.class: %.java
	javac -d . $<

run: $(CLASSES)
	java Main

clean:
	rm -rf $(CLASSES) *.jar

docs:
	mkdir -p javaDocs
	javadoc -d javaDocs $(SRCS)
cleandoc:
	$(RM) -r ./javaDocs
.PHONY: all compile run clean docs

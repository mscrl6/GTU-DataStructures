SRCS = driver.java Device.java Inventory.java Laptop.java TV.java VRHeadset.java Smartphone.java Smartwatch.java

CLASSES = $(SRCS:.java=.class)

DOCS_SRCS = $(SRCS:.java=.html)


all: compile

compile: $(CLASSES)

$(CLASSES): %.class: %.java
	javac -d . $<

run: $(CLASSES)
	java driver

clean:
	rm -rf $(CLASSES) *.jar

docs:
	mkdir -p javaDocs
	javadoc -d javaDocs $(SRCS)
cleandoc:
	$(RM) -r ./javaDocs
.PHONY: all compile run clean docs

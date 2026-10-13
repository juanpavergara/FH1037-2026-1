SRC_DIR  = src
TEST_DIR = test
OUT_DIR  = out
TEST_OUT = out-test
LIB_DIR  = lib
MAIN     = fundamentos.poo.banco.BancoDemo

# JUnit 5 (JUnit Platform Console Standalone: incluye Jupiter + launcher)
JUNIT_VERSION = 1.14.4
JUNIT_JAR     = $(LIB_DIR)/junit-platform-console-standalone-$(JUNIT_VERSION).jar
JUNIT_URL     = https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$(JUNIT_VERSION)/junit-platform-console-standalone-$(JUNIT_VERSION).jar

SRCS       = $(shell find $(SRC_DIR) -name '*.java')
TEST_SRCS  = $(shell find $(TEST_DIR) -name '*.java')

.PHONY: all run test clean

all: $(OUT_DIR)
	javac -d $(OUT_DIR) -sourcepath $(SRC_DIR) $(SRCS)

run: all
	java -cp $(OUT_DIR) $(MAIN)

# Compila las pruebas contra las clases de src/ y las ejecuta con JUnit 5.
test: all $(JUNIT_JAR)
	mkdir -p $(TEST_OUT)
	javac -d $(TEST_OUT) -cp $(OUT_DIR):$(JUNIT_JAR) $(TEST_SRCS)
	java -jar $(JUNIT_JAR) execute --class-path $(OUT_DIR):$(TEST_OUT) --scan-class-path $(TEST_OUT) --disable-banner

$(JUNIT_JAR):
	mkdir -p $(LIB_DIR)
	curl -fsSL -o $@ $(JUNIT_URL)

clean:
	rm -rf $(OUT_DIR) $(TEST_OUT)

$(OUT_DIR):
	mkdir -p $(OUT_DIR)

SRC_DIR  = src
OUT_DIR  = out
MAIN     = fundamentos.poo.banco.BancoDemo

SRCS     = $(shell find $(SRC_DIR) -name '*.java')

.PHONY: all run clean

all: $(OUT_DIR)
	javac -d $(OUT_DIR) -sourcepath $(SRC_DIR) $(SRCS)

run: all
	java -cp $(OUT_DIR) $(MAIN)

clean:
	rm -rf $(OUT_DIR)

$(OUT_DIR):
	mkdir -p $(OUT_DIR)

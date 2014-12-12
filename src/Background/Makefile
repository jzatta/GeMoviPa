
CLASSES_DIR:=Classes
CROSS_TARGET_V=7
CROSS_SOURCE_V=7
OBJ:=$(subst .java,.class,$(shell find *.java))
OBJ:=$(addprefix $(CLASSES_DIR)/,$(OBJ))
LIBS=../../lib/gson-2.3.jar
CLASSES_PATH:=$(CLASSES_DIR):$(LIBS)

JC=javac -cp $(CLASSES_PATH) -sourcepath . -d $(CLASSES_DIR) -source $(CROSS_SOURCE_V) -target $(CROSS_TARGET_V)
JAVA=java -cp $(CLASSES_PATH)

all: $(OBJ)

runJulianoTester: $(CLASSES_DIR)/JulianoTester.class
	$(JAVA) JulianoTester

runJohnaltanTester: $(CLASSES_DIR)/JohnaltanTester.class
	$(JAVA) JohnaltanTester

run: $(OBJ)
	$(JAVA) Main $(ARGS)

runJar: $(OBJ)
	$(JAVA) -jar Jogo.jar $(ARGS)

jar: $(OBJ) 
	jar cfm Jogo.jar Manifest.txt $(CLASSES_DIR) $(LIBS)
	
$(CLASSES_DIR)/%.class: %.java Makefile
	$(JC) $<

$(OBJ): | $(CLASSES_DIR)

$(CLASSES_DIR):
	mkdir $(CLASSES_DIR)

clean:
	rm -rf $(CLASSES_DIR)

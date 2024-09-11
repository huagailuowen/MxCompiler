#make build
#./script/mk_buildIn_code.sh
# shellcheck disable=SC2283
#ANTLR_JAR = $(CURDIR)/lib/antlr-4.13.1-complete.jar
#
## shellcheck disable=SC2283
#LOMBOK_JAR = $(CURDIR)/lib/lombok.jar
export PATH="/usr/local/opt/bin:$PATH"
testcases/codegen/scripts/test_asm_all.bash 'make run' testcases/codegen src/main/c/builtin.s

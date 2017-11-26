CHAT_OBJS=chat.o
CHAT_PROG=chat
CHAT_PROG_OBJS=chat_main.o
CFLAGS=-pedantic -Wconversion -Wall -Werror -Wextra -Wstrict-prototypes

client: $(CHAT_OBJS) $(CHAT_PROG_OBJS) 
	echo	gcc $(CHAT_OBJS) $(CHAT_PROG_OBJS) -o $(CHAT_PROG)

run:
	./$(CHAT_PROG) localhost 1066

doc:
	doxygen chat.config
	cd latex && make pdf

pdf: doc

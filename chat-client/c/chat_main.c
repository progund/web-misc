#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "chat.h"

int feedback(const char *msg)
{
  return fprintf(stdout, "\n[CHAT] %s\n", msg);
}

int main(int argc, char **argv) {
  chat_client cc;
  int port;
  int ret;

  /*
   * 
   * Rudimentary parser
   *
   */ 
  if (argc < 3)
    {
      fprintf(stderr,"Usage:\n");
      fprintf(stderr,"  %s <hostname> <port>\n", argv[0]);
      return 1;
    }

  if (sscanf(argv[2], "%d", &port)!=1)
    {
      fprintf(stderr,"invalid port: %s\n", argv[2]);
      return 2;
    }

  /*
   * Init chat client values
   */
  init_cc(&cc, argv[1], port);
  chat_set_feedback_fun(&cc, feedback);
  
  chat_loop(&cc);

  chat_close(&cc);
  
  return 0;
}

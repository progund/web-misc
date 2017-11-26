#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

enum
  {
    CHAT_CLIENT_OK,
    CHAT_CLIENT_BAD_ARG,
    CHAT_CLIENT_SETUP_FAILED,
    CHAT_CLIENT_COULD_NOT_OPEN_SOCKET,
    CHAT_CLIENT_ERROR,
    CHAT_CLIENT_LEAVE
  };

typedef int (*input_handler)(const char *str);

typedef struct chat_client_
{
  int sockfd;
  unsigned int port;
  struct sockaddr_in serveraddr;
  struct hostent *server;
  char *host_name;

  fd_set read_fds;
  unsigned nfds;

  input_handler feedback;
  
} chat_client ;

int init_cc(chat_client* cc, char *hostname, unsigned int port);

void chat_close(chat_client *cc);

int chat_loop(chat_client *cc);

int handle_input(chat_client *cc, char *msg);

void chat_set_feedback_fun(chat_client *cc, input_handler fun);

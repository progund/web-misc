#include <stdio.h>
#include <unistd.h>
#include <strings.h>
#include <string.h>
#include "chat.h"


#define BUF_SIZE 1000


static void print_chat_client_info(chat_client *cc)
{
  fprintf(stderr, "Chat client\n");
  fprintf(stderr, "* host name: %s\n", cc->host_name);
  fprintf(stderr, "* port:      %d\n", cc->port);
}

int print_msg(char *msg)
{
  return fprintf(stdout, "%s\n", msg);
}

void chat_set_feedback_fun(chat_client *cc, input_handler fun)
{
  cc->feedback = fun;
}

int init_cc(chat_client* cc, char *hostname, unsigned int port)
{
  if (cc==NULL || hostname==NULL)
    {
      return CHAT_CLIENT_BAD_ARG;
    }

  cc->host_name = hostname;
  cc->port = port;

  chat_set_feedback_fun(cc, (input_handler)print_msg);
  
  return CHAT_CLIENT_OK;
}

static int open_socket(chat_client* cc)
{
  if (cc==NULL)
    {
      return CHAT_CLIENT_SETUP_FAILED;
    }
  
  /* socket: create the socket */
  cc->sockfd = socket(AF_INET, SOCK_STREAM, 0);
  if (cc->sockfd < 0)
    {
      fprintf(stderr, "Could not open socket");
      print_chat_client_info(cc);
      return CHAT_CLIENT_COULD_NOT_OPEN_SOCKET;
    }
  
  /* gethostbyname: get the server's DNS entry */
  cc->server = gethostbyname(cc->host_name);
  if (cc->server == NULL)
    {
      fprintf(stderr, "Could not find host as %s\n", cc->host_name);
      print_chat_client_info(cc);
      return CHAT_CLIENT_COULD_NOT_OPEN_SOCKET;
    }
    
  /* build the server's Internet address */
  bzero((char *) &cc->serveraddr, sizeof(cc->serveraddr));
  cc->serveraddr.sin_family = AF_INET;
  bcopy((char *)cc->server->h_addr, 
        (char *)&(cc->serveraddr.sin_addr.s_addr),
        cc->server->h_length);
  cc->serveraddr.sin_port = htons(cc->port);

  /* connect: create a connection with the server */
  if (connect(cc->sockfd,
              (struct sockaddr *)&(cc->serveraddr),
              sizeof(cc->serveraddr)) < 0)
    {
      fprintf(stderr, "Could not connect to server\n");
      print_chat_client_info(cc);
      return CHAT_CLIENT_COULD_NOT_OPEN_SOCKET;
    }

  //  FD_ZERO(&master);
  FD_ZERO(&(cc->read_fds));
    
  cc->nfds = cc->sockfd +2;
    
  return CHAT_CLIENT_OK;
}

int chat_loop(chat_client *cc)
{
  int n;
  char buf[BUF_SIZE];
  int ret;

  if (cc==NULL)
    {
      return CHAT_CLIENT_BAD_ARG;
    }

  ret = open_socket(cc);
  if (ret!=CHAT_CLIENT_OK)
    {
      fprintf(stderr, "Failed opening socket: %d\n", ret);
      return ret;
    }

  
  while (1)
    {
      printf("type>");
      fflush(stdout);

      FD_SET(STDIN_FILENO,&(cc->read_fds));
      FD_SET(cc->sockfd,&(cc->read_fds)); 

      if (select(cc->nfds,&(cc->read_fds),NULL,NULL,NULL) == -1){
        fprintf(stderr, "select failed....");
        return CHAT_CLIENT_ERROR;
      }

      if (FD_ISSET(cc->sockfd, &(cc->read_fds)))
        {
          
          /* print the server's reply */
          bzero(buf, BUF_SIZE);
          n = read(cc->sockfd, buf, BUF_SIZE);
          if (n < 0)
            {
              fprintf(stderr, "ERROR reading from socket");
            }
          cc->feedback(buf);
        }
      else
        {
          bzero(buf, BUF_SIZE);
          fgets(buf, BUF_SIZE, stdin);
          
          ret = handle_input(cc, buf);
          if (ret==CHAT_CLIENT_LEAVE)
            {
              return ret;
            }
        }
      
    }
    
}

#define COMP_STR(a,b) strncmp(a, b, strlen(a))

int handle_input(chat_client *cc, char *msg)
{
  int written;
  
  if (cc==NULL || msg==NULL)
    {
      return CHAT_CLIENT_BAD_ARG;
    }

  if (COMP_STR(".quit", msg)==0)
    {
      return CHAT_CLIENT_LEAVE;
    }

  written = write(cc->sockfd, msg, strlen(msg));
  if (written < 0)
    {
      fprintf(stderr,
              "Failed writing to socket: '%s'\n",
              msg);
      return CHAT_CLIENT_ERROR;
    }
  return CHAT_CLIENT_OK;  
}

void chat_close(chat_client *cc)
{
  close(cc->sockfd);
}


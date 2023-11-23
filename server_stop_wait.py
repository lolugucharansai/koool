# Server implementation
# server.py

import socket
import random

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_address = ('10.1.88.87', 1234)
    server_socket.bind(server_address)

    while True:
        message = str(random.randint(1, 1000))
        server_socket.sendto(message.encode(), ('10.1.87.114', 4321))
        print(f"Server: Sent message - {message}")

        ack, client_address = server_socket.recvfrom(1024)
        print(f"Server: Received acknowledgment - {ack.decode()} from {client_address}")

if __name__ == "__main__":
    main()

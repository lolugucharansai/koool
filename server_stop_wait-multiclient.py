
import socket
import random
import threading

def main(ip_add):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_address = ('10.1.88.87', 1234)
    server_socket.bind(server_address)

    while True:
        message = str(random.randint(1, 1000))
        server_socket.sendto(message.encode(), (ip_add, 4321))
        print(f"Server: Sent message - {message}")

        ack, client_address = server_socket.recvfrom(1024)
        print(f"Server: Received acknowledgment - {ack.decode()} from {client_address}")

if name == "main":

    t1 = threading.Thread(target=main, args=('10.1.87.114',))
    t2 = threading.Thread(target=main, args=('10.1.97.21',))
    t3 = threading.Thread(target=main, args=('192.168.137.1',))
    t1.start()
    t2.start()
    t3.start()
    t1.join()
    t2.join()
    t3.join()
    main()
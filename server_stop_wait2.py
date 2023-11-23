import socket
import random
import time

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_address = ('10.1.88.87', 12345)
    server_socket.bind(server_address)

    while True:
        message = str(random.randint(1, 1000))

        # Simulate packet loss with a 20% probability
        if random.random() < 0.2:
            print("Server: Simulated packet loss. Message not sent.")
            time.sleep(2)  # Simulate delay for dropped packet
            continue

        server_socket.sendto(message.encode(), ('10.1.87.114', 54321))
        print(f"Server: Sent message - {message}")

        ack_received = False
        retries = 3
        while not ack_received and retries > 0:
            try:
                server_socket.settimeout(5)  # Set a timeout for acknowledgment
                ack, client_address = server_socket.recvfrom(1024)
                print(f"Server: Received acknowledgment - {ack.decode()} from {client_address}")
                ack_received = True
            except socket.timeout:
                print("Server: Timeout. Retrying...")
                retries -= 1

if __name__ == "__main__":
    main()

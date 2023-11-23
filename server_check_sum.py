import socket
import random
import hashlib
import time

def compute_checksum(message):
    # Use a simple MD5 hash as the checksum
    checksum = hashlib.md5(message.encode()).hexdigest()
    return checksum

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_address = ('10.1.88.87', 12345)
    server_socket.bind(server_address)

    while True:
        message = str(random.randint(1, 1000))
        checksum = compute_checksum(message)

        server_socket.sendto(f"{message}:{checksum}".encode(), ('10.1.87.114', 54321))
        print(f"Server: Sent message - {message} with checksum - {checksum}")

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

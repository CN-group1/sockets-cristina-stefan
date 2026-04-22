import socket
import threading

def start_server(ip_address="0.0.0.0", port=1234):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    try:
        server_socket.bind((ip_address, port))
        server_socket.listen(1)
        print(f"[TCP] Serverul Python ascultă pe portul {port}")

        client_socket, client_address = server_socket.accept()
        print(f"[TCP] Client conectat de la: {client_address}")

        def receive_messages():
            try:
                while True:
                    data = client_socket.recv(1024).decode('utf-8')
                    if not data:
                        break
                    print(f"\n[Client]: {data.strip()}")
                    print("Tu (Server Python): ", end="", flush=True)
            except:
                print("\n[!] Conexiune închisă.")
            finally:
                client_socket.close()

        receive_thread = threading.Thread(target=receive_messages, daemon=True)
        receive_thread.start()

        print("Chat activ. Scrie 'exit' pentru a ieși:")
        while True:
            mesaj = input("Tu (Server Python): ")
            if mesaj.lower() == "exit":
                break
            client_socket.sendall((mesaj + "\n").encode('utf-8'))

    except Exception as e:
        print(f"Eroare: {e}")
    finally:
        server_socket.close()

if __name__ == "__main__":
    start_server()
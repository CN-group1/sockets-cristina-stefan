import socket

HOST = '0.0.0.0'
PORT = 5001

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
    s.bind((HOST, PORT))
    print(f"[SERVER UDP] Astept datagrame pe portul {PORT}...")

    mesaje_primite = 0
    while True:
        data, addr = s.recvfrom(1024)
        mesaj = data.decode('utf-8')
        mesaje_primite += 1
        print(f"[{mesaje_primite}] De la {addr}: {mesaj}")
import java.util.Scanner;

// Node untuk Linked List
class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

// Queue berbasis Linked List
class Queue {
    private Node front, rear;

    public Queue() {
        front = rear = null;
    }

    // Tambah pelanggan
    public void enqueue(String nama) {
        Node newNode = new Node(nama);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    // Layani pelanggan
    public String dequeue() {
        if (front == null) {
            return null;
        }

        String data = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return data;
    }

    // Tampilkan antrean
    public void display() {
        if (front == null) {
            System.out.println("Antrean kosong.");
            return;
        }

        System.out.println("Pelanggan dalam antrean:");
        Node temp = front;
        int i = 1;

        while (temp != null) {
            System.out.println(i + ". " + temp.data);
            temp = temp.next;
            i++;
        }
    }
}

// Main program
public class MainQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();

        int pilihan;

        do {
            System.out.println("\n=== Sistem Antrean Customer Service ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrean");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = input.nextLine();
                    queue.enqueue(nama);
                    break;

                case 2:
                    String dilayani = queue.dequeue();
                    if (dilayani != null) {
                        System.out.println("Melayani pelanggan: " + dilayani);
                    } else {
                        System.out.println("Antrean kosong.");
                    }
                    break;

                case 3:
                    queue.display();
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 0);

        input.close();
    }
}
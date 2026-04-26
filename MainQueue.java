import java.util.Scanner;

// Node untuk Linked List (dipakai Queue & Stack)
class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

// ================= QUEUE =================
class Queue {
    private Node front, rear;
    private int size;
    private final int MAX = 5; // batas maksimal

    public Queue() {
        front = rear = null;
        size = 0;
    }

    // Tambah pelanggan
    public void enqueue(String nama) {
        if (size >= MAX) {
            System.out.println("Antrean penuh! Maksimal 5 pelanggan.");
            return;
        }

        Node newNode = new Node(nama);

        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        size++;
        System.out.println("Pelanggan ditambahkan.");
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

        size--;
        return data;
    }

    // Tampilkan antrean
    public void display() {
        if (front == null) {
            System.out.println("Antrean kosong.");
            return;
        }

        System.out.println("Daftar antrean:");
        Node temp = front;
        int i = 1;

        while (temp != null) {
            System.out.println(i + ". " + temp.data);
            temp = temp.next;
            i++;
        }
    }
}

// ================= STACK =================
class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    // Push
    public void push(String data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    // Pop
    public String pop() {
        if (top == null) return null;

        String data = top.data;
        top = top.next;
        return data;
    }

    // Cek kosong
    public boolean isEmpty() {
        return top == null;
    }
}

// ================= MAIN =================
public class MainQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Queue queue = new Queue();

        // Stack untuk Undo/Redo
        Stack undoStack = new Stack();
        Stack redoStack = new Stack();
        String text = "";

        int pilihan;

        do {
            System.out.println("\n=== Sistem Antrean & Undo/Redo ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrean");
            System.out.println("4. Tambah Teks");
            System.out.println("5. Undo");
            System.out.println("6. Redo");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            pilihan = input.nextInt();
            input.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = input.nextLine();
                    queue.enqueue(nama);
                    break;

                case 2:
                    String dilayani = queue.dequeue();
                    if (dilayani != null) {
                        System.out.println("Melayani: " + dilayani);
                    } else {
                        System.out.println("Antrean kosong.");
                    }
                    break;

                case 3:
                    queue.display();
                    break;

                case 4:
                    System.out.print("Masukkan teks: ");
                    String inputText = input.nextLine();

                    undoStack.push(text); // simpan state lama
                    text += inputText;
                    redoStack = new Stack(); // reset redo

                    System.out.println("Teks sekarang: " + text);
                    break;

                case 5: // Undo
                    if (!undoStack.isEmpty()) {
                        redoStack.push(text);
                        text = undoStack.pop();
                        System.out.println("Undo berhasil.");
                    } else {
                        System.out.println("Tidak bisa undo.");
                    }
                    System.out.println("Teks sekarang: " + text);
                    break;

                case 6: // Redo
                    if (!redoStack.isEmpty()) {
                        undoStack.push(text);
                        text = redoStack.pop();
                        System.out.println("Redo berhasil.");
                    } else {
                        System.out.println("Tidak bisa redo.");
                    }
                    System.out.println("Teks sekarang: " + text);
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

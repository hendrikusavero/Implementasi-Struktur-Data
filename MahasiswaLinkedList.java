// Node khusus buat nyimpen data mahasiswa
class MahasiswaNode {
    String nim;
    String nama;
    int nilai;
    MahasiswaNode next;

    MahasiswaNode(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.next = null;
    }
}

class MahasiswaLinkedList {
    private MahasiswaNode head;

    // 1. Tambah Data 
    public void tambah(String nim, String nama, int nilai) {
        MahasiswaNode newNode = new MahasiswaNode(nim, nama, nilai);
        if (head == null) {
            head = newNode;
        } else {
            MahasiswaNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Data mahasiswa " + nama + " berhasil ditambah.");
    }

    // 2. Hapus Data (Berdasarkan NIM karena NIM itu unik)
    public void hapus(String nim) {
        if (head == null) {
            System.out.println("Data kosong, nggak ada yang bisa dihapus.");
            return;
        }

        if (head.nim.equals(nim)) {
            head = head.next;
            System.out.println("Data NIM " + nim + " dihapus.");
            return;
        }

        MahasiswaNode curr = head;
        MahasiswaNode prev = null;

        while (curr != null && !curr.nim.equals(nim)) {
            prev = curr;
            curr = curr.next;
        }

        if (curr != null) {
            prev.next = curr.next;
            System.out.println("Data NIM " + nim + " berhasil dihapus.");
        } else {
            System.out.println("NIM " + nim + " nggak ketemu.");
        }
    }

    // 3. Update Nilai
    public void update(String nim, int nilaiBaru) {
        MahasiswaNode temp = head;
        while (temp != null) {
            if (temp.nim.equals(nim)) {
                temp.nilai = nilaiBaru;
                System.out.println("Nilai NIM " + nim + " berhasil diupdate.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("NIM " + nim + " nggak ketemu, gagal update.");
    }

    // 4. Tampilkan Semua Data
    public void display() {
        if (head == null) {
            System.out.println("Data mahasiswa masih kosong.");
            return;
        }
        System.out.println("\n--- DATA MAHASISWA ---");
        MahasiswaNode temp = head;
        while (temp != null) {
            System.out.println("NIM: " + temp.nim + " | Nama: " + temp.nama + " | Nilai: " + temp.nilai);
            temp = temp.next;
        }
    }
}

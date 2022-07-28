package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class App {
    static EntityManagerFactory enmafa;
    static EntityManager enma;
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        try {
            enmafa = Persistence.createEntityManagerFactory("IsFlat");
            enma = enmafa.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: To add Flat");
                    System.out.println("2: To delete Flat");
                    System.out.println("3: See all Flats");
                    System.out.println("4: Choose by price");
                    System.out.println("5: Choose by square");
                    System.out.println("6: Choose by rooms");
                    System.out.print("-> ");

                    String s = sc.nextLine();

                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            deleteFlat(sc);
                            break;
                        case "3":
                            viewAll();
                            break;
                        case "4":
                            selectionPrice(sc);
                            break;
                        case "5":
                            selectsquare(sc);
                            break;
                        case "6":
                            selectRoom(sc);
                            break;
                        default: return;
                    }
                }
            } finally {
                sc.close();
                enma.close();
                enmafa.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
    private static void addFlat (Scanner sc) {
        System.out.print("Enter region: ");
        String region = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter square (0.5 format): ");
        double square = Double.parseDouble(sc.nextLine());
        System.out.print("Enter count of rooms: ");
        int rooms = Integer.parseInt(sc.nextLine());
        System.out.print("Enter price: ");
        int price = Integer.parseInt(sc.nextLine());

        enma.getTransaction().begin();
        try {
            Flat first = new Flat(region,address,square,rooms,price);
            enma.persist(first);
            enma.getTransaction().commit();
        } catch (Exception ex) {
            enma.getTransaction().rollback();
        }
    }

    private static void deleteFlat (Scanner sc) {
        System.out.print("Flat id to delete: ");
        long aId = Long.parseLong(sc.nextLine());
        Flat first = enma.getReference(Flat.class, aId);

        if (first == null) {
            System.out.println("Flat not found");
            return;
        }

        enma.getTransaction().begin();
        try {
            enma.remove(first);
            enma.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            enma.getTransaction().rollback();
        }
    }

    private static void viewAll() {
        Query query = enma.createQuery("select x from Flat x", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();
        for (Flat first: list) {
            System.out.println(first.toString());
        }
    }

    private static void selectionPrice (Scanner sc) {
        System.out.print("What the price is: ");
        int price = Integer.parseInt(sc.nextLine());
        Query query = enma.createQuery("select x from Flat x where x.price <= :price", Flat.class);
        query.setParameter("price", price);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("Nothing found");
            return;
        }
        for (Flat first: list) {
            System.out.println(first.toString());
        }
    }

    private static void selectsquare (Scanner sc) {
        System.out.print("What the square is: ");
        double square = Double.parseDouble(sc.nextLine());
        Query query = enma.createQuery("select x from Flat x where x.square >= :square", Flat.class);
        query.setParameter("square", square);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("Nothing found");
            return;
        }
        for (Flat first: list) {
            System.out.println(first.toString());
        }
    }

    private static void selectRoom (Scanner sc) {
        System.out.print("Number of rooms: ");
        int rooms = Integer.parseInt(sc.nextLine());
        Query query = enma.createQuery("select x from Flat x where x.rooms = :rooms", Flat.class);
        query.setParameter("rooms", rooms);
        List<Flat> list = (List<Flat>) query.getResultList();
        if (list.size() < 1) {
            System.out.println("Nothing found");
            return;
        }
        for (Flat first: list) {
            System.out.println(first.toString());
        }
    }
}

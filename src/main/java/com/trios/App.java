package com.trios;

import javax.persistence.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQL_Test2");
    public static void main( String[] args )
    {
        //getTrkPrice("Balls to the Wall");

        // receiving data from user
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String menuChoices = "Please enter an option: %n" +
                " 1-Find the price of a track using track name %n" +
                " 2-Update the price of a track %n" +
                " 3-Create a new track";
        System.out.println(menuChoices);

        int userOption = myObj.nextInt();  // Read user input
        System.out.println("Selected option: " + userOption);  // Output user input

        String requestDetails;
        switch (userOption) {
            case 1:  requestDetails = "In order to find the price of a track, please insert track name: %n";
                System.out.println(requestDetails);

                //receiving user's answer
                Scanner option1 = new Scanner(System.in);  // Create a Scanner object
                String NameUser= option1.nextLine();  // Read user input
                getTrkPrice(NameUser);

                //exit menu or not
                Scanner exitOrNot = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Do you want to make more changes ? Y or N %n");
                String userAnswer = exitOrNot.nextLine();  // Read user input

                if(userAnswer.equals("Y")){
                    System.out.println(menuChoices);
                }
                else if(userAnswer.equals("N")){
                    break;
                }

            case 2:  requestDetails = "In order to update the price of a track, please insert track ID " +
                    "and track new price: %n";
                System.out.println(requestDetails);

                //receiving user's answer
                Scanner option2 = new Scanner(System.in);  // Create a Scanner object
                int trackIdUser = option2.nextInt();  // Read user input
                double UnitPriceUser = option2.nextInt();  // Read user input
                updateTrack(trackIdUser, UnitPriceUser );

                //exit menu or not
                Scanner exitOrNot2 = new Scanner(System.in);  // Create a Scanner object
                System.out.println("Do you want to make more changes ? Y or N %n");
                String userAnswer2 = exitOrNot2.nextLine();  // Read user input

                if(userAnswer2.equals("Y")){
                    System.out.println(menuChoices);
                }
                else if(userAnswer2.equals("N")){
                    break;
                }


            case 3:  requestDetails = "In order to Create a new track, please insert track Name, Album ID, %n" +
                    "Media Type ID, Genre ID, Composer name, length(Milliseconds), Size(Bytes), Price   ";
                System.out.println(requestDetails);
                break;

            //receiving user's answer
            Scanner option3 = new Scanner(System.in);  // Create a Scanner object
            String Name = option3.nextLine();  // Read user input
            int AlbumId = option3.nextInt();  // Read user input
            int MediaTypeId = option3.nextInt();  // Read user input
            int GenreId = option3.nextInt();  // Read user input
            String Composer = option3.nextLine();  // Read user input
            int Milliseconds = option3.nextInt();  // Read user input
            int Bytes = option3.nextInt();  // Read user input
            double UnitPrice = option3.nextDouble();  // Read user input
            addTrack(Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice);

            //exit menu or not
            Scanner exitOrNot3 = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Do you want to make more changes ? Y or N %n");
            String userAnswer3 = exitOrNot3.nextLine();  // Read user input


            if(userAnswer3.equals("Y")){
                System.out.println(menuChoices);
            }
            else if(userAnswer3.equals("N")){
                break;
            }

            default: requestDetails = "Invalid selection";
                System.out.println(requestDetails);
                break;
        }

    }


    //Find price of a track based on track name
    public static void getTrkPrice(String NameUser){
        EntityManager em = emf.createEntityManager();
        try{

            TypedQuery<Track> tq = em.createQuery("select e from track e where e.Name=:NameUser", Track.class);
            tq.setParameter("NameUser", NameUser);
            Track trk = tq.getSingleResult();
            System.out.println(trk.getUnitPrice());
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            {
                em.close();
            }
        }
    }

    //update track
    public static void updateTrack(int trackIdUser, double UnitPriceUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Query tq = em.createQuery(
                    "Update track e set e.UnitPrice=:UnitPriceUser where e.TrackId=:trackIdUser");
            tq.setParameter("trackIdUser", trackIdUser);
            tq.setParameter("UnitPriceUser",UnitPriceUser);
            tq.executeUpdate();

            et.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            {
                em.close();

            }
        }
    }

    //Add new track
    public static void addTrack(String Name, int AlbumId, int MediaTypeId, int GenreId, String Composer,
                                int Milliseconds, int Bytes, double UnitPrice){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Track trk = new Track();
            trk.setName(Name);
            trk.setAlbumId(AlbumId);
            trk.setMediaTypeId(MediaTypeId);
            trk.setGenreId(GenreId);
            trk.setMediaTypeId(MediaTypeId);
            trk.setComposer(Composer);
            trk.setMilliseconds(Milliseconds);
            trk.setBytes(Bytes);
            trk.setUnitPrice(UnitPrice);
            em.persist(trk);
            et.commit();
        }catch (Exception ex){
            if(et!=null){
                et.rollback();
            }
        }finally {
            em.close();
        }
    }
}

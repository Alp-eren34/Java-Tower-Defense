package helps;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is =LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");
        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getSpriteAtlas2() {
        BufferedImage img = null;
        InputStream is =LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas2.png");
        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getWinImage() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("victory.jpg");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getGameOverImage() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("gameOver.jpg");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getMenuImage() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("mainScreen.jpg");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void CreateLogFile(){
        File txtFile = new File("res/savunma_gunlugu.txt");
        try{
            if(txtFile.exists()){
                txtFile.delete();
            }
            txtFile.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void WriteToLog(String text){
        File txtFile = new File("res/savunma_gunlugu.txt");

        try{
            // 'true' parametresi dosyanin sonuna ekleme yapilmasini saglar (append)
            FileWriter fw = new FileWriter(txtFile, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(text); // Satiri yaz ve alt satira gec

            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //degerlere gore harita yaratmak icin
    public static void CreateLevel(String name, int[] idArr){
        File newLevel = new File("res/"+name+".txt");

        if(newLevel.exists()){
            System.out.println("File:" +name + "already exists");
            return;
        }else{
            try{
                newLevel.createNewFile();
            }catch(Exception e){
                e.printStackTrace();
            }
            WriteToFile(newLevel,idArr,new PathPoint(0,0),new PathPoint(0,0));
        }
    }

    public static void WriteToFile(File f, int[] idArr,PathPoint start,PathPoint end){

        try{
            PrintWriter pw = new PrintWriter(f);
            for(Integer i : idArr) {
                pw.println(i);
            }
            pw.println(start.getxCoord());
            pw.println(start.getyCoord());
            pw.println(end.getxCoord());
            pw.println(end.getyCoord());
            pw.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    //olusturdugumuz yeni haritayi kaydetmek icin kullaniliyor
    public static void saveLevel(String name,int[][] idArr,PathPoint start,PathPoint end){
        File levelFile = new File("res/"+name+".txt");

        if(levelFile.exists()){
            WriteToFile(levelFile,Utilz.TwoD1DintArr(idArr),start,end);
        }else{
            System.out.println("File:" +name+"does not exists");
        }

    }

    private static  ArrayList<Integer> ReadFromFile(File file){
        ArrayList<Integer> list = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                list.add(Integer.parseInt(sc.nextLine()));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<PathPoint> GetLevelPathPoints(String name){
        File lvlFile = new File("res/"+name+".txt");

        if(lvlFile.exists()){
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(400),list.get(401)));
            points.add(new PathPoint(list.get(402),list.get(403)));
            return points;
        }else{
            System.out.println("File:" +name+"does not exists");
            return null;
        }
    }

    public static int [][] GetLevelData(String name){
        File lvlFile = new File("res/"+name+".txt");

        if(lvlFile.exists()){
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilz.ArrayListTo2Dint(list,20,20);
        }else{
            System.out.println("File:" +name + "does not exist");
            return null;
        }


    }

}

package encryptdecrypt;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String action="enc";
        String message="";
        int key=0;
        boolean flag=true;
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-mode")){
                action=args[i+1];
            }
            if(args[i].equals("-key")){
                key=Integer.parseInt(args[i+1]);
            }

            if(args[i].equals("-data")){
                message=args[i+1];
                flag=false;
            }
            else if(args[i].equals("-in")&&flag){
                File file = new File(args[i+1]);
                try{Scanner scanner=new Scanner(file);
                message=scanner.nextLine();
                scanner.close();
                }
                catch (IOException e){return;}
            }
        }
        String unicode=" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

        if(action.equals("enc")){
            for(int i=0;i<message.length();i++){
                boolean done=true;
                for(int j=0;j<unicode.length();j++){
                    if(message.charAt(i)==unicode.charAt(j)&&done){
                        done=false;
                        int newIndex=j+key%unicode.length();
                        char newChar=unicode.charAt(newIndex);
                        message=message.substring(0,i)+newChar+message.substring(i+1);
                    }
                }
            }
        }
        if(action.equals("dec")){
            for(int i=0;i<message.length();i++){
                char newChar=message.charAt(i);
                newChar=(char)(newChar-key);
                message=message.substring(0,i)+newChar+message.substring(i+1);
            }
        }
        boolean console=true;
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-out")){
                File file = new File(args[i+1]);
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println(message);
                    console=false;

                }
                catch (Exception e){

                }
            }

    }
        if(console){
            System.out.println(message);}
}}

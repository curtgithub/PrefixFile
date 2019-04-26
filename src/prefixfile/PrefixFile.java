/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fromFile, choose Tools | Templates
 * and open the template in the editor.
 */
package prefixfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class PrefixFile {

   public static final String FromDirString = "I:\\media\\pics\\work";
   public static final String ToDirString = "I:\\media\\pics\\D90\\";
   public static final String Prefix = "20190426-";

   public static void main(String[] args) {

      File fromDirFile = new File(PrefixFile.FromDirString);
      if (!fromDirFile.exists()) {
         System.out.println("fromDirFile " + fromDirFile + "does not exist!");
         System.exit(0);
      }

      File toDirFile = new File(PrefixFile.FromDirString);
      if (!toDirFile.exists()) {
         System.out.println("toDirFile " + toDirFile + "does not exist!");
         System.exit(0);
      }

      File[] fromFileList = fromDirFile.listFiles();
      for (File fromFile : fromFileList) {
         if (fromFile.isFile()) {
            System.out.println(fromFile.getName());
            String toFileString = PrefixFile.ToDirString + PrefixFile.Prefix + fromFile.getName();
            System.out.println("toFileString = " + toFileString);
            File toFile = new File(toFileString);
            try {
               Files.copy(fromFile.toPath(), toFile.toPath());
            } catch (IOException ex) {
               Logger.getLogger(PrefixFile.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }

   }

}

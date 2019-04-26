/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fromFile, choose Tools | Templates
 * and open the template in the editor.
 */
package prefixfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class PrefixFile {

   public static final String DEFAULT_FROM_DIR = "I:\\media\\pics\\work";
   public static final String DEFAULT_TO_DIR = "I:\\media\\pics\\D90\\";
   public static final String DEFAULT_PREFIX = "YYYYMMDD-";

   private String fromDirString;
   private String toDirString;
   private String prefix;

   public PrefixFile() {
      this.fromDirString = PrefixFile.DEFAULT_FROM_DIR;
      this.toDirString = PrefixFile.DEFAULT_TO_DIR;
      this.prefix = PrefixFile.createYyyyMmDdPrefix();
   }

   public String getFromDirString() {
      return fromDirString;
   }

   public void setFromDirString(String fromDirString) {
      this.fromDirString = fromDirString;
   }

   public String getToDirString() {
      return toDirString;
   }

   public void setToDirString(String toDirString) {
      this.toDirString = toDirString;
   }

   public String getPrefix() {
      return prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public static String createYyyyMmDdPrefix() {
      String datePrefix = new String();

      LocalDate localDate = LocalDate.now();

      int year = localDate.getYear();
      int month = localDate.getMonthValue();
      int day = localDate.getDayOfMonth();

      //System.out.println("year = " + year);
      //System.out.println("month = " + month);
      //System.out.println("day = " + day);
      datePrefix = Integer.toString(year);
      if (month < 10) {
         datePrefix = datePrefix + "0";
      }
      datePrefix = datePrefix + Integer.toString(month);
      if (day < 10) {
         datePrefix = datePrefix + "0";
      }
      datePrefix = datePrefix + Integer.toString(day);
      datePrefix = datePrefix + "-";

      return datePrefix;
   }

   public static void main(String[] args) {

      PrefixFile pf = new PrefixFile();

      System.out.println("pf.getPrefix() = " + pf.getPrefix());

      File fromDirFile = new File(pf.getFromDirString());
      if (!fromDirFile.exists()) {
         System.out.println("fromDirFile " + fromDirFile + " does not exist!");
         System.exit(0);
      }

      File toDirFile = new File(pf.getToDirString());
      if (!toDirFile.exists()) {
         System.out.println("toDirFile " + toDirFile + " does not exist!");
         System.exit(0);
      }

      File[] fromFileList = fromDirFile.listFiles();
      for (File fromFile : fromFileList) {
         if (fromFile.isFile()) {
            System.out.println(fromFile.getName());
            String toFileString = pf.toDirString + pf.getPrefix() + fromFile.getName();
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

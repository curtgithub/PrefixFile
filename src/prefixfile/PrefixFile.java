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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class PrefixFile {

   public static final String DEFAULT_FROM_DIR = "G:\\media\\pictures\\work";
   public static final String DEFAULT_TO_DIR = "G:\\media\\pictures\\VermontTrainsFoliage2020\\";
   public static final String DEFAULT_PREFIX = "YYYYMMDD-";

   private String fromDirString;
   private String toDirString;
   private String prefix;

   public PrefixFile() {
      this.fromDirString = PrefixFile.DEFAULT_FROM_DIR;
      this.toDirString = PrefixFile.DEFAULT_TO_DIR;
      this.prefix = PrefixFile.createYyyyMmDdHhMmSsPrefix();
      this.prefix = "20201010-11-";
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

   public static String createYyyyMmDdHhMmSsPrefix() {
      String dateTimePrefix = new String();

      LocalDate ld = LocalDate.now();
      LocalTime lt = LocalTime.now();;
      LocalDateTime ldt = lt.atDate(ld);

      int year = ldt.getYear();
      int month = ldt.getMonthValue();
      int day = ldt.getDayOfMonth();
      int hour = ldt.getHour();
      int minute = ldt.getMinute();
      int second = ldt.getSecond();

      //System.out.println("year = " + year);
      //System.out.println("month = " + month);
      //System.out.println("day = " + day);
      dateTimePrefix = Integer.toString(year);

      if (month < 10) {
         dateTimePrefix = dateTimePrefix + "0";
      }
      dateTimePrefix = dateTimePrefix + Integer.toString(month);

      if (day < 10) {
         dateTimePrefix = dateTimePrefix + "0";
      }
      dateTimePrefix = dateTimePrefix + Integer.toString(day);

      if (hour < 10) {
         dateTimePrefix = dateTimePrefix + "0";
      }
      dateTimePrefix = dateTimePrefix + Integer.toString(hour);

      if (minute < 10) {
         dateTimePrefix = dateTimePrefix + "0";
      }
      dateTimePrefix = dateTimePrefix + Integer.toString(minute);

      if (second < 10) {
         dateTimePrefix = dateTimePrefix + "0";
      }
      dateTimePrefix = dateTimePrefix + Integer.toString(second);

      dateTimePrefix = dateTimePrefix + "-";

      return dateTimePrefix;
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

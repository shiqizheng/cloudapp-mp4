import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;

import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class SuperTable{

   public static void main(String[] args) throws IOException {

      // Instantiate Configuration class
         Configuration  con     =       HBaseConfiguration.create();

      // Instaniate HBaseAdmin class


        HBaseAdmin      admin   =       new     HBaseAdmin(con);

      // Instantiate table descriptor class
        HTableDescriptor tableDescriptor= new HTableDescriptor(TableName.valueOf("powers"));

      // Add column families to table descriptor

        tableDescriptor.addFamily(new   HColumnDescriptor("personal"));
        tableDescriptor.addFamily(new   HColumnDescriptor("professional"));
    // Execute the table through admin
        admin.createTable(tableDescriptor);
        System.out.println("    Table created   ");

      // Instantiating HTable class
        HTable hTable = new HTable(con, "powers");

      // Repeat these steps as many times as necessary

              // Instantiating Put class
              // Hint: Accepts a row name

                Put p=new Put(Bytes.toBytes("row1"));
        //      Put x=new Put(Bytes.toBytes("row2"));
        //      Put y=new Put(Bytes.toBytes("row3"));




       // Add values using add() method

      // Hints: Accepts column family name, qualifier/row name ,value

        p.add(Bytes.toBytes("personal"),Bytes.toBytes("hero"), Bytes.toBytes("power")) ;
        p.add(Bytes.toBytes("professional"),Bytes.toBytes("name"), Bytes.toBytes("xp"));

       // x.add(Bytes.toBytes("personal"),Bytes.toBytes("hero"), Bytes.toBytes("power"));
       // x.add(Bytes.toBytes("professional"),Bytes.toBytes("name"), Bytes.toBytes("xp"));

//        y.add(Bytes.toBytes("personal"),Bytes.toBytes("hero"), Bytes.toBytes("power"));
//        y.add(Bytes.toBytes("professional"),Bytes.toBytes("name"), Bytes.toBytes("xp"));

     // Save the table
        hTable.put(p);
//      hTable.put(x);
//      hTable.put(y);
      // Close table
        hTable.close();
      // Instantiate the Scan class
        HTable  table=new HTable(con,"powers");
          Scan scan =new Scan();
      // Scan the required columns
        scan.addColumn(Bytes.toBytes("personal"),Bytes.toBytes("hero"));

      // Get the scan result
        ResultScanner scanner=table.getScanner(scan);
      // Read values from scan result
        for (Result result=scanner.next();result!=null; result  =scanner.next())
                System.out.println("Found row : "+ result);
      // Print scan result

      // Close the scanner
            scanner.close();
      // Htable closer

        table.close();
     }
}


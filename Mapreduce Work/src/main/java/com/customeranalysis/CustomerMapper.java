package com.customeranalysis;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class CustomerMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
   
        if (key.get() == 0 && value.toString().contains("InvoiceNo")) return;

        String[] fields = value.toString().split(",");
        if (fields.length >= 8) {
            String customerId = fields[6];
            String invoiceNo = fields[0];
            String quantityStr = fields[3];
            String unitPriceStr = fields[5];

            try {
                int quantity = Integer.parseInt(quantityStr);
                double unitPrice = Double.parseDouble(unitPriceStr);
                double spend = quantity * unitPrice;

                context.write(new Text(customerId),
                              new Text(invoiceNo + "," + quantity + "," + spend));
            } catch (Exception e) {
                // bỏ qua dòng lỗi
            }
        }
    }
}

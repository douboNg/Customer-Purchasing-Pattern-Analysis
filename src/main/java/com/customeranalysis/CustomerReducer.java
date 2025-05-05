package com.customeranalysis;

import java.io.IOException;
import java.util.HashSet;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomerReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalSpend = 0;
        int totalProducts = 0;
        HashSet<String> uniqueInvoices = new HashSet<>();

        for (Text val : values) {
            String[] parts = val.toString().split(",");
            if (parts.length == 3) {
                String invoice = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                double spend = Double.parseDouble(parts[2]);

                totalSpend += spend;
                totalProducts += quantity;
                uniqueInvoices.add(invoice);
            }
        }

        int totalTransactions = uniqueInvoices.size();
        double avgTransactionValue = totalTransactions == 0 ? 0 : totalSpend / totalTransactions;

        context.write(key, new Text(totalTransactions + "," +
                                    totalProducts + "," +
                                    totalSpend + "," +
                                    avgTransactionValue));
    }
}

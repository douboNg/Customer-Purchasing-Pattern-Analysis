#!/usr/bin/env python3

import sys
import csv

for i, line in enumerate(sys.stdin):
    if i == 0 and "InvoiceNo" in line:
        continue
    try:
        parts = next(csv.reader([line]))
        if len(parts) < 10:
            continue
        invoice_no = parts[0]
        quantity = int(float(parts[3]))
        purchase_amount = float(parts[9])

        print(f"INVOICE\t{invoice_no}")
        print(f"PRODUCTS\t{quantity}")
        print(f"SPEND\t{purchase_amount}")
    except Exception:
        continue

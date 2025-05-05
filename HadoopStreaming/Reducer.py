import sys

unique_invoices = set()
total_products = 0
total_spend = 0.0

for line in sys.stdin:
    key, value = line.strip().split("\t", 1)
    if key == "INVOICE":
        unique_invoices.add(value)
    elif key == "PRODUCTS":
        total_products += int(value)
    elif key == "SPEND":
        total_spend += float(value)

total_transactions = len(unique_invoices)
avg_transaction_value = total_spend / total_transactions if total_transactions > 0 else 0

print(f"Total Transactions\t{total_transactions}")
print(f"Total Products Purchased\t{total_products}")
print(f"Total Spend\t{round(total_spend, 2)}")
print(f"Average Transaction Value\t{round(avg_transaction_value, 2)}")

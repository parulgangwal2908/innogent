import numpy as np


def compute_stats(data):
    if not data:
        return ("No products available in the inventory.")

    prices = np.array([product.price for product in data])
    stocks = np.array([product.stock for product in data])

    avg_price = np.mean(prices)
    print(f"Average Price: ₹{avg_price:.2f}")
    max_price = np.max(prices)
    print(f"Maximum Price: ₹{max_price:.2f}")

    total_stock = np.sum(stocks)
    print(f"Total Stock: {total_stock} units")

    for product in data:
        print(
            f"Product:{product.name} has the total inventory value of ₹{product.value()}")


def compute_tag_stats(data, tag):
    tagged_products = [product for product in data if tag in product.tags]
    if not tagged_products:
        print(f"No products found with tag '{tag}'.")
        return
    prices = np.array([product.price for product in tagged_products])
    stocks = np.array([product.stock for product in tagged_products])
    avg_price = np.mean(prices)
    print(f"Average Price for tag '{tag}': ₹{avg_price:.2f}")
    total_value = np.sum(prices * stocks)
    print(f"Total Inventory Value for tag '{tag}': ₹{total_value:.2f}")

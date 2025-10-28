from models.product import Product
from models.food_product import FoodProduct


class inventory_service:
    def __init__(self):
        self.inventory = [
            Product("Laptop", 2, "shelf-1", 60000, {"grocery", "clearance"}),
            Product("Smartphone", 30, "shelf-2", 40000, {"grocery"}),
            Product("Headphones", 25, "shelf-3", 15000, {"clearance"}),
            Product("Desk Chair", 10, "shelf-4",
                    8000, {"grocery", "clearance"}),
        ]

    def list_all_products(self):
        if not self.inventory:
            print("no products available in the inventory")
        for product in self.inventory:
            print(product.describe())

    def stock_warning(self, Low_stock=5):
        products = []
        for product in self.inventory:
            if product.stock < Low_stock:
                products.append(product)
        if not products:
            print("All products have sufficient stock.")
        for product in products:
            print(
                f"Warning: Low stock for {product.name} - Only {product.stock} left in inventory.")

    def add_product(self):
        name = input("Enter product name: ")
        stock = int(input("Enter stock quantity: "))
        location = input("Enter product location: ")
        price = float(input("Enter product price: "))
        tags = set(input("Enter product tags (comma separated): ").split(","))
        tags = {tag.strip() for tag in tags}
        is_food = input("Is this a food product? (yes/no): ").strip().lower()
        if is_food == 'yes':
            expiry_date = input("Enter expiry date (YYYY-MM-DD): ")
            product = FoodProduct(name, stock, location,
                                  price, tags, expiry_date)
        else:
            product = Product(name, stock, location, price, tags)
        self.inventory.append(product)
        print(f"Product {name} added to inventory.")

    def update_stock(self, name, new_stock):
        for product in self.inventory:
            if product.name == name:
                product.stock = new_stock
                print(f"Stock for {name} updated to {new_stock}.")
                return
            print(f"Product {name} not found in inventory.")

    def delete_product(self, name):
        for product in self.inventory:
            if product.name == name:
                self.inventory.remove(product)
                print(f"Product {name} removed from inventory.")
                return
        print(f"Product {name} not found in inventory.")

    def total_value(self):
        total = 0.0
        for product in self.inventory:
            total += product.value()
        print(f"Total inventory value: ₹{total}")

    def discount(self):
        discounted = []
        for p in self.inventory:
            if "clearance" in p.tags:
                old_price = p.price
                p.price *= 0.8
                discounted.append((p.name, old_price, p.price))
        if discounted:
            for name, old, new in discounted:
                print(f"{name}: Old Price ₹{old} - New Price ₹{new}")
        else:
            print("No items found with 'clearance' tag.")

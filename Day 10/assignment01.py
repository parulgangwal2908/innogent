inventory = [
    {"name": "Laptop", "stock": 2, "location": "shelf-1",
        "price": 60000, "tags": {"grocery", "clearance"}},
    {"name": "Smartphone", "stock": 30, "location": "shelf-2",
     "price": 40000, "tags": {"grocery"}},
    {"name": "Headphones", "stock": 25, "location": "shelf-3",
        "price": 15000, "tags": {"clearance"}},
    {"name": "Desk Chair", "stock": 10, "location": "shelf-4",
     "price": 8000, "tags": {"grocery"}},
    {"name": "Notebook", "stock": 50, "location": "shelf-5",
     "price": 200, "tags": {"clearance", "grocery"}}
]


def list_all_products():
    if not inventory:
        print("No products available in the inventory.")
        return
    for product in inventory:
        print(f"Name: {product['name']}, Stock: {product['stock']}, Location: {product['location']}, Price: {product['price']}, Tags: {', '.join(product['tags'])}")


def stock_warning(Low_stock=5):
    products = []
    for product in inventory:
        if product['stock'] < Low_stock:
            products.append(product)

    if not products:
        print("All products have sufficient stock.")

    for product in products:
        print(
            f"Warning: Low stock for {product['name']} - Only {product['stock']} left in inventory.")


def add_product(name, stock, location, price, tags):
    product = {
        "name": name,
        "stock": stock,
        "location": location,
        "price": price,
        "tags": set(tags)
    }

    inventory.append(product)
    print(f"Product {name} added to inventory.")


def update_stock(name, new_stock):
    for product in inventory:
        if product['name'] == name:
            product['stock'] = new_stock
            print(f"Stock for {name} updated to {new_stock}.")

        else:
            print(f"Product {name} not found in inventory.")


def delete_product(name):
    for product in inventory:
        if product['name'] == name:
            inventory.remove(product)
            print(f"Product {name} removed from inventory.")
        else:
            print(f"Product {name} not found in inventory.")


def total_value():
    total = 0.0
    for product in inventory:
        total += product['stock'] * product['price']

    print(f"Total inventory value: {total}")


def apply_discount():
    discounted = []
    for p in inventory:
        if "clearance" in p["tags"]:
            old_price = p["price"]
            p["price"] = round(p["price"] * 0.5, 2)
            discounted.append((p["name"], old_price, p["price"]))

    if discounted:
        for name, old, new in discounted:
            print(f"{name}: Old Price ₹{old} - New Price ₹{new}")
    else:
        print("No items found with 'clearance' tag.")


def main():
    while True:
        print("Inventory Management System")
        print("1. List all products")
        print("2. Stock warning")
        print("3. Add a new product")
        print("4. Update stock")
        print("5. Delete a product")
        print("6. Total inventory value")
        print("7. Apply discount to clearance items")
        print("8. Exit")

        choice = input("Enter your choice (1-8): ")
        match choice:
            case '1':
                list_all_products()
            case '2':
                stock_warning()
            case '3':
                name = input("Enter product name: ")
                stock = int(input("Enter stock quantity: "))
                location = input("Enter product location: ")
                price = float(input("Enter product price: "))
                tags = input(
                    "Enter product tags (comma-separated): ").split(',')
                add_product(name, stock, location, price,
                            {tag.strip() for tag in tags})
            case '4':
                name = input("Enter product name to update stock: ")
                new_stock = int(input("Enter new stock quantity: "))
                update_stock(name, new_stock)
            case '5':
                name = input("Enter product name to delete: ")
                delete_product(name)
            case '6':
                total_value()
            case '7':
                apply_discount()
            case '8':
                print("Exiting Inventory Management System.")
                break
            case _:
                print("Invalid choice. Please try again.")


if __name__ == "__main__":
    main()

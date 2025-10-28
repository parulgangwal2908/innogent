from models.product import Product
from models.food_product import FoodProduct
from services.inventory_service import inventory_service


def main():
    service = inventory_service()
    while True:
        print("\nINVENTORY TRACKER MENU")
        print("1. List all products")
        print("2. Show low stock warnings")
        print("3. Add product")
        print("4. Update stock")
        print("5. Delete product")
        print("6. Print total inventory value")
        print("7. Apply discount by tag ('clearance')")
        print("8. Exit")
        choice = input("Enter your choice (1-8): ")
        match choice:

            case '1':
                service.list_all_products()
            case '2':
                service.stock_warning()
            case '3':
                service.add_product()
            case '4':
                name = input("Enter product name to update stock: ")
                new_stock = int(input("Enter new stock quantity: "))
                service.update_stock(name, new_stock)
            case '5':
                name = input("Enter product name to delete: ")
                service.delete_product(name)
            case '6':
                service.total_value()
            case '7':
                service.discount()
            case '8':
                print("Exiting !")
                break
            case _:
                print("Invalid choice. Please enter a number between 1 and 8.")


if __name__ == "__main__":
    main()

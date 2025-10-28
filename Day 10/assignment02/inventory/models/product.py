class Product:
    def __init__(self, name, stock, location, price, tags):
        self.name = name
        self.stock = stock
        self.location = location
        self.price = price
        self.tags = set(tags)

    def value(self):
        return self.stock * self.price

    def describe(self):
        return (f"Product: {self.name}, Stock: {self.stock}, "
                f"Location: {self.location}, Price: {self.price}, "
                f"Tags: {', '.join(self.tags)}")

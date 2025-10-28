from models.product import Product
from datetime import date


class FoodProduct(Product):
    def __init__(self, name, stock, location, price, tags, expiry_date):
        super().__init__(name, stock, location, price, tags)
        self.expiry_date = expiry_date

    def describe(self):
        return super().describe() + f", Expiry Date: {self.expiry_date}"

    def is_expired(self):
        return date.today() > self.expiry_date
